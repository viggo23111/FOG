package business.services;

import business.entities.Request;
import business.entities.Roof;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.RequestMapper;
import business.persistence.RoofMapper;

import java.util.List;

public class LogicFacade {
    RoofMapper roofMapper;
    RequestMapper requestMapper;

    public LogicFacade(Database database) {
        this.roofMapper = new RoofMapper(database);
        this.requestMapper = new RequestMapper(database);
    }

    public List<Roof> getAllRoofsByType(int caportType) throws UserException {
        return roofMapper.getAllRoofsByType(caportType);
    }

    public List<Request> getAllRequestsByID(int userID) throws UserException {
        return requestMapper.getAllRequestsByID(userID);
    }
}
