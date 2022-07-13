import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Application;
import model.Map;
import model.TargetSelector;

/**
 * This application calculates routes, assesses threats, and selects targets for players in the tabletop roleplaying/strategy game Nexus.
 * @author Benjamin Gamman
 */
public class MAPP extends Application {

    /**
     * Launches the application.
     */
    public static void main(String[] args) { launch(args); }

    /**
     * Initializes the application's opening window and imports data.
     */
    public void start (Stage stage) throws IOException {
        Map.importMapData();
        Parent parent = FXMLLoader.load(getClass().getResource("view/selectFaction.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Welcome to MAP+");
        stage.setScene(scene);
        stage.show();

        /*
        //For testing and debugging
        Map.printAllPlanets();
        Map.printAllFactions();
        TargetSelector.selectTargets("Imperator's Hand", "Republic of Caedis", false, false, false, false, false, false, false);
        TargetSelector.selectTargets("Fury Shadow Ascendancy", "", false, false, false, false, false, false, false);
        TargetSelector.selectTargets("", "Phoenix", false, false, false, false, false, false, false);
        */
    }

}
