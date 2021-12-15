package web.commands;

import business.entities.Request;
import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStatusCommand extends CommandProtectedPage {
    UserFacade userFacade;
    LogicFacade logicFacade;

    public UpdateStatusCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID = Integer.parseInt(request.getParameter("requestID"));

        try {
            int statusID = Integer.parseInt(request.getParameter("status"));

            Request requestFound = null;
            try {
                requestFound = logicFacade.getRequestByID(requestID);
            } catch (UserException e) {
                e.printStackTrace();
            }

            if (statusID == 4) {
                if (requestFound.getPrice() < 1) {
                    throw new Exception();
                }
            }
            try {
                logicFacade.updateStatus(requestID, statusID);
            } catch (UserException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            request.setAttribute("error", "Bekræft prisen først!");

        }
        request.setAttribute("requestID", requestID);

        ViewRequestInfoCommand viewRequestInfoCommand = new ViewRequestInfoCommand("viewrequestinfopage", "employee");
        viewRequestInfoCommand.execute(request, response);

        return pageToShow;
    }
}
