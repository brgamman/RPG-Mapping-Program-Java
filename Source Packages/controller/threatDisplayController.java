package controller;

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
 * This is the controller class for the window in which identified vulnerabilities are displayed and ranked.
 * @author Benjamin Gamman
 */
public class threatDisplayController {
    @FXML
    private AnchorPane threatDisplayAnchor;
    @FXML
    private Button returnButton;
    @FXML
    private Button findVulnerabilitiesButton;
    @FXML
    private Button chartButton;
    @FXML
    private TableView<Planet> threatTable;
    @FXML
    private TableColumn<Planet, Double> threatProbability;
    @FXML
    private TableColumn<Planet, String> threatPlanet;
    @FXML
    private TableColumn<Planet, Integer> threatUniverse;
    @FXML
    private TableColumn<Planet, Integer> threatConnectionsFriendly;
    @FXML
    private TableColumn<Planet, Integer> threatConnectionsHostile;
    @FXML
    private TableColumn<Planet, Integer> threatValue;
    @FXML
    private TableColumn<Planet, Integer> threatI;
    @FXML
    private TableColumn<Planet, Integer> threatT;
    @FXML
    private TableColumn<Planet, Integer> threatM;
    @FXML
    private TableColumn<Planet, Integer> threatC;
    @FXML
    private TableColumn<Planet, Integer> threatP;
    @FXML
    private TableColumn<Planet, Integer> threatR;
    @FXML
    private TableColumn<Planet, Integer> threatA;

    /**
     * Initializes the window by populating the table with list of planets vulnerable to attack.
     */
    public void initialize() {
        threatProbability.setCellValueFactory(new PropertyValueFactory<>("targetProbability"));
        threatPlanet.setCellValueFactory(new PropertyValueFactory<>("name"));
        threatUniverse.setCellValueFactory(new PropertyValueFactory<>("universe"));
        threatConnectionsFriendly.setCellValueFactory(new PropertyValueFactory<>("playerConnections"));
        threatConnectionsHostile.setCellValueFactory(new PropertyValueFactory<>("hostileConnections"));
        threatValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        threatI.setCellValueFactory(new PropertyValueFactory<>("I"));
        threatT.setCellValueFactory(new PropertyValueFactory<>("T"));
        threatM.setCellValueFactory(new PropertyValueFactory<>("M"));
        threatC.setCellValueFactory(new PropertyValueFactory<>("C"));
        threatP.setCellValueFactory(new PropertyValueFactory<>("P"));
        threatR.setCellValueFactory(new PropertyValueFactory<>("R"));
        threatA.setCellValueFactory(new PropertyValueFactory<>("A"));
        threatTable.setItems(TargetSelector.getPriorityTargets());
        if (TargetSelector.getPriorityTargets().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No borders are shared with the selected faction.");
            alert.showAndWait();
        }
    }

    /**
     * When the calculate button is clicked, closes the current window and reopens the threat info window.
     */
    public void findVulnerabilitiesButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/threatInfo.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Threat Information");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) threatDisplayAnchor.getScene().getWindow();
        prev.close();
    }

    /**
     * When the chart button is clicked, opens a visualization of the data displayed.
     */
    public void chartButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/threatChart.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Threat Probability");
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
        Stage prev = (Stage) threatDisplayAnchor.getScene().getWindow();
        prev.close();
    }

}
