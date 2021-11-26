package web.commands;

import business.entities.Request;
import business.entities.User;
import business.exceptions.UserException;
import business.services.LogicFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewRequestInfoCommand extends CommandProtectedPage {
    UserFacade userFacade;
    LogicFacade logicFacade;
    List<User> userList;

    public ViewRequestInfoCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID= Integer.parseInt(request.getParameter("requestID"));
        Request requestFound = null;
        try {
            requestFound = logicFacade.getRequestByID(requestID);
        } catch (UserException e) {
            e.printStackTrace();
        }
        request.setAttribute("requestID", requestFound.getID());
        request.setAttribute("width", requestFound.getWidth());
        request.setAttribute("length", requestFound.getLength());
        request.setAttribute("roof", requestFound.getRoofName());
        if(requestFound.getCarportType() == 2) {
            request.setAttribute("slope", requestFound.getSlope());
        }
        if (requestFound.getCarportType() == 1){
        }

        request.setAttribute("shedWidth", requestFound.getShedWidth());
        request.setAttribute("shedLength", requestFound.getShedLength());
        request.setAttribute("carportType", requestFound.getCarportType());
        return "viewrequestinfopage";
    }
}
