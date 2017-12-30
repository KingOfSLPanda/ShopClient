package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.service.MainService;
import main.java.service.MessageTransmissionService;
import main.java.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginController {

    private SupportController supportController = new SupportController();
    private MainService mainService = new MainService();

    @FXML
    private TextField textUserName;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    public void signIn(ActionEvent actionEvent) throws JSONException, IOException, ClassNotFoundException {
        String adress = mainService.signIn(textUserName.getText(), textPassword.getText());
        supportController.changeWindow(buttonSignIn, adress);
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        supportController.changeWindow(buttonSignUp, "../../view/registration.fxml");
    }

//    public void userSave(ActionEvent actionEvent) throws IOException, JSONException, ClassNotFoundException {
//        JSONObject jsonObject = (new UserService()).convertUserFieldToJSONobject(
//                textUserName.getText(),
//                textPassword.getText(),
//                textFirstName.getText(),
//                textLastName.getText(),
//                textEmail.getText(),
//                textAge.getText(),
//                textAdress.getText(),
//                comboBoxGender.getSelectionModel().getSelectedItem().toString());
//        String adress =  mainService.registration(jsonObject);
//        supportController.changeWindow(buttonSave,adress);
//    }
//
//    public void registrationCancle(ActionEvent actionEvent) throws IOException {
//        supportController.changeWindow(buttonCancle, "../../view/login.fxml");
//    }
}
