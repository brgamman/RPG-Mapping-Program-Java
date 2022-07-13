package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import model.Planet;
import model.TargetSelector;
import java.util.ArrayList;

/**
 * This is the controller class for the window displaying a visualization of target priorities.
 * @author Benjamin Gamman
 */
public class targetChartController {
    @FXML
    private AnchorPane targetChartAnchor;
    @FXML
    private BarChart<String, Double> barChart;

    /**
     * Populates the chart with the necessary data.
     */
    public void initialize() {
        ArrayList<Planet> topTargets = new ArrayList<>();
        for (Planet target : TargetSelector.getPriorityTargets()) {
            if (target.getTargetRank()<=10) { topTargets.add(target); }
            if (target.getTargetRank()>10) { break; }
        }
        XYChart.Series<String, Double> priorities = new XYChart.Series<>();
        for (Planet planet : topTargets) {
            priorities.getData().add(new XYChart.Data<>(planet.getName(), planet.getTargetProbability()));
        }
        barChart.getData().add(priorities);
    }

}