package business.services;

import business.entities.Roof;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.RoofMapper;

import java.util.List;

public class LogicFacade {
    RoofMapper roofMapper;

    public LogicFacade(Database database) {
        this.roofMapper = new RoofMapper(database);
    }

    public List<Roof> getAllRoofsByType(int caportType) throws UserException {
        return roofMapper.getAllRoofsByType(caportType);
    }
}
