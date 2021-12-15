package business.persistence;

import business.entities.Roof;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoofMapper {
    private Database database;

    protected RoofMapper(Database database) {
        this.database = database;
    }

    protected List<Roof> getAllRoofsByType(int caportType) throws UserException {
        List<Roof> roofList = null;

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM roofs WHERE carport_type_id =+ '" + caportType + "'";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (roofList == null) {
                        roofList = new ArrayList<>();
                    }

                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Roof roof = new Roof(name, id);
                    roofList.add(roof);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return roofList;
    }
}
