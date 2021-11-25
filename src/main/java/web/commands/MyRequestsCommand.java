package web.commands;

import business.entities.Request;
import business.entities.Roof;
import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyRequestsCommand extends CommandProtectedPage {
    LogicFacade logicFacade;
    List<Request> requestList;

    public MyRequestsCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            requestList = logicFacade.getAllRequestsByID(1);
        } catch (UserException e) {
            e.printStackTrace();
        }

       request.setAttribute("requestList", requestList);
        for (Request request1 : requestList) {
            System.out.println("id: " + request1.getID());
            System.out.println("Status name: " + request1.getStatusName());
        }
        return "myrequests";
    }
}
