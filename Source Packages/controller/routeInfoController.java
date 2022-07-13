package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Map;
import model.Planet;
import model.RouteCalculator;
import java.io.IOException;

/**
 * This is the controller class for the window in which information used to calculate routes is entered.
 * @author Benjamin Gamman
 */
public class routeInfoController {
    @FXML
    private AnchorPane routeInfoAnchor;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Planet> startTable;
    @FXML
    private TableColumn<Planet, String> startPlanet;
    @FXML
    private TableColumn<Planet, Integer> startUniverse;
    @FXML
    private TableColumn<Planet, String> startFaction;
    @FXML
    private Button findRouteButton;
    @FXML
    private TableView<Planet> destTable;
    @FXML
    private TableColumn<Planet, String> destPlanet;
    @FXML
    private TableColumn<Planet, Integer> destUniverse;
    @FXML
    private TableColumn<Planet, String> destFaction;
    @FXML
    private TextField startSearch;
    @FXML
    private TextField destSearch;

    /**
     * Initializes the window by populating the tables with lists of all planets that may then be selected as starting point and destination.
     */
    public void initialize() {
        startPlanet.setCellValueFactory(new PropertyValueFactory<>("name"));
        startUniverse.setCellValueFactory(new PropertyValueFactory<>("universe"));
        startFaction.setCellValueFactory(new PropertyValueFactory<>("faction"));
        startTable.setItems(Map.getAllPlanets());
        destPlanet.setCellValueFactory(new PropertyValueFactory<>("name"));
        destUniverse.setCellValueFactory(new PropertyValueFactory<>("universe"));
        destFaction.setCellValueFactory(new PropertyValueFactory<>("faction"));
        destTable.setItems(Map.getAllPlanets());
    }

    /**
     * Filters the table from which start locations are selected by what is entered in the search box.
     */
    public void startSearchListener() {
        if (startSearch.getText() != null && !startSearch.getText().equals("")) {
            ObservableList<Planet> searchList = FXCollections.observableArrayList();
            for (Planet planet : Map.getAllPlanets()) {
                if (planet.getName().toLowerCase().contains(startSearch.getText().toLowerCase())) {
                    searchList.add(planet);
                }
            }
            startTable.setItems(searchList);
            if (searchList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No matching planet found.");
                alert.showAndWait();
            }
        }
        else { startTable.setItems(Map.getAllPlanets()); }
    }

    /**
     * Filters the table from which destination locations are selected by what is entered in the search box.
     */
    public void destSearchListener() {
        if (destSearch.getText() != null && !destSearch.getText().equals("")) {
            ObservableList<Planet> searchList = FXCollections.observableArrayList();
            for (Planet planet : Map.getAllPlanets()) {
                if (planet.getName().toLowerCase().contains(destSearch.getText().toLowerCase())) {
                    searchList.add(planet);
                }
            }
            destTable.setItems(searchList);
            if (searchList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No matching planet found.");
                alert.showAndWait();
            }
        }
        else { destTable.setItems(Map.getAllPlanets()); }
    }

    /**
     * When the calculate button is clicked, calculates the route then closes the current window and displays the results.
     */
    public void findRouteButtonListener() throws IOException {
        Planet start = startTable.getSelectionModel().getSelectedItem();
        Planet dest = destTable.getSelectionModel().getSelectedItem();
        if (start != null && dest != null) {
            RouteCalculator.calcRoute(start, dest);
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/routeDisplay.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Calculated Route");
            stage.setScene(scene);
            stage.show();
            Stage prev = (Stage) routeInfoAnchor.getScene().getWindow();
            prev.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select both a starting point and destination.");
            alert.showAndWait();
        }
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
        Stage prev = (Stage) routeInfoAnchor.getScene().getWindow();
        prev.close();
    }

}
