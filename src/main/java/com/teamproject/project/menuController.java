package com.teamproject.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    @FXML
    public Button addGameMenuButton;
    public ChoiceBox<String> languageChoiceBox;
    String[] languages = {"English", "French"};

    ResourceBundle bundle = ResourceBundle.getBundle("i18n.texts", Locale.getDefault());

    @FXML
    private void openAddNewGameWindow() {
        openWindow("addNewGame.fxml", bundle.getString("addNewGame.fxml"));
    }

    @FXML
    private void openViewGamesWindow() {
        openWindow("viewGames.fxml", bundle.getString("viewGames.fxml"));
    }

    @FXML
    private void openDeleteGamesWindow() {
        openWindow("deleteGame.fxml", bundle.getString("deleteGame.fxml"));
    }

    private void changeLanguage(String language) {
        if (language.equals("English")) {
            Locale.setDefault(Locale.ENGLISH);
        }
        if (language.equals("French")) {
            Locale.setDefault(Locale.FRENCH);
        }
        reloadCurrentScene();
    }


    private void openWindow(String fxml, String title) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.texts", Locale.getDefault());

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml), bundle);
            Parent root = loader.load();

            double width = root.prefWidth(-1);
            double height = root.prefHeight(-1);


            Stage stage = new Stage();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addGameMenuButton.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.getDialogPane().setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageChoiceBox.getItems().addAll(languages);
        if(Locale.getDefault().equals(Locale.ENGLISH)) {
            languageChoiceBox.getSelectionModel().select("English");
        }else if(Locale.getDefault().equals(Locale.FRENCH)) {
            languageChoiceBox.getSelectionModel().select("French");
        }else{
            languageChoiceBox.getSelectionModel().selectFirst();
        }
        languageChoiceBox.setOnAction(this::getLanguage);
    }

    public void getLanguage(ActionEvent actionEvent) {
        String language = languageChoiceBox.getValue();
        changeLanguage(language);
    }

    private void reloadCurrentScene() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.texts", Locale.getDefault());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = (Stage) addGameMenuButton.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}