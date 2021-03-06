/*
 * Copyright 2012-2016 Andrey Grigorov, Anton Grigorov
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cherchgk.actions.user;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.cherchgk.domain.security.Role;
import org.cherchgk.domain.security.User;
import org.cherchgk.security.PermissionChecker;
import org.cherchgk.services.SecurityService;
import org.cherchgk.utils.ActionContextHelper;

import java.util.*;

/**
 * Действие создания и редактирования пользователя.
 *
 * @author Andrey Grigorov (peneksglazami@gmail.com)
 */
public class EditUserAction extends ActionSupport implements Preparable {

    private SecurityService securityService;
    private User user;
    private String previousPasswordHash;
    private String previousPasswordHashPrefix;

    public EditUserAction(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void validate() {
        if (user != null) {
            if (user.getId() == null) { // создание нового пользователя
                if ("".equals(user.getUsername())) {
                    addFieldError("user.username", "Не указан логин пользователя");
                } else {
                    User existedUser = securityService.getUserByName(user.getUsername());
                    if (existedUser != null) {
                        addFieldError("user.username", "Пользователь с таким логином уже существует");
                    }
                }
            }
            if ("".equals(user.getPassword())) {
                addFieldError("user.password", "Не указан пароль");
            }
        }
    }

    public void prepare() throws Exception {
        String userId = ActionContextHelper.getRequestParameterValue("user.id");
        if ((userId != null) && !"".equals(userId)) {
            user = securityService.getUserById(Long.valueOf(userId));
            previousPasswordHash = user.getPassword();
            previousPasswordHashPrefix = previousPasswordHash.substring(0, 5);
        }
        if (user == null) {
            /*
             * Возможно, это костыль, и можно всё сделать средствами Struts.
             * Сделано для того, чтобы при вызове метода setRole user был не
             * равен null, так как setUser вызывается мозже setRole.
             * В EditTeamAction такого делать не пришлось, может быть, там
             * нам просто повезло и звёзды сошлись так, что сначала вызывается
             * setTeam, а потом setTeamCategory.
             */
            user = new User();
        }
    }

    public String save() {
        if (user.getId() == null) { // создание нового пользователя
            PermissionChecker.checkPermissions("user:create");
            Iterator<Role> roleIterator = user.getRoles().iterator();
            String roleName = null;
            if (roleIterator.hasNext()) {
                roleName = roleIterator.next().getName();
            }
            securityService.createUserIfNotExist(user.getUsername(), user.getPassword(), roleName);
        } else { // обновление уже существующего пользователя
            PermissionChecker.checkPermissions("user:edit:" + user.getId());
            if (previousPasswordHashPrefix.equals(user.getPassword())) {
                user.setPassword(previousPasswordHash);
            } else {
                securityService.setUserPassword(user, user.getPassword());
            }
            securityService.updateUser(user);
        }
        return Action.SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, String> getRoles() {
        Map<String, String> roles = new LinkedHashMap<String, String>();
        roles.put("administrator", "Администратор");
        roles.put("organizer", "Организатор");
        return Collections.unmodifiableMap(roles);
    }

    public void setRole(String roleName) {
        Role role = securityService.getRoleByName(roleName);
        if (user.getRoles() != null) { // пользователь уже существует, сбросим все роли
            user.getRoles().clear();
        } else { // создаём нового пользователя, поэтому сначала создаём множество для хранения ролей
            user.setRoles(new HashSet<Role>());
        }
        user.getRoles().add(role);
    }
}