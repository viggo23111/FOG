package web.commands;

import business.entities.Request;
import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.services.SVG;
import business.services.SVGGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyRequestOverviewCommand extends CommandProtectedPage {
    LogicFacade logicFacade;
    SVGGenerator svg;

    public MyRequestOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID = Integer.parseInt(request.getParameter("requestID"));

        Request requestFound = null;
        try {
            requestFound = logicFacade.getRequestByID(requestID);
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("requestID", requestID);
        request.setAttribute("width", requestFound.getWidth() + " cm");
        request.setAttribute("length", requestFound.getLength() + " cm");
        request.setAttribute("roof", requestFound.getRoofName());
        request.setAttribute("slope", requestFound.getSlope() + " grader");
        request.setAttribute("statusID", requestFound.getStatusID());
        request.setAttribute("price", requestFound.getPrice() + " DKK");
        request.setAttribute("carportType", requestFound.getCarportType());

        if (requestFound.getShedWidth() != 0) {
            request.setAttribute("shedwidth", requestFound.getShedWidth() + " cm");
            request.setAttribute("shedlength", requestFound.getShedLength() + " cm");
        } else {
            request.setAttribute("shedwidth", "Ønsker ikke redskabsrum");
            request.setAttribute("shedlength", "Ønsker ikke redskabsrum");
        }


        List<SVG> svgList = null;

        if(requestFound.getCarportType() == 1) {
            svg = new SVGGenerator(requestFound.getWidth(), requestFound.getLength(), requestFound.getShedWidth(), requestFound.getShedLength(), 0);
            svgList = svg.generateSVGFlat();
        } else {
            SVGGenerator svg = new SVGGenerator(requestFound.getWidth(),requestFound.getLength(),requestFound.getShedWidth(),requestFound.getShedLength(),requestFound.getSlope());
            svgList = svg.generateSVGSlope();
        }

        request.setAttribute("aboveView", svgList.get(0));
        request.setAttribute("sideView", svgList.get(1));

        return pageToShow;
    }
}
