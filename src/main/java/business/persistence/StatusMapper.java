package business.persistence;

import business.entities.Roof;
import business.entities.Status;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusMapper {
    private Database database;

    public StatusMapper(Database database) {
        this.database = database;
    }

    public List<Status> getAllStatus() throws UserException {
        List<Status> statusList = null;

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM status";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (statusList == null) {
                        statusList = new ArrayList<>();
                    }

                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Status status = new Status(id,name);
                    statusList.add(status);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return statusList;
    }
}
