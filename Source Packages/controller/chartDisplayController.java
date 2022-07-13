package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;
import model.Faction;
import model.Map;
import model.Planet;
import java.util.ArrayList;

/**
 * This is the controller class for the window displaying visualizations of the distribution of planets and resources among all factions.
 * @author Benjamin Gamman
 */
public class chartDisplayController {
    @FXML
    private AnchorPane chartDisplayAnchor;
    @FXML
    private StackedBarChart<String, Integer> resourcesBarChart;
    @FXML
    private StackedBarChart<String, Integer> territoryBarChart;

    /**
     * Populates the charts with the necessary data.
     */
    public void initialize() {

        XYChart.Series<String, Integer> seriesI = new XYChart.Series<>();
        seriesI.setName("Influence");
        XYChart.Series<String, Integer> seriesT = new XYChart.Series<>();
        seriesT.setName("Tech");
        XYChart.Series<String, Integer> seriesM = new XYChart.Series<>();
        seriesM.setName("Magic");
        XYChart.Series<String, Integer> seriesC = new XYChart.Series<>();
        seriesC.setName("Commerce");
        XYChart.Series<String, Integer> seriesP = new XYChart.Series<>();
        seriesP.setName("Production");
        XYChart.Series<String, Integer> seriesR = new XYChart.Series<>();
        seriesR.setName("Recruitment");
        XYChart.Series<String, Integer> seriesA = new XYChart.Series<>();
        seriesA.setName("Agriculture (net)");

        for (Faction faction : Map.getAllFactions()) {
            int factionI = 0;
            int factionT = 0;
            int factionM = 0;
            int factionC = 0;
            int factionP = 0;
            int factionR = 0;
            int factionA = 0;
            for (Planet planet : faction.getTerritory()) {
                factionI = factionI + planet.getI();
                factionT = factionT + planet.getT();
                factionM = factionM + planet.getM();
                factionC = factionC + planet.getC();
                factionP = factionP + planet.getP();
                factionR = factionR + planet.getR();
                factionA = factionA + planet.getA();
            }
            seriesI.getData().add(new XYChart.Data<>(faction.getName(), factionI));
            seriesT.getData().add(new XYChart.Data<>(faction.getName(), factionT));
            seriesM.getData().add(new XYChart.Data<>(faction.getName(), factionM));
            seriesC.getData().add(new XYChart.Data<>(faction.getName(), factionC));
            seriesP.getData().add(new XYChart.Data<>(faction.getName(), factionP));
            seriesR.getData().add(new XYChart.Data<>(faction.getName(), factionR));
            seriesA.getData().add(new XYChart.Data<>(faction.getName(), factionA));
        }

        resourcesBarChart.getData().addAll(seriesP, seriesC, seriesM, seriesA, seriesT, seriesI, seriesR);

        int numUniverses = 0;
        for (Planet planet : Map.getAllPlanets()) {
            if (planet.getUniverse() > numUniverses) { numUniverses = planet.getUniverse(); }
        }
        ArrayList<XYChart.Series<String, Integer>> universeSeriesList = new ArrayList<>();
        for (int i = 1; i <= numUniverses; i++) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName("Universe "+ i);
            universeSeriesList.add(series);
        }

        for (Faction faction : Map.getAllFactions()) {
            int[] universeCounts = new int[numUniverses];
            for (Planet planet : faction.getTerritory()) {
                universeCounts[planet.getUniverse()-1]+=1;
            }

            for (int i = 1; i <= numUniverses; i++) {
                universeSeriesList.get(i-1).getData().add(new XYChart.Data<>(faction.getName(), universeCounts[i-1]));
            }

        }
        territoryBarChart.getData().addAll(universeSeriesList);

    }
}