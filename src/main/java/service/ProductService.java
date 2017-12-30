package main.java.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.controller.SupportController;
import main.java.model.Product;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26.11.2017.
 */
public class ProductService {

    public static String deleteProduct(int id) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "deleteProduct");
        object.put("idProduct", String.valueOf(id));
        MessageTransmissionService.sendMessage(object);
        if(!MessageTransmissionService.getMessage("request").equals("true")){
            (new SupportController()).showMessage("Проверьте введённые данные.");
        }
        return "../../view/products.fxml";
    }

    public static String updateProduct(JSONObject object) throws JSONException, IOException, ClassNotFoundException {
        object.put("action", "updateProduct");
        MessageTransmissionService.sendMessage(object);
        System.out.println("sended message");
        if(!MessageTransmissionService.getMessage("request").equals("true")){
            (new SupportController()).showMessage("Проверьте введённые данные.");
        }
        return "../../view/products.fxml";
    }

    public static String addProduct(JSONObject object) throws JSONException, IOException, ClassNotFoundException {
        object.put("action", "addProduct");
        MessageTransmissionService.sendMessage(object);
        if(!MessageTransmissionService.getMessage("request").equals("true")){
            (new SupportController()).showMessage("Проверьте введённые данные.");
            return "../../view/productForm.fxml";
        }
        return "../../view/products.fxml";

    }

    public static ObservableList<Product> getProductsByCategoryId(int id) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getProductsByCategoryId");
        object.put("categoryId", id);
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        List<Product> products = getProductsFromJSONobject(request);
        return FXCollections.observableArrayList(products);
    }

    public static ObservableList<Product> getProducts() throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getProducts");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        List<Product> products = getProductsFromJSONobject(request);
        return FXCollections.observableArrayList(products);
    }

    public static JSONObject convertProductsToJSONobject(List<Product> products) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("sizeProduct", products.size());
        for (int i=0; i<products.size(); i++){
            object.put("idProduct" + String.valueOf(i), products.get(i).getId());
            object.put("imgProduct" + String.valueOf(i), products.get(i).getImg());
            object.put("titleProduct" + String.valueOf(i), products.get(i).getTitle());
            object.put("materialProduct" + String.valueOf(i), products.get(i).getMaterial());
            object.put("heightProduct" + String.valueOf(i), products.get(i).getHeight());
            object.put("widthProduct" + String.valueOf(i), products.get(i).getWidth());
            object.put("lengthProduct" + String.valueOf(i), products.get(i).getLength());
            object.put("weightProduct" + String.valueOf(i), products.get(i).getWeight());
            object.put("descriptionProduct" + String.valueOf(i), products.get(i).getDescription());
            object.put("countProduct" + String.valueOf(i), products.get(i).getCount());
            object.put("costProduct" + String.valueOf(i), products.get(i).getCost());
            object.put("categoryIdProduct" + String.valueOf(i), products.get(i).getCategoryId());
        }
        System.out.println("Successfull convert");
        return object;
    }

    public static List<Product> getProductsFromJSONobject(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        List<Product> products = new ArrayList<Product>();
        for(int i = 0; i < (int)object.get("sizeProduct"); i++){
            Product product = new Product();
            product.setId((int) object.get("idProduct" + String.valueOf(i)));
            product.setImg((String) object.get("imgProduct" + String.valueOf(i)));
            product.setTitle((String) object.get("titleProduct" + String.valueOf(i)));
            product.setMaterial((String) object.get("materialProduct" + String.valueOf(i)));
            product.setHeight((int) object.get("heightProduct" + String.valueOf(i)));
            product.setWidth((int) object.get("widthProduct" + String.valueOf(i)));
            product.setLength((int) object.get("lengthProduct" + String.valueOf(i)));
            product.setWeight((int) object.get("weightProduct" + String.valueOf(i)));
            product.setDescription((String) object.get("descriptionProduct" + String.valueOf(i)));
            product.setCount((int) object.get("countProduct" + String.valueOf(i)));
            product.setCost((int) object.get("costProduct" + String.valueOf(i)));
            product.setCategoryId((int) object.get("categoryIdProduct" + String.valueOf(i)));

            Image image = new Image(product.getImg());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            product.setImage(imageView);

            products.add(product);
        }
        return  products;
    }

}
