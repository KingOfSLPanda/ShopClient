package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.model.Category;
import main.java.model.CurrentUser;
import main.java.model.Product;
import main.java.service.CategoryService;
import main.java.service.MainService;
import main.java.service.OrderService;
import main.java.service.ProductService;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

/**
 * Created by User on 20.11.2017.
 */
public class MainController {

    private static int index = 0;

    private static Product currentProduct;

    @FXML
    public MenuBar menuAdmin;

    @FXML
    public Button buttonOrder;

    @FXML
    private Label labelProductTitle;

    @FXML
    private Label labelProductMaterial;

    @FXML
    private Label labelProductCount;

    @FXML
    private Label labelProductWidth;

    @FXML
    private Label labelProductHeight;

    @FXML
    private Label labelProductLength;

    @FXML
    private Label labelProductWeight;

    @FXML
    private Label labelProductDescription;

    @FXML
    private Label labelProductCost;

    @FXML
    private ImageView imageProductImg;


    @FXML
    private TableView tableCategory;

    @FXML
    private TableColumn<Category, String> columnCategory;

    @FXML
    private TableView tableProduct;

    @FXML
    private TableColumn<Product, String> columnTitle;

    @FXML
    private TableColumn<Product, String> columnImage;

    @FXML
    private TableColumn<Product, String> columnDescription;

    @FXML
    private TableColumn<Product, Integer> columnCost;

    @FXML
    private TableColumn<Product, Integer> columnCount;

    @FXML
    private Button buttonCurrentUser;

    @FXML
    private ImageView imageReklama;

    @FXML
    private Button buttonLogOut;

    @FXML
    public void  initialize() throws JSONException, IOException, ClassNotFoundException {
        columnCategory.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryName"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Product, String>("title"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Product, Integer>("cost"));
        columnCount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));
        buttonCurrentUser.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
//        Image imagef = new Image("file:D:\\img\\fon.jpg");
        ObservableList<Product> products = ProductService.getProducts();
        Image image = new Image("file:Default");
        System.out.println(CurrentUser.getUser().getRole());

        if (CurrentUser.getUser().getRole().equals("admin")){
            menuAdmin.setVisible(true);
        }
        else {
            menuAdmin.setVisible(false);
        }


        if(index==0 && products.size()!=0){
            index = products.get(0).getId();
        }
        for(int i=0; i <products.size(); i++){
            if(products.get(i).getId() == index){
                image = new Image(products.get(i).getImg());
                labelProductTitle.setText(products.get(i).getTitle());
                labelProductCost.setText(String.valueOf(products.get(i).getCost()));
                labelProductMaterial.setText(products.get(i).getMaterial());
                labelProductCount.setText(String.valueOf(products.get(i).getCount()));
                labelProductHeight.setText(String.valueOf(products.get(i).getHeight()));
                labelProductWidth.setText(String.valueOf(products.get(i).getWidth()));
                labelProductLength.setText(String.valueOf(products.get(i).getLength()));
                labelProductWeight.setText(String.valueOf(products.get(i).getWeight()));
                labelProductDescription.setText(products.get(i).getDescription());
                currentProduct = products.get(i);
                i=products.size();
            }
        }
        imageReklama.setImage(new Image("file:D:\\img\\shop\\vulkan.jpg"));
        imageProductImg.setImage(image);
        tableProduct.setItems(products);
        tableCategory.setItems(CategoryService.getCategories());
        initListeners();
    }

    private void initListeners() throws JSONException, IOException, ClassNotFoundException {
        tableProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
//                    (new SupportController()).showMessage("Choose prod");
                    index = ((Product)tableProduct.getSelectionModel().getSelectedItem()).getId();
                    try {
                        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/main.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tableCategory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    int categoryId = ((Category) tableCategory.getSelectionModel().getSelectedItem()).getId();
                    try {
                        tableProduct.setItems(ProductService.getProductsByCategoryId(categoryId));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void showUserInfo() throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/userInfo.fxml");
    }

    @FXML
    public void logOut() throws IOException {
        (new SupportController()).changeWindow(buttonLogOut, "../../view/login.fxml");
    }

    public void showAllUsers(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/users.fxml");
    }

    public void showAllCategories(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/categories.fxml");
    }

    public void showAllProducts(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/products.fxml");
    }

    public void showStat(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/statistics.fxml");
    }

    public void newOrder(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        (new OrderService()).newOrder(currentProduct);

    }

    public void showOrders(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/orders.fxml");
    }

    public void writeReport(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        MainService.writeReport();
    }

    public void sendShares(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        MainService.sendShares();
    }

}
