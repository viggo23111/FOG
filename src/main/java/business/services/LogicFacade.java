package business.services;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.*;

import java.util.List;

public class LogicFacade {
    RoofMapper roofMapper;
    RequestMapper requestMapper;
    BomItemMapper bomItemMapper;
    CategoryMapper categoryMapper;
    StatusMapper statusMapper;
    MaterialMapper materialMapper;

    public LogicFacade(Database database) {
        this.roofMapper = new RoofMapper(database);
        this.requestMapper = new RequestMapper(database);
        this.bomItemMapper = new BomItemMapper(database);
        this.categoryMapper = new CategoryMapper(database);
        this.statusMapper = new StatusMapper(database);
        this.materialMapper = new MaterialMapper(database);
    }

    public List<Roof> getAllRoofsByType(int caportType) throws UserException {
        return roofMapper.getAllRoofsByType(caportType);
    }
    public List<Request> getAllRequests() throws UserException {
        return requestMapper.getAllRequests();
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

    public Category getCategoryByID(int categoryID) throws UserException {
        return categoryMapper.getCategoryByID(categoryID);
    }
    public void updateRequestCarportTypeTwo(int requestID, int width, int length, int roofID, int slope, int shedWidth, int shedLength) throws UserException {
        requestMapper.updateRequestCarportTypeTwo(requestID,width,length,roofID,slope,shedWidth,shedLength);
    }

    public void updateRequestCarportTypeOne(int requestID, int width, int length, int roofID, int shedWidth, int shedLength) throws UserException {
        requestMapper.updateRequestCarportTypeOne(requestID,width,length,roofID,shedWidth,shedLength);
    }

    public void updatePrice(int requestID, double price) throws UserException {
        requestMapper.updatePrice(requestID,price);
    }

    public void updateStatus(int requestID, int statusID) throws UserException {
        requestMapper.updateStatus(requestID,statusID);
    }

    public List<Status> getAllStatus() throws UserException {
        return statusMapper.getAllStatus();
    }

    public List<Material> getAllMaterials() throws UserException {
        return materialMapper.getAllMaterials();
    }

    public void createBomItem(int requestID, List<Material> materialList) throws UserException
    {
        bomItemMapper.insertBOM(requestID,materialList);
    }
    public void deleteBomItemsByRequestID(int requestID) throws UserException {
        bomItemMapper.deleteBomItemsByRequestID(requestID);
    }
}
