package web.commands;

import business.entities.Roof;
import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FlatOverviewCommand extends CommandProtectedPage{
    LogicFacade logicFacade;

    public FlatOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        String roof = request.getParameter("roof");

        System.out.println("are we here? 2");

        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));

        System.out.println("are we here? 3");

        if (shedWidth != 0 && shedLength != 0){
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
        }

        System.out.println("are we here? 4");

        request.setAttribute("width", width);
        request.setAttribute("length", length);
        request.setAttribute("roof", roof);
        request.setAttribute("shedWidth", width);
        request.setAttribute("shedLength", shedLength);

        request.setAttribute("carportType", 1);


        System.out.println("are we here? 5");
        return "carportoverviewpage";
    }
}
