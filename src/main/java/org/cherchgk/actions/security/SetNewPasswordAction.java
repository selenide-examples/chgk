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
package org.cherchgk.actions.security;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.cherchgk.domain.security.Token;
import org.cherchgk.services.SecurityService;

/**
 * Действие установки нового пароля. Выполняется в ходе заключительного
 * шага восстановления пароля. Через данное действие можно сменить пароль
 * только в том случае, если с учётной записью для которой изменяется
 * пароль связан валидный токен, позволяющий сменить пароль.
 *
 * @author Andrey Grigorov (peneksglazami@gmail.com)
 */
public class SetNewPasswordAction extends ActionSupport {

    private SecurityService securityService;
    private String tokenUUID;
    private String password;
    private String password2;
    private String message;

    public SetNewPasswordAction(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void validate() {
        if ((password == null) || ("".equals(password))) {
            addFieldError("password", "Пароль не может быть пустым");
        } else if (!password.equals(password2)) {
            addFieldError("password2", "Пароль и повторно введённый пароль не совпадают");
        }
    }

    @Override
    public String execute() throws Exception {
        if (securityService.isValidToken(tokenUUID, Token.Type.RESTORE_PASSWORD)) {
            securityService.setNewPassword(tokenUUID, password);
            message = "Новый пароль успешно установлен.";
            return Action.SUCCESS;
        } else {
            message = "Ссылка на страницу установки нового пароля устарела либо недействительна.";
            return Action.ERROR;
        }
    }

    public String getTokenUUID() {
        return tokenUUID;
    }

    public void setTokenUUID(String tokenUUID) {
        this.tokenUUID = tokenUUID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getMessage() {
        return message;
    }
}
