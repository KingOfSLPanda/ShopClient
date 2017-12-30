package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.service.CategoryService;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by User on 03.12.2017.
 */
public class CategoryFormController {

    @FXML
    public TextField textCategoryName;

    @FXML
    public Button buttonSave;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonLogOut;



    public void saveCategory(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
       String adress = CategoryService.addCategory(textCategoryName.getText());
        (new SupportController()).changeWindow(buttonBack, adress);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/categories.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/login.fxml");
    }
}
