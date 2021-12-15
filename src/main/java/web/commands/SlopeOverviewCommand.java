package web.commands;

import business.entities.Roof;
import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.services.SVG;
import business.services.SVGGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SlopeOverviewCommand extends CommandProtectedPage {
    LogicFacade logicFacade;
    List<Roof> roofList;

    public SlopeOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            String roof = request.getParameter("roof");
            int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            int shedLength = Integer.parseInt(request.getParameter("shedLength"));

            if (roof.equals("0") || width == 0 || length == 0) {
                request.setAttribute("error", "Du mangler at udfylde nogle felter!");
                throw new Exception();
            }

            if (shedWidth > 0 && shedLength < 1 || shedLength > 0 && shedWidth < 1) {
                request.setAttribute("error", "Du skal udfylde både redskabsskurs længde og bredde ");
                throw new Exception();
            }

            int slope = Integer.parseInt(request.getParameter("slope"));
            int roofID = 0;

            try {
                for (Roof roof1 : logicFacade.getAllRoofsByType(2)) {
                    if (roof1.getName().equals(roof)) {
                        roofID = roof1.getID();
                    }
                }
            } catch (UserException e) {
                e.printStackTrace();
            }

            if (shedWidth != 0 && shedLength != 0) {
                shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
                shedLength = Integer.parseInt(request.getParameter("shedLength"));
            }
            int amountOfPoles = 0;

            if (length <= 510) {
                amountOfPoles = 4;
            } else {
                amountOfPoles = 6;
            }

            SVGGenerator svg = new SVGGenerator(width,length,0,amountOfPoles,shedWidth,shedLength,slope);

            List<SVG> svgList = svg.generateSVGSlope();

            request.setAttribute("aboveView", svgList.get(0));
            request.setAttribute("sideView", svgList.get(1));

            request.setAttribute("width", width);
            request.setAttribute("length", length);
            request.setAttribute("roof", roof);
            request.setAttribute("slope", slope);
            request.setAttribute("shedWidth", shedWidth);
            request.setAttribute("shedLength", shedLength);
            request.setAttribute("roofID", roofID);
            request.setAttribute("carportType", 2);
            return pageToShow;

        } catch (Exception e) {
            if (roofList == null) {
                try {
                    roofList = logicFacade.getAllRoofsByType(2);
                } catch (UserException ex) {
                    ex.printStackTrace();
                }
            }

            request.setAttribute("roofList", roofList);
            return "slopecarportpage";
        }
    }
}
