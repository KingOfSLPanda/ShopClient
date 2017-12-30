package main.java.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.controller.SupportController;
import main.java.model.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04.12.2017.
 */
public class OrderService {

    public static String acceptOrder(int id) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "acceptOrder");
        object.put("idOrder", id);
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        if (request.equals("true")){
            (new SupportController()).showMessage("Заявка принята.");
        }
        else {
            (new SupportController()).showMessage("Произошла ошибка.");
        }
        return "../../view/orders.fxml";
    }

    public static String cancleOrder(int id) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "cancleOrder");
        object.put("idOrder", id);
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        if (request.equals("true")){
            (new SupportController()).showMessage("Заявка отклонена.");
        }
        else {
            (new SupportController()).showMessage("Произошла ошибка.");
        }
        return "../../view/orders.fxml";
    }

    public String newOrder(Product product) throws JSONException, IOException, ClassNotFoundException {
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(0, CurrentUser.getUser().getId(), product.getId()));
        JSONObject object = convertListOfOrdersToJSON(orders);
        object.put("sizeOrder", orders.size());
        object.put("action", "addOrder");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        if (request.equals("true")){
            (new SupportController()).showMessage("Заявка успешно оформлена.");
        }
        else {
            (new SupportController()).showMessage("Упс. Произошла ошибка.");
        }
        return "../../view/main.fxml";
    }

    public static ObservableList<Order> getOrders() throws IOException, JSONException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getOrders");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        List<Order> orders = getListOfOrders(request);
        return FXCollections.observableArrayList(orders);
    }

    public static ObservableList<DescriptionOrder> getDescriptionOrders() throws IOException, JSONException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getDescriptionOrders");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        List<DescriptionOrder> orders = getListOfDescriptionOrders(request);
        return FXCollections.observableArrayList(orders);
    }

    public static List<DescriptionOrder> getListOfDescriptionOrders(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        List<DescriptionOrder> orders = new ArrayList<DescriptionOrder>();
        for (int i = 0; i < (int) object.get("sizeDescriptionOrder"); i++){
            DescriptionOrder order = new DescriptionOrder();
            order.setId((int) object.get("idDescriptionOrder" + String.valueOf(i)));
            order.setUserName((String) object.get("userNameDescriptionOrder" + String.valueOf(i)));
            order.setProductName((String) object.get("productNameDescriptionOrder" + String.valueOf(i)));
            orders.add(order);
        }
        return orders;
    }

    public static JSONObject convertDescriptionOrdersToJSON(List<DescriptionOrder> orders) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("sizeDescriptionOrder", orders.size());
        for (int i = 0; i < orders.size(); i++){
            object.put("idDescriptionOrder" + String.valueOf(i), orders.get(i).getId());
            object.put("userNameDescriptionOrder" + String.valueOf(i), orders.get(i).getUserName());
            object.put("productNameDescriptionOrder" + String.valueOf(i), orders.get(i).getProductName());
        }
        return object;
    }

    public static List<Order> getListOfOrders(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        List<Order> orders = new ArrayList<Order>();
        for (int i = 0; i < (int) object.get("sizeOrder"); i++){
            Order order = new Order();
            order.setId((int) object.get("idOrder" + String.valueOf(i)));
            order.setUserId((int) object.get("userIdOrder" + String.valueOf(i)));
            order.setProductId((int) object.get("productIdOrder" + String.valueOf(i)));
            orders.add(order);
        }
        return orders;
    }

    public static JSONObject convertListOfOrdersToJSON(List<Order> orders) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("sizeOrder", orders.size());
        for (int i = 0; i < orders.size(); i++){
            object.put("idOrder" + String.valueOf(i), orders.get(i).getId());
            object.put("userIdOrder" + String.valueOf(i), orders.get(i).getUserId());
            object.put("productIdOrder" + String.valueOf(i), orders.get(i).getProductId());
        }
        return object;
    }
}
