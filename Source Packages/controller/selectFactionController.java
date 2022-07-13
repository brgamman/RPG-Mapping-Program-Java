package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Map;
import java.io.IOException;

/**
 * This is the controller class for the preliminary window in which the player selects their faction.
 * @author Benjamin Gamman
 */
public class selectFactionController {
    @FXML
    private AnchorPane selectFactionAnchor;
    @FXML
    private Button continueButton;
    @FXML
    private ComboBox<String> selectFaction;

    /**
     * Populates the drop-down menu with all faction names.
     */
    public void initialize() {
        selectFaction.setItems(Map.getFactionNames());
    }

    /**
     * When the Continue button is clicked, closes the current window and opens the main menu.
     */
    public void continueButtonListener() throws IOException {
        if (selectFaction.getSelectionModel().getSelectedItem() != null) {
            Map.setPlayerFaction(selectFaction.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
            Stage prev = (Stage) selectFactionAnchor.getScene().getWindow();
            prev.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a faction to continue.");
            alert.showAndWait();
        }
    }
}
