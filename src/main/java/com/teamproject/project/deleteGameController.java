package com.teamproject.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class deleteGameController implements Initializable {
    @FXML
    private TableView<Game> gamesTable;

    @FXML private TableColumn<Game, String> idCol;
    @FXML private TableColumn<Game, String> titleCol;
    @FXML private TableColumn<Game, Integer> yearCol;
    @FXML private TableColumn<Game, Integer> minCol;
    @FXML private TableColumn<Game, Integer> maxCol;
    @FXML private TableColumn<Game, Integer> minutesCol;
    @FXML private TableColumn<Game, Double> complexityCol;
    @FXML private TableColumn<Game, String> designerCol;

    private ObservableList<Game> gamesList = FXCollections.observableArrayList();

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
    public void deleteGame() {
        Game selected = gamesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("No game selected.");
            return;
        } else {
            String deleteQuery = "DELETE FROM Games WHERE game_id = ?";
            try (Connection con = new Database().getConnection();
                 PreparedStatement stmt = con.prepareStatement(deleteQuery)) {

                stmt.setInt(1, selected.getId());

                stmt.executeUpdate();
                System.out.println("Game Deleted in DB!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            gamesTable.getItems().removeAll(gamesList);
            loadGamesFromDatabase();
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
}
