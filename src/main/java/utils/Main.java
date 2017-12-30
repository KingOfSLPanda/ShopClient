package main.java.utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controller.MainController;
import main.java.service.ConnectionService;
import org.json.JSONException;

import java.io.IOException;

public class Main extends Application {


    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException, InterruptedException {

        ConnectionService.connect();
        System.out.println("connection established....");
        launch(args);
        System.out.println("End.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../view/login.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 600, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
