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
import main.java.model.Category;
import main.java.model.CurrentUser;
import main.java.service.CategoryService;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by User on 28.11.2017.
 */
public class CategoriesController {

    private static int index = 0;

    @FXML
    public TableView tableCategories;

    @FXML
    public TableColumn columnCategoryName;

    @FXML
    public TextField textCategoryName;

    @FXML
    public Button buttonCurrentUser;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonLogOut;

    @FXML
    public Button buttonDelete;

    @FXML
    public Button buttonSave;

    public void  initialize() throws JSONException, IOException, ClassNotFoundException {
        columnCategoryName.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryName"));
        buttonCurrentUser.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
        ObservableList<Category> categories = CategoryService.getCategories();
        if (index==0 && categories.size()!=0){
            index = categories.get(0).getId();
        }
        for (int i=0; i<categories.size();i++){
            if (categories.get(i).getId() == index){
                textCategoryName.setText(categories.get(i).getCategoryName());
            }
        }
        tableCategories.setItems(categories);
        initListeners();
    }

    private void initListeners(){
        tableCategories.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    index = ((Category)tableCategories.getSelectionModel().getSelectedItem()).getId();
                    try {
                        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/categories.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void showAllCategories(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/categories.fxml");
    }

    public void addCategory(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/categoryForm.fxml");
    }

    public void saveCategory(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        int numberOfOrders =  ((Category)tableCategories.getSelectionModel().getSelectedItem()).getNumberOfOrders();
        String adress = CategoryService.updateCategory(new Category(index,
                textCategoryName.getText(),
               numberOfOrders));
        (new SupportController()).changeWindow(buttonSave, adress);
    }

    public void deleteCategory(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
       String adress = CategoryService.deleteCategory(index);
        (new SupportController()).changeWindow(buttonDelete, adress);
    }

    public void showUserInfo(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/userInfo.fxml");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/main.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        CurrentUser.setUser(null);
        (new SupportController()).changeWindow(buttonLogOut, "../../view/login.fxml");
    }
}
