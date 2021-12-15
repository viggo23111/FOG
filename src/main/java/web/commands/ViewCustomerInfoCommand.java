package web.commands;

import business.entities.Request;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.LogicFacade;
import business.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewCustomerInfoCommand extends CommandProtectedPage {
    UserFacade userFacade;
    LogicFacade logicFacade;

    public ViewCustomerInfoCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        logicFacade = new LogicFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userID = Integer.parseInt(request.getParameter("customerID"));
        User user = null;

        try {
            user = userFacade.getUserById(userID);
        } catch (UserException e) {
            e.printStackTrace();
        }

        List<Request> requestList = null;

        try {
            requestList = logicFacade.getAllRequestsByUserID(userID);
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("customer", user);
        request.setAttribute("requestList", requestList);

        return pageToShow;
    }
}
