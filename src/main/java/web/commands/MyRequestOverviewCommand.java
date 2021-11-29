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

    public MyRequestOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID = Integer.parseInt(request.getParameter("requestID"));

        Request requestfound= null;
        try {
            requestfound = logicFacade.getRequestByID(requestID);
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("requestID", requestID);
        request.setAttribute("width", requestfound.getWidth() + " cm");
        request.setAttribute("length", requestfound.getLength() +" cm");
        request.setAttribute("roof", requestfound.getRoofName());
        request.setAttribute("slope", requestfound.getSlope() + " grader");
        request.setAttribute("statusID", requestfound.getStatusID());
        request.setAttribute("price", requestfound.getPrice() + " DKK");

        if (requestfound.getShedWidth()!=0){
            request.setAttribute("shedwidth", requestfound.getShedWidth() +" cm");
            request.setAttribute("shedlength", requestfound.getShedLength() + " cm");
        }else{
            request.setAttribute("shedwidth", "Ønsker ikke redskabsrum");
            request.setAttribute("shedlength", "Ønsker ikke redskabsrum");
        }


        return "myrequestoverviewpage";
    }
}
