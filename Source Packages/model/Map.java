package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class holds information about the game's entire map, and the import method to construct it.
 * @author Benjamin Gamman
 */
public class Map {

    /**
     * Name of the player's faction.
     */
    private static String playerFaction = "";

    /**
     * List of all Planets.
     */
    private static ObservableList<Planet> allPlanets = FXCollections.observableArrayList();

    /**
     * List of all Factions.
     */
    private static ObservableList<Faction> allFactions = FXCollections.observableArrayList();

    /**
     * List of all faction names (used to populate drop-down menus).
     */
    private static ObservableList<String> factionNames = FXCollections.observableArrayList();

    /**
     * Getter for the list of all Planets.
     */
    public static ObservableList<Planet> getAllPlanets() { return allPlanets; }

    /**
     * Getter for the list of all Factions.
     */
    public static ObservableList<Faction> getAllFactions() { return allFactions; }

    /**
     * Getter for the list of all faction names (used for drop-down menus).
     */
    public static ObservableList<String> getFactionNames() { return factionNames; }

    /**
     * Setter for the name of the player's faction.
     */
    public static void setPlayerFaction(String faction) { playerFaction = faction; }

    /**
     * Getter for the name of the player's faction.
     */
    public static String getPlayerFaction() { return playerFaction; }

    /**
     * Imports all data about the game's map required by the application from a CSV file,
     * using it to construct representative objects.
     */
    public static void importMapData() throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File("./MAP+ Data.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] planetEntry = scanner.nextLine().split("\\s*,\\s*");
                Planet newPlanet = new Planet();
                newPlanet.setName(planetEntry[0]);
                newPlanet.setUniverse(Integer.parseInt(planetEntry[1]));
                newPlanet.setFaction(planetEntry[2]);
                newPlanet.setI(Integer.parseInt(planetEntry[3]));
                newPlanet.setT(Integer.parseInt(planetEntry[4]));
                newPlanet.setM(Integer.parseInt(planetEntry[5]));
                newPlanet.setC(Integer.parseInt(planetEntry[6]));
                newPlanet.setP(Integer.parseInt(planetEntry[7]));
                newPlanet.setR(Integer.parseInt(planetEntry[8]));
                newPlanet.setA(Integer.parseInt(planetEntry[9]) + Integer.parseInt(planetEntry[10]));
                newPlanet.setValue(Integer.parseInt(planetEntry[11]));
                String[] connections = planetEntry[12].split("\\s*;\\s*");
                for (String planetName : connections) {
                    newPlanet.addConnectionName(planetName);
                }
                newPlanet.setTotalConnections(newPlanet.getConnectionsList().size());
                allPlanets.add(newPlanet);
                if (factionNames.contains(newPlanet.getFaction())) {
                    for (Faction faction : allFactions) {
                        if (faction.getName().equals(newPlanet.getFaction())) {
                            faction.addPlanet(newPlanet);
                        }
                    }
                }
                else {
                    factionNames.add(newPlanet.getFaction());
                    Faction newFaction = new Faction();
                    newFaction.setName(newPlanet.getFaction());
                    newFaction.addPlanet(newPlanet);
                    allFactions.add(newFaction);
                }
            }
            Collections.sort(factionNames);
            for (Planet planet1 : Map.getAllPlanets()) {
                for (Planet planet2 : Map.getAllPlanets()) {
                    if (planet1.getConnectionsList().contains(planet2.getName())) {
                        planet1.addConnection(planet2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File Not Found");
            alert.showAndWait();
        }
    }

    /**
     * Prints information about all planets to console.
     * Used for testing and debugging only.
     */
    public static void printAllPlanets() {
        for (Planet planet : allPlanets) {
            planet.printPlanet();
        }
    }

    /**
     * Prints information about all factions to console.
     * Used for testing and debugging only.
     */
    public static void printAllFactions() {
        for (Faction faction : allFactions) {
            faction.printFaction();
        }
    }

}