package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Planet;
import model.TargetSelector;
import java.io.IOException;

/**
 * This is the controller class for the window in which prioritized targets are displayed.
 * @author Benjamin Gamman
 */
public class targetDisplayController {
    @FXML
    private AnchorPane targetDisplayAnchor;
    @FXML
    private Button returnButton;
    @FXML
    private Button findTargetButton;
    @FXML
    private Button chartButton;
    @FXML
    private TableView<Planet> targetTable;
    @FXML
    private TableColumn<Planet, Integer> targetRank;
    @FXML
    private TableColumn<Planet, String> targetPlanet;
    @FXML
    private TableColumn<Planet, Integer> targetUniverse;
    @FXML
    private TableColumn<Planet, String> targetFaction;
    @FXML
    private TableColumn<Planet, Integer> targetConnectionsFriendly;
    @FXML
    private TableColumn<Planet, Integer> targetConnectionsHostile;
    @FXML
    private TableColumn<Planet, Integer> targetValue;
    @FXML
    private TableColumn<Planet, Integer> targetI;
    @FXML
    private TableColumn<Planet, Integer> targetT;
    @FXML
    private TableColumn<Planet, Integer> targetM;
    @FXML
    private TableColumn<Planet, Integer> targetC;
    @FXML
    private TableColumn<Planet, Integer> targetP;
    @FXML
    private TableColumn<Planet, Integer> targetR;
    @FXML
    private TableColumn<Planet, Integer> targetA;

    /**
     * Initializes the window by populating the table with ranked list of suggested target planets.
     */
    public void initialize() {
        targetRank.setCellValueFactory(new PropertyValueFactory<>("targetRank"));
        targetPlanet.setCellValueFactory(new PropertyValueFactory<>("name"));
        targetUniverse.setCellValueFactory(new PropertyValueFactory<>("universe"));
        targetFaction.setCellValueFactory(new PropertyValueFactory<>("faction"));
        targetConnectionsFriendly.setCellValueFactory(new PropertyValueFactory<>("playerConnections"));
        //Friendly and hostile connection values are switched because in this window the planets belong to a hostile faction,
        //so the connections "friendly" to the planet are hostile to the player.
        targetConnectionsHostile.setCellValueFactory(new PropertyValueFactory<>("friendlyConnections"));
        targetValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        targetI.setCellValueFactory(new PropertyValueFactory<>("I"));
        targetT.setCellValueFactory(new PropertyValueFactory<>("T"));
        targetM.setCellValueFactory(new PropertyValueFactory<>("M"));
        targetC.setCellValueFactory(new PropertyValueFactory<>("C"));
        targetP.setCellValueFactory(new PropertyValueFactory<>("P"));
        targetR.setCellValueFactory(new PropertyValueFactory<>("R"));
        targetA.setCellValueFactory(new PropertyValueFactory<>("A"));
        ObservableList<Planet> topTargets = FXCollections.observableArrayList();
        for (Planet target : TargetSelector.getPriorityTargets()) {
            if (target.getTargetRank()<=10) { topTargets.add(target); }
            if (target.getTargetRank()>10) { break; }
        }
        targetTable.setItems(topTargets);
        if (TargetSelector.getPriorityTargets().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No borders are shared with the selected faction.");
            alert.showAndWait();
        }
    }

    /**
     * When the calculate button is clicked, closes the current window and reopens the target info window.
     */
    public void findTargetButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/targetInfo.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Target Information");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) targetDisplayAnchor.getScene().getWindow();
        prev.close();
    }

    /**
     * When the chart button is clicked, opens a visualization of the data displayed.
     */
    public void chartButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/targetChart.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Target Priority");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * When the return button is clicked, closes the current window and opens the main menu.
     */
    public void returnButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) targetDisplayAnchor.getScene().getWindow();
        prev.close();
    }

}
