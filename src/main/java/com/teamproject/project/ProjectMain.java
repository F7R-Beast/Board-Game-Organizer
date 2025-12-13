package com.teamproject.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProjectMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.texts", Locale.getDefault());

        FXMLLoader fxmlLoader = new FXMLLoader(ProjectMain.class.getResource("menu.fxml"), bundle);
        Parent root = fxmlLoader.load();

        double width = root.prefWidth(-1);
        double height = root.prefHeight(-1);

        Scene scene = new Scene(root, width, height);
        stage.setTitle(bundle.getString("menu.fxml"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
       launch();
    }
}