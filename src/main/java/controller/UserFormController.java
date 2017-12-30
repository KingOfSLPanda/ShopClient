package main.java.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.model.Const;
import main.java.model.User;
import main.java.service.MainService;
import main.java.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by User on 28.11.2017.
 */
public class UserFormController {

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
    public Button buttonBack;

    @FXML
    public Button buttonLogOut;

    @FXML
    public ComboBox comboBoxGender;

    @FXML
    public ComboBox comboBoxRole;

    @FXML
    public Label labelUserId;

    @FXML
    public Label labelUId;

    @FXML
    public void initialize(){
        comboBoxGender.setItems(FXCollections.observableArrayList(Const.GENDER));
        comboBoxRole.setItems(FXCollections.observableArrayList(Const.ROLE));
        labelId.setVisible(false);
        labelUId.setVisible(false);
        labelUserId.setVisible(false);

    }

    public void saveUser(ActionEvent actionEvent) throws JSONException, IOException, ClassNotFoundException {
        User newUser = new User(
                0,
                textUserName.getText(),
                textPassword.getText(),
                textFirstName.getText(),
                textLastName.getText(),
                textEmail.getText(),
                comboBoxRole.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(textAge.getText()),
                textAdress.getText(),
                comboBoxGender.getSelectionModel().getSelectedItem().toString());
        JSONObject jsonObject = (new UserService()).convertUserToJSONobject(newUser);
        String adress =  (new MainService()).addUser(jsonObject);
        (new SupportController()).changeWindow(buttonSave,adress);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/users.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonBack, "../../view/login.fxml");
    }
}
