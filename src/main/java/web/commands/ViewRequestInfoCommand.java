package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.services.SVG;
import business.services.SVGGenerator;
import business.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewRequestInfoCommand extends CommandProtectedPage {
    UserFacade userFacade;
    LogicFacade logicFacade;
    List<BomItem> BOMList;
    SVGGenerator svg;

    public ViewRequestInfoCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
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

        List<Roof> roofList = null;

        try {
            for (Status status : logicFacade.getAllStatus()) {
                if (requestFound.getStatusID() == status.getId()) {
                    request.setAttribute("status", status.getName());
                }
            }
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("requestID", requestFound.getID());
        request.setAttribute("width", requestFound.getWidth());
        request.setAttribute("length", requestFound.getLength());
        request.setAttribute("roof", requestFound.getRoofName());
        request.setAttribute("roofID", requestFound.getRoofId());
        request.setAttribute("price", requestFound.getPrice());
        request.setAttribute("statusID", requestFound.getStatusID());

        if (requestFound.getCarportType() == 2) {
            request.setAttribute("slope", requestFound.getSlope());
            try {
                roofList = logicFacade.getAllRoofsByType(2);
            } catch (UserException e) {
                e.printStackTrace();
            }

        }
        if (requestFound.getCarportType() == 1) {
            try {
                roofList = logicFacade.getAllRoofsByType(1);
            } catch (UserException e) {
                e.printStackTrace();
            }
        }

        try {
            BOMList = logicFacade.getAllBomItemsByRequestID(requestID);
        } catch (UserException e) {
            e.printStackTrace();
        }
        double price = 0;

        for (BomItem bomItem : BOMList) {
            price += bomItem.getPrice();
        }

        double priceRoundOff = Math.round(price * 100) / 100;
        double suggestedPrice = Math.round(priceRoundOff * 2.20 * 100) / 100;
        double profit = 0;
        if (requestFound.getPrice() > 0) {
            profit = requestFound.getPrice() - priceRoundOff;
        } else {
            profit = suggestedPrice - priceRoundOff;
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
        request.setAttribute("roofList", roofList);
        request.setAttribute("shedWidth", requestFound.getShedWidth());
        request.setAttribute("shedLength", requestFound.getShedLength());
        request.setAttribute("carportType", requestFound.getCarportType());
        request.setAttribute("suggestedPrice", suggestedPrice);
        request.setAttribute("profit", profit);

        return pageToShow;
    }
}
