package business.persistence;

import business.entities.Request;
import business.entities.Roof;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper {
    private Database database;

    public RequestMapper(Database database) {
        this.database = database;
    }

    public List<Request> getAllRequestsByID(int userID) throws UserException {
        List<Request> requestList = null;
        System.out.println("TEST1");

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request_overview WHERE user_id =+ '" + userID + "'";
            System.out.println("TEST2");

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                System.out.println("TEST3");
                while (rs.next()) {
                    if (requestList == null) {
                        requestList = new ArrayList<>();
                    }
                    System.out.println("TEST4");

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

                    //System.out.println(id+userID+email+name+phone+statusID+status+width+length+roofID+roofName+slope+shedWidth+shedLength+price);

                   Request request = new Request(id,userID,email,name,phone,statusID,status,width,length,roofID,roofName,slope,shedWidth,shedLength,price);
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
}
