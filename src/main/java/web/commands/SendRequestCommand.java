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

        int carportType = Integer.parseInt(request.getParameter("carportType"));
        int userID = Integer.parseInt(String.valueOf(session.getAttribute("userid")));



        int roofID = Integer.parseInt(request.getParameter("roofID"));
        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));

        System.out.println("roof id is: " + roofID);

        int id = 0;

        if(carportType == 2) {
            int slope = Integer.parseInt(request.getParameter("slope"));


            try {
                id = logicFacade.createRequestForCarportTypeTwo(userID, 1, width, length, roofID, slope, shedWidth, shedLength);
            } catch (UserException e) {
                e.printStackTrace();
            }
        }

        if (carportType == 1){
            try {
                id = logicFacade.createRequestForCarportTypeOne(userID, 1, width, length, roofID, shedWidth, shedLength);
            } catch (UserException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("requestID", id);

        return "requestsentpage";
    }
}
