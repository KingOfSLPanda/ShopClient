package main.java.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.java.model.*;
import main.java.service.OrderService;
import main.java.service.ProductService;
import main.java.service.UserService;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by User on 04.12.2017.
 */
public class OrdersController {

    static int index=0;

    @FXML
    public TableView tableOrders;

    @FXML
    public TableColumn columnProductName;

    @FXML
    public TableColumn columnUserName;

    @FXML
    public TextField textProductName;

    @FXML
    public TextField textUserName;

    @FXML
    public Button buttonAccept;

    @FXML
    public Button buttonCancle;

    @FXML
    public Button buttonCurrentUser;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonLogOut;

    @FXML
    public void  initialize() throws IOException, JSONException, ClassNotFoundException {
        buttonCurrentUser.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
//        ObservableList<User> users = (new UserService()).getAllUsers();
//        ObservableList<Product> products = ProductService.getProducts();
//        ObservableList<Order> orders = OrderService.getOrders();
        ObservableList<DescriptionOrder> descriptionOrders = OrderService.getDescriptionOrders();
        columnUserName.setCellValueFactory(new PropertyValueFactory<DescriptionOrder, String>("userName"));
        columnProductName.setCellValueFactory(new PropertyValueFactory<DescriptionOrder, String>("productName"));
        if(index==0 && descriptionOrders.size()!=0){
            index = descriptionOrders.get(0).getId();
        }
        for (int i=0; i<descriptionOrders.size(); i++){
            if(descriptionOrders.get(i).getId() == index) {
                textUserName.setText(descriptionOrders.get(i).getUserName());
                textProductName.setText(descriptionOrders.get(i).getProductName());
                i=descriptionOrders.size();
            }
        }
        textUserName.setDisable(true);
        textProductName.setDisable(true);
        tableOrders.setItems(descriptionOrders);
        initListeners();
    }

    private void initListeners() throws JSONException, IOException, ClassNotFoundException {
        tableOrders.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
//                    (new SupportController()).showMessage("Choose prod");
                    index = ((DescriptionOrder)tableOrders.getSelectionModel().getSelectedItem()).getId();
                    try {
                        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/orders.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void showStat(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/statistics.fxml");
    }

    public void showOrders(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/orders.fxml");
    }

    public void acceptOrder(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        String adress = OrderService.acceptOrder(index);
        (new SupportController()).changeWindow(buttonAccept, adress);
    }

    public void cancleOrder(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        String adress = OrderService.cancleOrder(index);
        (new SupportController()).changeWindow(buttonAccept, adress);
    }

    public void showUserInfo(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/userInfo.fxml");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/main.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonLogOut, "../../view/login.fxml");
    }
}
