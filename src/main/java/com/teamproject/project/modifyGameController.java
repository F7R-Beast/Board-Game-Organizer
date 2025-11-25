package com.teamproject.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class modifyGameController implements Initializable {
    static Game game;

    @FXML
    private TextField idField;
    @FXML
    private TextField gameNameField;
    @FXML
    private TextField yearPublishedField;
    @FXML
    private TextField minPlayersField;
    @FXML
    private TextField maxPlayersField;
    @FXML
    private TextField minutesPlayedField;
    @FXML
    private TextField gameComplexityField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField addMinutesField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.setPromptText(String.valueOf(game.getId()));
        gameNameField.setText(game.getTitle());
        yearPublishedField.setText(String.valueOf(game.getYearPublished()));
        minPlayersField.setText(String.valueOf(game.getMinPlayers()));
        maxPlayersField.setText(String.valueOf(game.getMaxPlayers()));
        minutesPlayedField.setText(String.valueOf(game.getMinPlayed()));
        gameComplexityField.setText(String.valueOf(game.getComplexity()));
        userNameField.setText(game.getUserName());
    }

    @FXML
    private void handleApplyAction() {
        if(!gameNameField.getText().isBlank() && !gameNameField.getText().equals(game.getTitle())) {
            game.setTitle(gameNameField.getText());
        }

        if (!yearPublishedField.getText().isBlank()) {
            int newYear = Integer.parseInt(yearPublishedField.getText());
            if (newYear != game.getYearPublished()) {
                game.setYearPublished(newYear);
            }
        }else if(yearPublishedField.getText().isBlank()){
            game.setYearPublished(LocalDate.now().getYear());
        }

        if (!minPlayersField.getText().isBlank()) {
            int newMin = Integer.parseInt(minPlayersField.getText());
            if (newMin != game.getMinPlayers()) {
                game.setMinPlayers(newMin);
            }
        }

        if (!maxPlayersField.getText().isBlank()) {
            int newMax = Integer.parseInt(maxPlayersField.getText());
            if (newMax != game.getMaxPlayers()) {
                game.setMaxPlayers(newMax);
            }
        }

        if (!minutesPlayedField.getText().isBlank()) {
            int newMinutes = Integer.parseInt(minutesPlayedField.getText());
            if (newMinutes != game.getMinPlayed()) {
                game.setMinPlayed(newMinutes);
            }
        }

        if (!gameComplexityField.getText().isBlank()) {
            double newComplexity = Double.parseDouble(gameComplexityField.getText());
            if (newComplexity != game.getComplexity()) {
                game.setComplexity(newComplexity);
            }
        }

        if (!userNameField.getText().isBlank() && !userNameField.getText().equals(game.getUserName())) {
            game.setUserName(userNameField.getText());
        }
    }

    @FXML
    private void addMinutes() {
        if (!minutesPlayedField.getText().isBlank()) {
            game.addMinPlayed(Integer.parseInt(addMinutesField.getText()));
            minutesPlayedField.setText(String.valueOf(game.getMinPlayed()));
        }
    }
}
