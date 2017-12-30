package main.java.controller;



import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.java.model.Const;
import main.java.model.CurrentUser;
import main.java.service.MainService;
import main.java.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by User on 27.11.2017.
 */
public class UserInfoController {

    @FXML
    public ComboBox comboBoxGender;

    @FXML
    private TextField textUserName;

    @FXML
    private TextField textPassword;

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
    private Button buttonSave;

    @FXML
    private Button buttonCancle;

    @FXML
    private Menu menuAdmin;


    @FXML
    public void initialize(){
        System.out.println("show user");
        textUserName.setText(CurrentUser.getUser().getUsername());
        textPassword.setText(CurrentUser.getUser().getPassword());
        textFirstName.setText(CurrentUser.getUser().getFirstName());
        textLastName.setText(CurrentUser.getUser().getLastName());
        textEmail.setText(CurrentUser.getUser().getEmail());
        textAge.setText(String.valueOf(CurrentUser.getUser().getAge()));
        textAdress.setText(CurrentUser.getUser().getAdress());
        comboBoxGender.setValue(CurrentUser.getUser().getGender());
        comboBoxGender.setItems(FXCollections.observableArrayList(Const.GENDER));
    }

    @FXML
    public void userSave() throws JSONException, IOException, ClassNotFoundException {
        JSONObject jsonObject = (new UserService()).convertUserFieldToJSONobject(
                textUserName.getText(),
                textPassword.getText(),
                textFirstName.getText(),
                textLastName.getText(),
                textEmail.getText(),
                textAge.getText(),
                textAdress.getText(),
                comboBoxGender.getSelectionModel().getSelectedItem().toString());
        String adress =  (new MainService()).updateUser(jsonObject);
        (new SupportController()).changeWindow(buttonSave,adress);
    }

    public void registrationCancle(ActionEvent actionEvent) throws IOException {
        (new SupportController()).changeWindow(buttonCancle, "../../view/main.fxml");
    }
}
