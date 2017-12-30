package main.java.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import main.java.controller.SupportController;
import main.java.model.Category;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.11.2017.
 */
public class CategoryService {

    public static String addCategory(String categoryName) throws JSONException, IOException, ClassNotFoundException {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category(0, categoryName, 0));
        JSONObject object =  convertListOfCategoriesToJSONobject(categories);
        object.put("sizeCategory", categories.size());
        object.put("action", "addCategory");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        if (request.equals("false")){
            (new SupportController()).showMessage("Данное категория уже сущетвует.");
        }
        return "../../view/categories.fxml";
    }

    public static String updateCategory(Category category) throws JSONException, IOException, ClassNotFoundException {
        List<Category> categories = new ArrayList<Category>();
//        categories.add(new Category(category.getId(), category.getCategoryName()));
        categories.add(category);
        JSONObject object =  convertListOfCategoriesToJSONobject(categories);
        object.put("sizeCategory", categories.size());
        object.put("action", "updateCategory");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        if (request.equals("false")){
            (new SupportController()).showMessage("Данное категория уже сущетвует.");
        }
        return "../../view/categories.fxml";
    }

    public static String deleteCategory(int id) throws JSONException, IOException, ClassNotFoundException {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category(id, "categoryName", 0));
        JSONObject object =  convertListOfCategoriesToJSONobject(categories);
        object.put("sizeCategory", categories.size());
        object.put("action", "deleteCategory");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        if (request.equals("false")){
            (new SupportController()).showMessage("Удалите товары из данной категории.");
        }
        return "../../view/categories.fxml";
    }

    public static ObservableList<Category> getCategories() throws IOException, JSONException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getCategories");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        List<Category> categories = getListOfCategoriesFromJSONobject(request);
        return FXCollections.observableArrayList(categories);
    }

    public static ObservableList<PieChart.Data> getData(ObservableList<Category> categories){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (int i=0; i<categories.size(); i++){
            data.add(new PieChart.Data(categories.get(i).getCategoryName(), categories.get(i).getNumberOfOrders()));
        }
        return data;
    }

    public static List<Category> getListOfCategoriesFromJSONobject(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        List<Category> categories = new ArrayList<Category>();
        for(int i = 0; i < (int)object.get("sizeCategory"); i++){
            Category category = new Category();
            category.setId((int) object.get("idCategory" + String.valueOf(i)));
            category.setCategoryName((String) object.get("categoryNameCategory" + String.valueOf(i)));
            category.setNumberOfOrders((int) object.get("numberOfOrdersCategory" + String.valueOf(i)));
            categories.add(category);
        }
        return  categories;
    }

    public static JSONObject convertListOfCategoriesToJSONobject(List<Category> categories) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("sizeCategory", categories.size());
        for (int i=0; i<categories.size(); i++){
            object.put("idCategory" + String.valueOf(i), categories.get(i).getId());
            object.put("categoryNameCategory" + String.valueOf(i), categories.get(i).getCategoryName());
            object.put("numberOfOrdersCategory" + String.valueOf(i), categories.get(i).getNumberOfOrders());
        }
        return object;
    }

}
