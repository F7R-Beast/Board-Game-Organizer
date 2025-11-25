package com.teamproject.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class createGameController {
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
    private void registerGameAction() {
        String gameName = gameNameField.getText();
        String yearStr = yearPublishedField.getText();
        String minPlayersStr = minPlayersField.getText();
        String maxPlayersStr = maxPlayersField.getText();
        String minutesStr = minutesPlayedField.getText();
        String complexityStr = gameComplexityField.getText();
        String userName = userNameField.getText();

        // check empty fields
        if (gameName.isEmpty() || minPlayersStr.isEmpty() ||
                maxPlayersStr.isEmpty() || minutesStr.isEmpty() || complexityStr.isEmpty() ||
                userName.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Unable to Register Game");
            alert.setContentText("Please fill all the fields");
            alert.setTitle("Important Information");

            // highlight empty fields
            gameNameField.setStyle(gameName.isEmpty() ? "-fx-border-color: red;" : "-fx-border-color: green;");
            minPlayersField.setStyle(minPlayersStr.isEmpty() ? "-fx-border-color: red;" : "-fx-border-color: green;");
            maxPlayersField.setStyle(maxPlayersStr.isEmpty() ? "-fx-border-color: red;" : "-fx-border-color: green;");
            minutesPlayedField.setStyle(minutesStr.isEmpty() ? "-fx-border-color: red;" : "-fx-border-color: green;");
            gameComplexityField.setStyle(complexityStr.isEmpty() ? "-fx-border-color: red;" : "-fx-border-color: green;");
            userNameField.setStyle(userName.isEmpty() ? "-fx-border-color: red;" : "-fx-border-color: green;");

            alert.showAndWait();
            return;
        }

        try {
            // parse numeric fields
            int yearPublished = (yearStr.isEmpty()? LocalDate.now().getYear() : Integer.parseInt(yearStr));
            int minPlayers = Integer.parseInt(minPlayersStr);
            int maxPlayers = Integer.parseInt(maxPlayersStr);
            int minutesPlayed = Integer.parseInt(minutesStr);
            double gameComplexity = Double.parseDouble(complexityStr);

            // create game object
            Game game = new Game(gameName, yearPublished, minPlayers, maxPlayers, minutesPlayed, gameComplexity);

            // insert into database
            Database db = new Database();
            Connection con = db.getConnection();

            String sql = "INSERT INTO Games (title, year_published, min_players, max_players, playing_time_min, complexity, designer_name) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            var pstmt = con.prepareStatement(sql);
            pstmt.setString(1, gameName);
            pstmt.setInt(2, yearPublished);
            pstmt.setInt(3, minPlayers);
            pstmt.setInt(4, maxPlayers);
            pstmt.setInt(5, minutesPlayed);
            pstmt.setDouble(6, gameComplexity);
            pstmt.setString(7, userName);

            pstmt.executeUpdate();
            pstmt.close();
            con.close();

            // reset fields
            gameNameField.setText("");
            gameNameField.setStyle("-fx-border-color: transparent;");
            yearPublishedField.setText("");
            yearPublishedField.setStyle("-fx-border-color: transparent;");
            minPlayersField.setText("");
            minPlayersField.setStyle("-fx-border-color: transparent;");
            maxPlayersField.setText("");
            maxPlayersField.setStyle("-fx-border-color: transparent;");
            minutesPlayedField.setText("");
            minutesPlayedField.setStyle("-fx-border-color: transparent;");
            gameComplexityField.setText("");
            gameComplexityField.setStyle("-fx-border-color: transparent;");
            userNameField.setText("");
            userNameField.setStyle("-fx-border-color: transparent;");

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText("Game Registered");
            success.setContentText("The game was successfully added to the database.");
            success.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter valid numbers for year, players, minutes, and complexity.");
            alert.setTitle("Error");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Database Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}