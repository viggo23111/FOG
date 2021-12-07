package business.persistence;

import business.entities.BomItem;
import business.entities.Material;
import business.entities.Request;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BomItemMapper {
    private Database database;


    public BomItemMapper(Database database) {
        this.database = database;
    }

    public List<BomItem> getAllBomItemsByRequestID(int requestID) throws UserException {
        List<BomItem> BOMList = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bom_overview WHERE request_id =+ '" + requestID + "'";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (BOMList == null) {
                        BOMList = new ArrayList<>();
                    }

                    int id = rs.getInt("id");
                    int materialID = rs.getInt("material_id");
                    int amount = rs.getInt("amount");
                    String name = rs.getString("name");
                    int length = rs.getInt("length");
                    String unit = rs.getString("unit");
                    String description = rs.getString("description");
                    int categoryID = rs.getInt("category_id");
                    String categoryName = rs.getString("category_name");
                    double price = rs.getDouble("total_price");

                    BomItem bomItem = new BomItem(id, requestID, materialID, amount, name, unit, description, categoryID, categoryName,price);
                    bomItem.setLength(length);
                    BOMList.add(bomItem);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return BOMList;
    }

    public void insertBOM(int requestID, List<Material> materialList) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO bom (request_id, material_id, amount,description) VALUES (?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (Material material : materialList) {
                    ps.setInt(1, requestID);
                    ps.setInt(2, material.getId());
                    ps.setInt(3, material.getAmount());
                    ps.setString(4, material.getDescription());
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void deleteBomItemsByRequestID(int requestID) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM bom WHERE request_id = ('"+requestID+"') ";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
