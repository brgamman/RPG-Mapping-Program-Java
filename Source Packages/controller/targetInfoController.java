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
 * This is the controller class for the window in which information used to select targets is entered.
 * @author Benjamin Gamman
 */
public class targetInfoController {
    @FXML
    private AnchorPane targetInfoAnchor;
    @FXML
    private Button returnButton;
    @FXML
    private Button findTargetsButton;
    @FXML
    private ComboBox<String> targetFaction;
    @FXML
    private RadioButton targetSpecified;
    @FXML
    private ToggleGroup targetType;
    @FXML
    private RadioButton targetGeneral;
    @FXML
    private RadioButton resourcesGeneral;
    @FXML
    private ToggleGroup resourcePrioritization;
    @FXML
    private RadioButton resourcesSpecified;
    @FXML
    private CheckBox boxI;
    @FXML
    private CheckBox boxT;
    @FXML
    private CheckBox boxM;
    @FXML
    private CheckBox boxC;
    @FXML
    private CheckBox boxP;
    @FXML
    private CheckBox boxR;
    @FXML
    private CheckBox boxA;

    /**
     * Sets the options for target factions to be selected when the window is initialized.
     */
    public void initialize() {
        ObservableList<String> nonPlayerFactions = FXCollections.observableArrayList();
        for (String faction : Map.getFactionNames()) {
            if (!faction.equals(Map.getPlayerFaction())) {
                nonPlayerFactions.add(faction);
            }
        }
        targetFaction.setItems(nonPlayerFactions);
    }

    /**
     * When the calculate button is clicked, submits entered information then closes the current window and displays the results.
     */
    public void findTargetsButtonListener() throws IOException {
        boolean I = false;
        boolean T = false;
        boolean M = false;
        boolean C = false;
        boolean P = false;
        boolean R = false;
        boolean A = false;
        if (resourcesSpecified.isSelected()) {
            if (boxI.isSelected()) { I=true; }
            if (boxT.isSelected()) { T=true; }
            if (boxM.isSelected()) { M=true; }
            if (boxC.isSelected()) { C=true; }
            if (boxP.isSelected()) { P=true; }
            if (boxR.isSelected()) { R=true; }
            if (boxA.isSelected()) { A=true; }
        }
        if (targetGeneral.isSelected()) {
            TargetSelector.selectTargets(Map.getPlayerFaction(), "", I, T, M, C, P, R, A);
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/targetDisplay.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Suggested Targets");
            stage.setScene(scene);
            stage.show();
            Stage prev = (Stage) targetInfoAnchor.getScene().getWindow();
            prev.close();
        }
        else if (targetSpecified.isSelected()) {
            if (targetFaction.getSelectionModel().getSelectedItem() != null) {
                TargetSelector.selectTargets(Map.getPlayerFaction(), targetFaction.getSelectionModel().getSelectedItem(), I, T, M, C, P, R, A);
                Stage stage = new Stage();
                Parent parent = FXMLLoader.load(getClass().getResource("/view/targetDisplay.fxml"));
                Scene scene = new Scene(parent);
                stage.setTitle("Suggested Targets");
                stage.setScene(scene);
                stage.show();
                Stage prev = (Stage) targetInfoAnchor.getScene().getWindow();
                prev.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a target faction to continue.");
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
        Stage prev = (Stage) targetInfoAnchor.getScene().getWindow();
        prev.close();
    }

}
