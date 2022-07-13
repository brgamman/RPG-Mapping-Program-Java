package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Map;
import java.io.IOException;

/**
 * This is the controller class for the main window in which the player selects
 * which of the program's three main functions is to be used next.
 * @author Benjamin Gamman
 */
public class mainMenuController {
    @FXML
    private AnchorPane mainMenuAnchor;
    @FXML
    private Button targetButton;
    @FXML
    private Label factionLabel;
    @FXML
    private Button routeButton;
    @FXML
    private Button threatButton;
    @FXML
    private Button factionSelectButton;
    @FXML
    private Button chartButton;
    @FXML
    private Button exitButton;

    /**
     * Places the name of the player's faction in the window's text.
     */
    public void initialize() {
        factionLabel.setText(Map.getPlayerFaction()+".");
    }

    /**
     * When the button to return to faction selection is clicked, closes the current window
     * and reopens that window.
     */
    public void factionSelectButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/selectFaction.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Welcome to MAP+");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) mainMenuAnchor.getScene().getWindow();
        prev.close();
    }

    /**
     * When the route button is clicked, closes the current window and opens that window.
     */
    public void routeButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/routeInfo.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Route Information");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) mainMenuAnchor.getScene().getWindow();
        prev.close();
    }

    /**
     * When the target button is clicked, closes the current window and opens that window.
     */
    public void targetButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/targetInfo.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Target Information");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) mainMenuAnchor.getScene().getWindow();
        prev.close();
    }

    /**
     * When the threats button is clicked, closes the current window and opens that window.
     */
    public void threatButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/threatInfo.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Threat Information");
        stage.setScene(scene);
        stage.show();
        Stage prev = (Stage) mainMenuAnchor.getScene().getWindow();
        prev.close();
    }


    /**
     * When the chart button is clicked, opens visualizations of map data.
     */
    public void chartButtonListener() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/chartDisplay.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Map Data Charts");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exits the application when the Exit button is clicked.
     */
    public void exitButtonListener() {
        System.exit(0);
    }

}
