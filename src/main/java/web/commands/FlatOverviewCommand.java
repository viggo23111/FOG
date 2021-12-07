package web.commands;

import business.entities.Request;
import business.entities.Roof;
import business.exceptions.UserException;
import business.services.LogicFacade;
import business.services.SVG;
import business.services.SVGGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FlatOverviewCommand extends CommandProtectedPage{
    LogicFacade logicFacade;
    SVGGenerator svg;

    public FlatOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        String roof = request.getParameter("roof");

        int roofID = 0;

        try {
            for (Roof roof1 :logicFacade.getAllRoofsByType(1)) {
                if(roof1.getName().equals(roof)){
                    roofID = roof1.getID();
                }
            }
        } catch (UserException e) {
            e.printStackTrace();
        }

        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));



        if (shedWidth != 0 && shedLength != 0){
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
        }
        int amountOfPoles = 0;

        if(length >= 510){
            amountOfPoles = 4;
        } else{
            amountOfPoles = 6;
        }


        svg = new SVGGenerator(width,length,Math.abs((double) length/55),amountOfPoles,shedWidth,shedLength);


        List<SVG> svgList = svg.generateSVG();

        request.setAttribute("aboveView",svgList.get(0));
        request.setAttribute("sideView",svgList.get(1));

        request.setAttribute("width", width);
        request.setAttribute("length", length);
        request.setAttribute("roof", roof);
        request.setAttribute("shedWidth", shedWidth);
        request.setAttribute("shedLength", shedLength);
        request.setAttribute("roofID",roofID);
        request.setAttribute("carportType", 1);


        System.out.println("are we here? 5");
        return "carportoverviewpage";
    }
}
