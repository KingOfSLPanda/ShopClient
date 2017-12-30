package main.java.model;


import javafx.scene.image.ImageView;

/**
 * Created by User on 25.11.2017.
 */
public class Product {

    private int id;

    private String img;

    private ImageView image;

    private String title;

    private String material;

    private int height;

    private int width;

    private int length;

    private int weight;

    private String description;

    private int count;

    private int cost;

    private int categoryId;

    public Product(){};

    public Product(int id, String img, String title, String material, int height, int width,
                   int length, int weight, String description, int count, int cost, int categoryId) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.material = material;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.description = description;
        this.count = count;
        this.cost = cost;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (height != product.height) return false;
        if (width != product.width) return false;
        if (length != product.length) return false;
        if (weight != product.weight) return false;
        if (count != product.count) return false;
        if (cost != product.cost) return false;
        if (categoryId != product.categoryId) return false;
        if (!title.equals(product.title)) return false;
        if (material != null ? !material.equals(product.material) : product.material != null) return false;
        return description != null ? description.equals(product.description) : product.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + length;
        result = 31 * result + weight;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + count;
        result = 31 * result + cost;
        result = 31 * result + categoryId;
        return result;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
