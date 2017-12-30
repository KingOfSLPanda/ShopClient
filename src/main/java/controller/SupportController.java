package main.java.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by User on 25.11.2017.
 */
public class SupportController {

    public void changeWindow(Button button, String string) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource(string));
        Scene scene = new Scene(root);
//        if(size.equals("full")){
        stage.setResizable(true);
//        stage.setFullScreen(true);
//        }
        stage.setScene(scene);
        stage.show();
    }

    public void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
