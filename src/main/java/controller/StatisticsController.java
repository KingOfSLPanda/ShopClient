package main.java.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.model.CurrentUser;
import main.java.service.CategoryService;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by User on 04.12.2017.
 */
public class StatisticsController {

    @FXML
    public PieChart pieChartCategories;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonLogOut;

    @FXML
    public void  initialize() throws JSONException, IOException, ClassNotFoundException {
        ObservableList<PieChart.Data> data = CategoryService.getData(CategoryService.getCategories());
        pieChartCategories.setData(data);
        pieChartCategories.setTitle("Информация о категориях");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/main.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        CurrentUser.setUser(null);
        (new SupportController()).changeWindow(buttonLogOut, "../../view/login.fxml");
    }
}
