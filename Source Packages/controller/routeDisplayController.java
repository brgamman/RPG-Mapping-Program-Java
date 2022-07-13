package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Planet;
import model.RouteCalculator;
import java.io.IOException;

/**
 * This is the controller class for the window in which the calculated route is displayed.
 * @author Benjamin Gamman
 */
public class routeDisplayController {
    @FXML
    private AnchorPane routeDisplayAnchor;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Planet> routeTable;
    @FXML
    private TableColumn<Planet, Integer> routeStop;
    @FXML
    private TableColumn<Planet, String> routePlanet;
    @FXML
    private TableColumn<Planet, Integer> routeUniverse;
    @FXML
    private TableColumn<Planet, String> routeFaction;
    @FXML
    private Button findRouteButton;

    /**
     * Initializes the window by populating the table with all stops along the route.
     */
    public void initialize() {
        routeStop.setCellValueFactory(new PropertyValueFactory<>("stop"));
        routePlanet.setCellValueFactory(new PropertyValueFactory<>("name"));
        routeUniverse.setCellValueFactory(new PropertyValueFactory<>("universe"));
        routeFaction.setCellValueFactory(new PropertyValueFactory<>("faction"));
        routeTable.setItems(RouteCalculator.getRoute());
    }

    /**
     * When the new route button is clicked, closes the current window and reopens the route info window.
     */
    public void findRouteButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/routeInfo.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Route Information");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) routeDisplayAnchor.getScene().getWindow();
        prev.close();
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
        Stage prev = (Stage) routeDisplayAnchor.getScene().getWindow();
        prev.close();
    }

}
