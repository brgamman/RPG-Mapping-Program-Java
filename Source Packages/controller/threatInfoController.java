package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Map;
import model.TargetSelector;
import java.io.IOException;

/**
 * This is the controller class for the window in which information used to assess the player's vulnerabilities is entered.
 * @author Benjamin Gamman
 */
public class threatInfoController {
    @FXML
    private AnchorPane threatInfoAnchor;
    @FXML
    private Button returnButton;
    @FXML
    private Button findVulnerabilitiesButton;
    @FXML
    private ComboBox<String> hostileFaction;
    @FXML
    private RadioButton threatSpecified;
    @FXML
    private RadioButton threatGeneral;
    @FXML
    private ToggleGroup threatType;

    /**
     * Sets the options for hostile factions to be selected when the window is initialized.
     */
    public void initialize() {
        ObservableList<String> nonPlayerFactions = FXCollections.observableArrayList();
        for (String faction : Map.getFactionNames()) {
            if (!faction.equals(Map.getPlayerFaction())) {
                nonPlayerFactions.add(faction);
            }
        }
        hostileFaction.setItems(nonPlayerFactions);
    }

    /**
     * When the calculate button is clicked, submits information then closes the current window and displays the results.
     */
    public void findVulnerabilitiesButtonListener() throws IOException {
        if (threatGeneral.isSelected()) {
            TargetSelector.selectTargets("", Map.getPlayerFaction(), false, false, false, false, false, false, false);
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/threatDisplay.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Detected Vulnerabilities");
            stage.setScene(scene);
            stage.show();
            Stage prev = (Stage) threatInfoAnchor.getScene().getWindow();
            prev.close();
        }
        else if (threatSpecified.isSelected()) {
            if (hostileFaction.getSelectionModel().getSelectedItem() != null) {
                TargetSelector.selectTargets(hostileFaction.getSelectionModel().getSelectedItem(), Map.getPlayerFaction(), false, false, false, false, false, false, false);
                Stage stage = new Stage();
                Parent parent = FXMLLoader.load(getClass().getResource("/view/threatDisplay.fxml"));
                Scene scene = new Scene(parent);
                stage.setTitle("Detected Vulnerabilities");
                stage.setScene(scene);
                stage.show();
                Stage prev = (Stage) threatInfoAnchor.getScene().getWindow();
                prev.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a hostile faction to continue.");
                alert.showAndWait();
            }
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
        Stage prev = (Stage) threatInfoAnchor.getScene().getWindow();
        prev.close();
    }

}