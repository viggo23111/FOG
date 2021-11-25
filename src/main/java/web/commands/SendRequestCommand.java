package web.commands;

import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendRequestCommand extends CommandProtectedPage{
    LogicFacade logicFacade;

    public SendRequestCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession();



        int userID = Integer.parseInt(String.valueOf(session.getAttribute("userid")));

        System.out.println("userid: " + userID);

        int roofID = Integer.parseInt(request.getParameter("roofID"));
        System.out.println("roofID: " + roofID);

        int width = Integer.parseInt(request.getParameter("width"));
        System.out.println("width: " + width);
        int length = Integer.parseInt(request.getParameter("length"));
        System.out.println("length: " + length);

        int slope = Integer.parseInt(request.getParameter("slope"));
        System.out.println("slope: " + slope);


        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        System.out.println("shedWidth: " + shedWidth);
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        System.out.println("shedLength: " + shedLength);


        try {
            logicFacade.createRequestForCarportTypeTwo(userID,1,width,length,roofID,slope,shedWidth,shedLength);
        } catch (UserException e) {
            e.printStackTrace();
        }

        return "requestsentpage";
    }
}
