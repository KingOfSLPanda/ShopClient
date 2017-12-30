package main.java.model;

/**
 * Created by User on 04.12.2017.
 */
public class DescriptionOrder {

    private int id;

    private String userName;

    private String productName;

    public DescriptionOrder() {}

    public DescriptionOrder(int id, String userName, String productName) {
        this.id = id;
        this.userName = userName;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
