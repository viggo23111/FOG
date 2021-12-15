package web.commands;

import business.entities.Roof;
import business.exceptions.UserException;
import business.persistence.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DesignSlopeCarportCommand extends CommandProtectedPage {
    LogicFacade logicFacade;
    List<Roof> roofList;

    public DesignSlopeCarportCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (roofList == null) {
            try {
                roofList = logicFacade.getAllRoofsByType(2);
            } catch (UserException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("roofList", roofList);
        return pageToShow;
    }
}
