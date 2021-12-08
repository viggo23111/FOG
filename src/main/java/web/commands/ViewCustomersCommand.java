package web.commands;

import business.entities.Request;
import business.entities.User;
import business.exceptions.UserException;
import business.services.LogicFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewCustomersCommand extends CommandProtectedPage {
    UserFacade userFacade;
    List<User> userList;

    public ViewCustomersCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        try {
            userList = userFacade.getAllCustomers();
        } catch (UserException e) {
            e.printStackTrace();
        }

        request.setAttribute("userList", userList);
        return pageToShow;
    }
}
