package business.persistence;

import business.entities.Category;
import business.entities.Roof;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    private Database database;

    public CategoryMapper(Database database) {
        this.database = database;
    }

    public Category getCategoryByID(int categoryID) throws UserException {
        Category category = null;

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM categories WHERE id =+ '" + categoryID + "'";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    category = new Category(categoryID,name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return category;
    }
}
