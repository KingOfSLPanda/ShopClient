package main.java.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.model.Category;
import main.java.model.CurrentUser;
import main.java.model.Product;
import main.java.service.CategoryService;
import main.java.service.MainService;
import main.java.service.ProductService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28.11.2017.
 */
public class ProductsController {

    static int index=0;

    @FXML
    public TableView tableProduct;

    @FXML
    public TableColumn columnTitle;

    @FXML
    public TableColumn columnImage;

    @FXML
    public TableColumn columnDescription;

    @FXML
    public TableColumn columnCost;

    @FXML
    public TableColumn columnCount;

    @FXML
    public ImageView imageProductImg;



    @FXML
    public Button buttonCurrentUser;

    @FXML
    public Button buttonLogOut;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonSave;

    @FXML
    public Button buttonDelete;

    @FXML
    public MenuBar menuAdmin;

    @FXML
    public TextField textProductTitle;

    @FXML
    public TextField textProductCost;

    @FXML
    public TextField textProductMaterial;

    @FXML
    public TextField textProductHeight;

    @FXML
    public TextField textProductWidth;

    @FXML
    public TextField textProductCount;

    @FXML
    public TextField textProductLength;

    @FXML
    public TextField textProductWeight;

    @FXML
    public TextArea textProductDescription;

    @FXML
    public ComboBox comboBoxCategory;

    @FXML
    public Label labelProductId;

    @FXML
    public void initialize() throws IOException, JSONException, ClassNotFoundException {
        buttonCurrentUser.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
        columnTitle.setCellValueFactory(new PropertyValueFactory<Product, String>("title"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Product, Integer>("cost"));
        columnCount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));
        ObservableList<Product> products = ProductService.getProducts();
        ObservableList<Category> categories = CategoryService.getCategories();
        Image image = new Image("file:Default");
        if(index==0 && products.size()!=0){
            index = products.get(0).getId();
        }
        for(int i=0; i <products.size(); i++){
            if(products.get(i).getId() == index){
                image = new Image(products.get(i).getImg());
                textProductTitle.setText(products.get(i).getTitle());
                textProductCost.setText(String.valueOf(products.get(i).getCost()));
                textProductMaterial.setText(products.get(i).getMaterial());
                textProductCount.setText(String.valueOf(products.get(i).getCount()));
                textProductHeight.setText(String.valueOf(products.get(i).getHeight()));
                textProductWidth.setText(String.valueOf(products.get(i).getWidth()));
                textProductLength.setText(String.valueOf(products.get(i).getLength()));
                textProductWeight.setText(String.valueOf(products.get(i).getWeight()));
                textProductDescription.setText(products.get(i).getDescription());
                labelProductId.setText(String.valueOf(products.get(i).getId()));
                for(int j=0;j<categories.size();j++){
                    if(products.get(i).getCategoryId() == categories.get(j).getId()){
                        comboBoxCategory.setValue(categories.get(j));
                    }
                }
                i=products.size();
            }
        }

        comboBoxCategory.setItems(categories);
//        comboBoxCategory.setSelectionModel();
        imageProductImg.setImage(image);
        tableProduct.setItems(products);
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
                        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/products.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void showAllProducts(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/products.fxml");
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/productForm.fxml");
    }

    public void deliteProductById(ActionEvent actionEvent) {
    }

    public void showUserInfo(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/userInfo.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonLogOut, "../../view/login.fxml");

    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/main.fxml");
    }

    public void saveProduct(ActionEvent actionEvent) throws JSONException, IOException, ClassNotFoundException {
        if(!MainService.isInt(textProductHeight.getText()) ||
                !MainService.isInt(textProductWidth.getText()) ||
                !MainService.isInt(textProductLength.getText()) ||
                !MainService.isInt(textProductWeight.getText()) ||
                !MainService.isInt(textProductCount.getText()) ||
                !MainService.isInt(textProductCost.getText())){
            return;
        }
        Product product = new Product(
                Integer.parseInt(labelProductId.getText()),
                "file:",
                textProductTitle.getText(),
                textProductMaterial.getText(),
                Integer.parseInt(textProductHeight.getText()),
                Integer.parseInt(textProductWidth.getText()),
                Integer.parseInt(textProductLength.getText()),
                Integer.parseInt(textProductWeight.getText()),
                textProductDescription.getText(),
                Integer.parseInt(textProductCount.getText()),
                Integer.parseInt(textProductCost.getText()),
                index
        );
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        JSONObject jsonObject = ProductService.convertProductsToJSONobject(products);
        ProductService.updateProduct(jsonObject);
    }

    public void deleteProduct(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
       String adress = ProductService.deleteProduct(Integer.parseInt(labelProductId.getText()));
       index = 0;
       (new SupportController()).changeWindow(buttonBack, adress);
    }
}
