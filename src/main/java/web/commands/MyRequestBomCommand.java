package web.commands;

import business.entities.BomItem;
import business.entities.Category;
import business.entities.Request;
import business.exceptions.UserException;
import business.services.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        Category category1=null;
        Category category2=null;
        try {
            BOMList = logicFacade.getAllBomItemsByRequestID(requestID);
        } catch (UserException e) {
            e.printStackTrace();
        }
        try {
            category1=logicFacade.getCategoryByID(1);
            category2=logicFacade.getCategoryByID(2);
        } catch (UserException e) {
            e.printStackTrace();
        }
        List<BomItem> BOMlistCategory1 = new ArrayList<>();
        List<BomItem> BOMlistCategory2 = new ArrayList<>();

        for (BomItem bomItem : BOMList) {
            if(bomItem.getCategoryID()==1){
                BOMlistCategory1.add(bomItem);

            }else if (bomItem.getCategoryID()==2){
                BOMlistCategory2.add(bomItem);
            }
        }

        request.setAttribute("BOMlistcategory1", BOMlistCategory1);
        request.setAttribute("BOMlistcategory2", BOMlistCategory2);
        request.setAttribute("category1", category1.getName());
        request.setAttribute("category2", category2.getName());
        request.setAttribute("requestID", requestID);
        return "myrequestBOM";
    }
}
