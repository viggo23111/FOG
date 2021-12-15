package web.commands;

import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePriceCommand extends CommandProtectedPage {
    UserFacade userFacade;
    LogicFacade logicFacade;

    public UpdatePriceCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int requestID = Integer.parseInt(request.getParameter("requestID"));
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            logicFacade.updatePrice(requestID, price);
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("requestID", requestID);
        ViewRequestInfoCommand viewRequestInfoCommand = new ViewRequestInfoCommand("viewrequestinfopage", "employee");
        viewRequestInfoCommand.execute(request, response);

        return pageToShow;
    }
}
