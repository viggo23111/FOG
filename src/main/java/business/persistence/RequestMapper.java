package business.persistence;

import business.entities.Request;
import business.entities.Roof;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper {
    private Database database;

    public RequestMapper(Database database) {
        this.database = database;
    }

    public List<Request> getAllRequestsByID(int userID) throws UserException {
        List<Request> requestList = null;

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request_overview WHERE user_id =+ '" + userID + "'";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (requestList == null) {
                        requestList = new ArrayList<>();
                    }


                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    int statusID = rs.getInt("status_id");
                    String status = rs.getString("status");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int roofID = rs.getInt("roof_id");
                    String roofName = rs.getString("roof_name");
                    int slope = rs.getInt("slope");
                    int shedWidth = rs.getInt("shed_width");
                    int shedLength = rs.getInt("shed_length");
                    double price = rs.getDouble("price");
                    Timestamp createdAt = rs.getTimestamp("created_at");

                    //System.out.println(id+userID+email+name+phone+statusID+status+width+length+roofID+roofName+slope+shedWidth+shedLength+price);

                    Request request = new Request(id, userID, email, name, phone, statusID, status, width, length, roofID, roofName, slope, shedWidth, shedLength, price, createdAt);
                    requestList.add(request);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return requestList;
    }

    public Request getRequestByID(int requestID) throws UserException {
        Request request = null;

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request_overview WHERE id =+ '" + requestID + "'";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int userID = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    int statusID = rs.getInt("status_id");
                    String status = rs.getString("status");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int roofID = rs.getInt("roof_id");
                    String roofName = rs.getString("roof_name");
                    int slope = rs.getInt("slope");
                    int shedWidth = rs.getInt("shed_width");
                    int shedLength = rs.getInt("shed_length");
                    double price = rs.getDouble("price");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    request = new Request(id, userID, email, name, phone, statusID, status, width, length, roofID, roofName, slope, shedWidth, shedLength, price, createdAt);
                }
            }
        } catch (
                SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return request;
    }
}
