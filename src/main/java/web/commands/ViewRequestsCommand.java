package web.commands;

import business.entities.Request;
import business.exceptions.UserException;
import business.persistence.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewRequestsCommand extends CommandProtectedPage {
    LogicFacade logicFacade;

    public ViewRequestsCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Request> requestList = logicFacade.getAllRequests();
            request.setAttribute("requestList", requestList);
        } catch (UserException e) {
            e.printStackTrace();
        }
        return pageToShow;
    }
}
