package business.entities;

public class Request {
    private int ID;
    private int userID;
    private String email;
    private String name;
    private String phone;
    private int statusID;
    private String statusName;
    private int width;
    private int length;
    private int roofId;
    private String roofName;
    private int slope;
    private int shedWidth;
    private int shedLength;
    private double price;

    public Request(int ID, int userID, String email, String name, String phone, int statusID, String statusName, int width, int length, int roofId,String roofName, int slope, int shedWidth, int shedLength, double price) {
        this.ID = ID;
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.statusID = statusID;
        this.statusName = statusName;
        this.width = width;
        this.length = length;
        this.roofId = roofId;
        this.roofName=roofName;
        this.slope = slope;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRoofId() {
        return roofId;
    }

    public void setRoofId(int roofId) {
        this.roofId = roofId;
    }

    public String getRoofName() {
        return roofName;
    }

    public void setRoofName(String roofName) {
        this.roofName = roofName;
    }

    public int getSlope() {
        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
