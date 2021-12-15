package web.commands;

import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayCommand extends CommandProtectedPage {
    UserFacade userFacade;
    LogicFacade logicFacade;

    public PayCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID = Integer.parseInt(request.getParameter("requestID"));
        int statusID = Integer.parseInt(request.getParameter("status"));
        try {
            logicFacade.updateStatus(requestID, statusID);
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("requestID", requestID);
        MyRequestOverviewCommand myRequestOverviewCommand = new MyRequestOverviewCommand("myrequestoverviewpage", "customer");
        myRequestOverviewCommand.execute(request, response);

        return pageToShow;
    }
}
