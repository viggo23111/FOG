package web.commands;

import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SlopeOverviewCommand extends CommandProtectedPage{
    LogicFacade logicFacade;

    public SlopeOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        String roof = request.getParameter("roof");
        int slope = Integer.parseInt(request.getParameter("slope"));


        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));



        if (shedWidth != 0 && shedLength != 0){
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
        }



        request.setAttribute("width", width);
        request.setAttribute("length", length);
        request.setAttribute("roof", roof);
        request.setAttribute("slope",slope);
        request.setAttribute("shedWidth", width);
        request.setAttribute("shedLength", shedLength);

        request.setAttribute("carportType", 2);



        return "carportoverviewpage";
    }
}
