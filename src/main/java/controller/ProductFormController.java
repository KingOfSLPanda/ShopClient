package main.java.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import main.java.model.Category;
import main.java.model.Product;
import main.java.service.CategoryService;
import main.java.service.MainService;
import main.java.service.ProductService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28.11.2017.
 */
public class ProductFormController {

    String url;

    @FXML
    public TextField textProductTitle;

    @FXML
    public TextField textProductCost;

    @FXML
    public ComboBox comboBoxProductCategory;

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
    public TextArea textAreaProductDescription;

    @FXML
    public Button buttonSave;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonLogOut;

    @FXML
    public ImageView imageProductImg;

    @FXML
    public void initialize() throws JSONException, IOException, ClassNotFoundException {
        ObservableList<Category> categories = CategoryService.getCategories();
        comboBoxProductCategory.setItems(categories);
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
                0,
                "file:" + url,
                textProductTitle.getText(),
                textProductMaterial.getText(),
                Integer.parseInt(textProductHeight.getText()),
                Integer.parseInt(textProductWidth.getText()),
                Integer.parseInt(textProductLength.getText()),
                Integer.parseInt(textProductWeight.getText()),
                textAreaProductDescription.getText(),
                Integer.parseInt(textProductCount.getText()),
                Integer.parseInt(textProductCost.getText()),
                ((Category)comboBoxProductCategory.getSelectionModel().getSelectedItem()).getId()
        );
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        JSONObject jsonObject = ProductService.convertProductsToJSONobject(products);
        ProductService.addProduct(jsonObject);
        (new SupportController()).changeWindow(buttonSave, "../../view/products.fxml");

    }

    public void handleDrugOver(DragEvent event) {
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image image = new Image(new FileInputStream(files.get(0)));
        url = files.get(0).getAbsolutePath();
        imageProductImg.setImage(image);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonLogOut, "../../view/login.fxml");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/products.fxml");
    }
}
