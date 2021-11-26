package web.commands;

import business.entities.BomItem;
import business.entities.Request;
import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyRequestBomCommand extends CommandProtectedPage {
    LogicFacade logicFacade;
    List<BomItem> BOMList;

    public MyRequestBomCommand(String pageToShow, String role) {
        super(pageToShow, role);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID = Integer.parseInt(request.getParameter("requestID"));
        try {
            BOMList = logicFacade.getAllBomItemsByRequestID(requestID);
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("BOMList", BOMList);
        request.setAttribute("requestID", requestID);
        return "myrequestBOM";
    }
}
