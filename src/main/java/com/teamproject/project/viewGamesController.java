package com.teamproject.project;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class viewGamesController implements Initializable{
    @FXML private TableView<Game> gamesTable;

    @FXML private TableColumn<Game, String> idCol;
    @FXML private TableColumn<Game, String> titleCol;
    @FXML private TableColumn<Game, Integer> yearCol;
    @FXML private TableColumn<Game, Integer> minCol;
    @FXML private TableColumn<Game, Integer> maxCol;
    @FXML private TableColumn<Game, Integer> minutesCol;
    @FXML private TableColumn<Game, Double> complexityCol;
    @FXML private TableColumn<Game, String> designerCol;

    private ObservableList<Game> gamesList = FXCollections.observableArrayList();
    ResourceBundle bundle = ResourceBundle.getBundle("i18n.texts", Locale.getDefault());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        minCol.setCellValueFactory(new PropertyValueFactory<>("minPlayers"));
        maxCol.setCellValueFactory(new PropertyValueFactory<>("maxPlayers"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<>("minPlayed"));
        complexityCol.setCellValueFactory(new PropertyValueFactory<>("complexity"));
        designerCol.setCellValueFactory(new PropertyValueFactory<>("userName"));

        loadGamesFromDatabase();
    }

    @FXML
    public void modifyGame(){
        Game selected = gamesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("No game selected.");
            return;
        }else{
            modifyGameController.game = selected;
            openWindow("modifyGame.fxml", bundle.getString("modifyGame.fxml"));
            Game updated = modifyGameController.game;

            String sql = "UPDATE games SET title=?, year_published=?, min_players=?, " +
                    "max_players=?, playing_time_min=?, complexity=?, designer_name=? " +
                    "WHERE game_id=?";

            try (Connection con = new Database().getConnection();
                 PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, updated.getTitle());
                stmt.setInt(2, updated.getYearPublished());
                stmt.setInt(3, updated.getMinPlayers());
                stmt.setInt(4, updated.getMaxPlayers());
                stmt.setInt(5, updated.getMinPlayed());
                stmt.setDouble(6, updated.getComplexity());
                stmt.setString(7, updated.getUserName());
                stmt.setInt(8, updated.getId());

                stmt.executeUpdate();
                System.out.println("Game updated in DB!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            gamesTable.refresh();
        }
    }

    private void loadGamesFromDatabase() {
        String sql = "SELECT * FROM games";

        try (Connection con = new Database().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            gamesList.clear();

            while (rs.next()) {
                gamesList.add(new Game(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getInt("year_published"),
                        rs.getInt("min_players"),
                        rs.getInt("max_players"),
                        rs.getInt("playing_time_min"),
                        rs.getDouble("complexity"),
                        rs.getString("designer_name")
                ));
            }

            gamesTable.setItems(gamesList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            stage.initOwner(gamesTable.getScene().getWindow());

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
