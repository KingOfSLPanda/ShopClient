package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.model.Const;
import main.java.service.MainService;
import main.java.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by User on 03.12.2017.
 */
public class RegistrationController {

    @FXML
    private TextField textUserName;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textFirstName;

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textAge;

    @FXML
    private TextField textAdress;

    @FXML
    ComboBox comboBoxGender;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancle;

    @FXML
    public void initialize() throws JSONException, IOException, ClassNotFoundException {
        ObservableList<String> genders = FXCollections.observableArrayList(Const.GENDER);
        comboBoxGender.setItems(genders);
    }

    public void userSave(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
        JSONObject jsonObject = (new UserService()).convertUserFieldToJSONobject(
                textUserName.getText(),
                textPassword.getText(),
                textFirstName.getText(),
                textLastName.getText(),
                textEmail.getText(),
                textAge.getText(),
                textAdress.getText(),
                comboBoxGender.getSelectionModel().getSelectedItem().toString());
        String adress =  (new MainService()).registration(jsonObject);
        (new SupportController()).changeWindow(buttonSave,adress);
    }

    public void registrationCancle(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCancle, "../../view/login.fxml");
    }

}
