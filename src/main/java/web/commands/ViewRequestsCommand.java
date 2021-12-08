package web.commands;

import business.entities.Request;
import business.entities.User;
import business.exceptions.UserException;
import business.services.LogicFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
