package com.teamproject.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class menuController {
    @FXML
    public Button addGameMenuButton;

    @FXML
    private void openAddNewGameWindow() {
        openWindow("addNewGame.fxml", "Add a New Game");
    }

    @FXML
    private void openViewGamesWindow() {
        openWindow("viewGames.fxml", "Game View Menu");
    }

    @FXML
    private void openDeleteGamesWindow() {
        openWindow("deleteGame.fxml", "Delete Games");
    }


    private void openWindow(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
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
}