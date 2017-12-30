package main.java.model;

/**
 * Created by User on 25.11.2017.
 */
public class Category {

    private int id;

    private String categoryName;

    private int numberOfOrders;

    public Category(){}

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Category(int id, String categoryName, int numberOfOrders) {
        this.id = id;
        this.categoryName = categoryName;
        this.numberOfOrders = numberOfOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
