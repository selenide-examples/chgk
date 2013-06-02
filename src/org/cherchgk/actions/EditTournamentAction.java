package org.cherchgk.actions;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.cherchgk.domain.TeamCategory;
import org.cherchgk.domain.Tournament;
import org.cherchgk.services.TournamentService;
import org.cherchgk.utils.ActionContextHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Действие создания и редактирования описания турнира
 *
 * @author Andrey Grigorov (peneksglazami@gmail.com)
 */
public class EditTournamentAction extends ActionSupport implements Preparable {

    private TournamentService tournamentService;
    private Tournament tournament;

    public EditTournamentAction(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Override
    public void validate() {
        if (tournament != null) {
            updateTeamCategories();
            if ("".equals(tournament.getTitle())) {
                addFieldError("tournament.title", "Не указано название турнира");
            }
            if (tournament.getDate() == null) {
                addFieldError("tournament.date", "Не указана дата турнира");
            }
            if (tournament.getQuestionAmount() == null) {
                addFieldError("tournament.questionAmount", "Не указано количество вопросов");
            } else if ((tournament.getQuestionAmount() < 1) || (tournament.getQuestionAmount() > 100)) {
                addFieldError("tournament.questionAmount", "Количество вопросов должно быть от 1 до 100");
            }
        }
    }

    public void prepare() throws Exception {
        String tournamentId = ActionContextHelper.getRequestParameterValue("tournament.id");
        if ((tournamentId != null) && !"".equals(tournamentId)) {
            tournament = tournamentService.find(Long.valueOf(tournamentId));
        }
    }

    public String save() {
        tournamentService.save(tournament);
        return Action.SUCCESS;
    }

    private void updateTeamCategories() {
        List<TeamCategory> deletedTeamCategories = new ArrayList<TeamCategory>();
        if (tournament.getTeamCategories() != null) {
            for (TeamCategory teamCategory : tournament.getTeamCategories()) {
                if (ActionContextHelper.getRequestParameterValue("category_" + teamCategory.getId()) == null) {
                    deletedTeamCategories.add(teamCategory);
                }
            }
        }
        List<TeamCategory> newTeamCategoryList = new ArrayList<TeamCategory>();
        for (String paramName : ActionContextHelper.getRequestParameters().keySet()) {
            if (paramName.startsWith("new_category")) {
                newTeamCategoryList.add(new TeamCategory(ActionContextHelper.getRequestParameterValue(paramName), tournament));
            }
        }
        if (tournament.getTeamCategories() == null) {
            tournament.setTeamCategories(newTeamCategoryList);
        } else {
            tournament.getTeamCategories().removeAll(deletedTeamCategories);
            tournament.getTeamCategories().addAll(newTeamCategoryList);
        }
        Collections.sort(tournament.getTeamCategories());
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}