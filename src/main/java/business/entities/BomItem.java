package business.entities;

import java.sql.Timestamp;

public class BomItem {
    private int ID;
    private int requestID;
    private int materialID;
    private int amount ;
    private String name;
    private int length;
    private String unit;
    private String description;
    private int categoryID;
    private String categoryName;

    public BomItem(int ID, int requestID, int materialID, int amount, String name, String unit, String description, int categoryID, String categoryName) {
        this.ID = ID;
        this.requestID = requestID;
        this.materialID = materialID;
        this.amount = amount;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

