package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import model.Planet;
import model.TargetSelector;

/**
 * This is the controller class for the window displaying a visualization of threat probabilities.
 * @author Benjamin Gamman
 */
public class threatChartController {
    @FXML
    private AnchorPane threatChartAnchor;
    @FXML
    private PieChart pieChart;

    /**
     * Populates the chart with the necessary data.
     */
    public void initialize() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Planet target : TargetSelector.getPriorityTargets()) {
            pieChartData.add(new PieChart.Data(target.getName(), target.getTargetProbability()));
        }
        pieChart.setData(pieChartData);
    }
}
