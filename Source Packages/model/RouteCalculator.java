package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Collections;
import static java.lang.Math.floor;

/**
 * This class calculates routes and holds the resulting information to be accessed by other classes.
 * @author Benjamin Gamman
 */
public class RouteCalculator {

    /**
     * Holds the calculated route to be accessed by other classes.
     */
    private static ObservableList<Planet> route = FXCollections.observableArrayList();

    /**
     * Gets the calculated route to be used by other classes.
     */
    public static ObservableList<Planet> getRoute() { return route; }

    /**
     * Calculates the shortest route between provided start and destination planets using an application of Dijkstra's algorithm.
     */
    public static void calcRoute(Planet start, Planet dest) {
        ArrayList<Planet> unvisited = new ArrayList<>();
        for (Planet current : Map.getAllPlanets()) {
            current.setDist(Double.POSITIVE_INFINITY);
            unvisited.add(current);
        }
        start.setDist(0.0);
        Planet nearest = start;
        while (!unvisited.isEmpty()) {
            for (Planet current : unvisited) {
                if (current.getDist()<nearest.getDist()) {
                    nearest= current;
                }
            }
            unvisited.remove(nearest);
            for (Planet connection : nearest.getConnections()) {
                double altDist = Double.POSITIVE_INFINITY;
                if (connection.getUniverse()==nearest.getUniverse()) {
                    altDist = nearest.getDist()+1.0;
                }
                else {
                    altDist = nearest.getDist()+1.1;
                }
                if (altDist < connection.getDist()) {
                    connection.setDist(altDist);
                    connection.setPrev(nearest);
                }
            }
            nearest = new Planet();
        }
        route.clear();
        Planet current = dest;
        while (!(current.getName().equals(start.getName()))) {
            route.add(current);
            current.setStop((int)floor(current.getDist()));
            current = current.getPrev();
        }
        start.setStop(0);
        route.add(start);
        Collections.reverse(route);
    }
}