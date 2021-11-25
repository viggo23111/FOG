package web.commands;

import business.entities.Roof;
import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DesignFlatCarportCommand extends CommandProtectedPage{
    LogicFacade logicFacade;
    List<Roof> roofList;

    public DesignFlatCarportCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        if (roofList == null){
            try {
                roofList = logicFacade.getAllRoofsByType(1);
            } catch (UserException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("roofList",roofList);

        for (Roof roof : roofList) {
            System.out.println(roof.getName() + " " + roof.getID());
        }

        return "flatcarportpage";
    }
}
