package main.java.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.controller.MainController;
import main.java.controller.SupportController;
import main.java.model.Category;
import main.java.model.CurrentUser;
import main.java.model.Product;
import main.java.model.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by User on 21.11.2017.
 */
public class MainService {

    public static void getCategoriesAndProducts(ObservableList<Category> categories, ObservableList<Product> products) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getCategoriesAndProducts");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        categories = FXCollections.observableArrayList(CategoryService.getListOfCategoriesFromJSONobject(request));
        products = FXCollections.observableArrayList(ProductService.getProductsFromJSONobject(request));
    }

    public String addUser(JSONObject object) throws JSONException, IOException, ClassNotFoundException {
        if(!isInt(String.valueOf(object.get("age")))){
            return "../../view/users.fxml";
        }
        object.put("action", "signUp");
        MessageTransmissionService.sendMessage(object);
        if(!MessageTransmissionService.getMessage("request").equals("true")){
            (new SupportController()).showMessage("Данное имя уже занято.");
        }
        return "../../view/users.fxml";
    }

    public String registration(JSONObject object) throws JSONException, IOException, ClassNotFoundException {
        if(!isInt(String.valueOf(object.get("age")))){
            return "../../view/registration.fxml";
        }
        object.put("action", "signUp");
        MessageTransmissionService.sendMessage(object);
        if(MessageTransmissionService.getMessage("request").equals("true")){
            return "../../view/login.fxml";
        }
        else {
            (new SupportController()).showMessage("Данное имя уже занято.");
        }
        return "../../view/registration.fxml";
    }

    public static boolean isInt(String text){
        try {
            int number = Integer.parseInt(text);
            if(number<0){
                (new SupportController()).showMessage(text + " является отрицательным числом");
                return false;
            }
            return true;
        }catch (NumberFormatException e){
            (new SupportController()).showMessage(text + " не является числом");
            return false;
        }
    }

    public String updateUserByAdmin(JSONObject object) throws JSONException, IOException, ClassNotFoundException {
        object.put("action", "updateUser");
        MessageTransmissionService.sendMessage(object);
        if(!MessageTransmissionService.getMessage("request").equals("true")){
            (new SupportController()).showMessage("Данное имя уже занято.");
        }
        return "../../view/users.fxml";
    }

    public String updateUser(JSONObject object) throws JSONException, IOException, ClassNotFoundException {
        if(!isInt(String.valueOf(object.get("age")))){
            return "../../view/main.fxml";
        }
        object.put("action", "updateUser");
        object.put("id", String.valueOf(CurrentUser.getUser().getId()));
        MessageTransmissionService.sendMessage(object);
        if(MessageTransmissionService.getMessage("request").equals("true")){
            if(CurrentUser.getUser().getRole().equals("admin")){
                object.put("role", "admin");
            }
            CurrentUser.setUser((new UserService()).getUser(object));
            return "../../view/main.fxml";
        }
        else {
            (new SupportController()).showMessage("Данное имя уже занято.");
        }
        return "../../view/userInfo.fxml";
    }

    public String signIn(String username, String password) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "signIn");
        object.put("userName", username);
        object.put("password", password);
        MessageTransmissionService.sendMessage(object);
        JSONObject request = MessageTransmissionService.getMessage();
        if(request.get("request").equals("true")){
            CurrentUser.setUser((new UserService()).getUser(request));
            return "../../view/main.fxml";
        }
        else {
            (new SupportController()).showMessage("Логин или пароль введены неверно.");
        }
        return "../../view/login.fxml";
    }

    public static String writeReport() throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "writeReport");
        MessageTransmissionService.sendMessage(object);
//        JSONObject request = MessageTransmissionService.getMessage();
        return "../../view/main.fxml";
    }

    public static void sendShares() throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "sendShares");
        MessageTransmissionService.sendMessage(object);
        JSONObject request = MessageTransmissionService.getMessage();
        if(request.get("request").equals("true")){
            (new SupportController()).showMessage("Рассылка прошла успешно.");
        }
        else {
            (new SupportController()).showMessage("Упс. Что-то пошло не так. Попробуйте еще раз");
        }
    }

}
