package business.services;

import business.entities.BomItem;
import business.entities.Request;
import business.entities.Roof;
import business.exceptions.UserException;
import business.persistence.BomItemMapper;
import business.persistence.Database;
import business.persistence.RequestMapper;
import business.persistence.RoofMapper;

import java.util.List;

public class LogicFacade {
    RoofMapper roofMapper;
    RequestMapper requestMapper;
    BomItemMapper bomItemMapper;

    public LogicFacade(Database database) {
        this.roofMapper = new RoofMapper(database);
        this.requestMapper = new RequestMapper(database);
        this.bomItemMapper = new BomItemMapper(database);
    }

    public List<Roof> getAllRoofsByType(int caportType) throws UserException {
        return roofMapper.getAllRoofsByType(caportType);
    }

    public List<Request> getAllRequestsByID(int userID) throws UserException {
        return requestMapper.getAllRequestsByID(userID);
    }

    public Request getRequestByID(int requestID) throws UserException {
        return requestMapper.getRequestByID(requestID);
    }

    public int createRequestForCarportTypeTwo(int userID, int statusID, int width, int length, int roofID, int slope, int shedWidth, int shedLength) throws UserException {
        return requestMapper.createRequestForCarportTypeTwo(userID,statusID,width,length,roofID,slope,shedWidth,shedLength);
    }
    public int createRequestForCarportTypeOne(int userID, int statusID, int width, int length, int roofID, int shedWidth, int shedLength) throws UserException {
        return requestMapper.createRequestForCarportTypeOne(userID,statusID,width,length,roofID,shedWidth,shedLength);
    }
    public List<BomItem> getAllBomItemsByRequestID(int requestID) throws UserException {
        return bomItemMapper.getAllBomItemsByRequestID(requestID);
    }
}
