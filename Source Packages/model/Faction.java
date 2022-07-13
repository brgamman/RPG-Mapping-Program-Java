package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

/**
 * This class holds information about a faction within the game.
 * @author Benjamin Gamman
 */
public class Faction {

    /**
     * Name of the faction.
     */
    private String name = "";

    /**
     * List of planets controlled by the faction.
     */
    private ObservableList<Planet> territory = FXCollections.observableArrayList();

    /**
     * Getter for the name of the faction.
     */
    public String getName() { return name; }

    /**
     * Getter for the faction's territory list.
     */
    public ObservableList<Planet> getTerritory() { return territory; }

    /**
     * Setter for the name of the faction.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Adds planets to the faction's territory list.
     */
    public void addPlanet(Planet planet) { territory.add(planet); }

    /**
     * Prints information about the planets in the faction's territory to console.
     * Used for testing and debugging only.
     */
    public void printFaction() {
        System.out.println(name);
        ArrayList<String> planetsList = new ArrayList<>();
        for (Planet planet : territory) {
            planetsList.add(planet.getName());
        }
        System.out.println(planetsList);
        /*
        for (Planet planet : territory) {
            planet.printPlanet();
        }
        */
    }

}
