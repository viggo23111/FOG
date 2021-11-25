package web.commands;

import business.entities.Request;
import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyRequestOverviewCommand extends CommandProtectedPage {
    LogicFacade logicFacade;
    List<Request> requestList;

    public MyRequestOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int userid= (int) session.getAttribute("userid");
        try {
            requestList = logicFacade.getAllRequestsByID(userid);
        } catch (UserException e) {
            e.printStackTrace();
        }

       request.setAttribute("requestList", requestList);
        return "myrequests";
    }
}
