package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.java.model.Const;
import main.java.model.CurrentUser;
import main.java.model.User;
import main.java.service.MainService;
import main.java.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by User on 27.11.2017.
 */
public class UsersController {

    @FXML
    public Label labelProductId;

    UserService userService = new UserService();

    private static int index = 0;

    @FXML
    public MenuBar menuAdmin;

    @FXML
    public TableView tableUsers;

//    @FXML
//    public TableColumn<User, Integer> columnId;

    @FXML
    public TableColumn<User, String> columnUserName;

    @FXML
    public TableColumn<User, String> columnFirstName;

    @FXML
    public TableColumn<User, String> columnLastName;

    @FXML
    public TableColumn<User, String> columnRole;

    @FXML
    public ComboBox comboBoxRole;

    @FXML
    public ComboBox comboBoxGender;

    @FXML
    public Label labelId;

    @FXML
    public TextField textUserName;

    @FXML
    public TextField textPassword;

    @FXML
    public TextField textFirstName;

    @FXML
    public TextField textAdress;

    @FXML
    public TextField textLastName;

    @FXML
    public TextField textEmail;

    @FXML
    public TextField textAge;

    @FXML
    public Button buttonSave;

    @FXML
    public Button buttonDelete;

    @FXML
    public Button buttonCurrentUser;

    @FXML
    public Button buttonLogOut;

    @FXML
    public Button buttonBack;

    @FXML
    public void initialize() throws IOException, JSONException, ClassNotFoundException {
        labelId.setVisible(false);
        labelProductId.setVisible(false);
//        columnId.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        columnUserName.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        columnRole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));

        buttonCurrentUser.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
        ObservableList<User> users = userService.getAllUsers();

        if(index == 0 && users.size()!=0){
            index = users.get(0).getId();
        }

        for (int i=0;i<users.size();i++){
            if(index == users.get(i).getId()){
                User user = users.get(i);
                labelId.setText(String.valueOf(user.getId()));
                textUserName.setText(user.getUsername());
                textPassword.setText(user.getPassword());
                textFirstName.setText(user.getFirstName());
                textLastName.setText(user.getLastName());
                textEmail.setText(user.getEmail());
                textAge.setText(String.valueOf(user.getAge()));
                textAdress.setText(user.getAdress());
                comboBoxGender.setValue(user.getGender());
                comboBoxRole.setValue(user.getRole());
            }
        }

        comboBoxGender.setItems(FXCollections.observableArrayList(Const.GENDER));
        comboBoxRole.setItems(FXCollections.observableArrayList(Const.ROLE));
        tableUsers.setItems(users);
        initListeners();
    }

    private void initListeners(){
        tableUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
//                    (new SupportController()).showMessage("Choose prod");
                    index = ((User)tableUsers.getSelectionModel().getSelectedItem()).getId();
                    try {
                        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/users.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void saveUser(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        if(!MainService.isInt(textAge.getText())){
            (new SupportController()).changeWindow(buttonSave,"../../view/users.fxml");
        }
        else {
            User newUser = new User(
                    Integer.parseInt(labelId.getText()),
                    textUserName.getText(),
                    textPassword.getText(),
                    textFirstName.getText(),
                    textLastName.getText(),
                    textEmail.getText(),
                    comboBoxRole.getSelectionModel().getSelectedItem().toString(),
                    Integer.parseInt(textAge.getText()),
                    textAdress.getText(),
                    comboBoxGender.getSelectionModel().getSelectedItem().toString());
            JSONObject jsonObject = userService.convertUserToJSONobject(newUser);
            String adress =  (new MainService()).updateUserByAdmin(jsonObject);
            (new SupportController()).changeWindow(buttonSave,adress);
        }
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        String adress = userService.deleteUser(Integer.parseInt(labelId.getText()));
        (new SupportController()).changeWindow(buttonDelete, adress);
    }

    public void showAllUsers(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCurrentUser, "../../view/users.fxml");
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

    public void addUser(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/userForm.fxml");
    }
}
