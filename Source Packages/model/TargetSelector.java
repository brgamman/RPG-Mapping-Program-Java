package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.util.ArrayList;

/**
 * This class selects the most advantageous targets given the scenario information provided,
 * ranking them and holding the list for use by other classes.
 * It is used both to assess potential targets for the player and to assess the player's vulnerabilities.
 * @author Benjamin Gamman
 */
public class TargetSelector {

    /**
     * List of prioritized and ranked potential targets to be accessed by other classes.
     */
    private static ObservableList<Planet> priorityTargets = FXCollections.observableArrayList();

    /**
     * Gets the list of prioritized and ranked potential targets to be used by other classes.
     */
    public static ObservableList<Planet> getPriorityTargets() { return priorityTargets; }

    /**
     * Finds and prioritizes available targets based on information provided.
     * The attacking and defending factions, connections between planets, and resource production and valuation are considered.
     */
    public static void selectTargets(String attackerName, String defenderName, boolean I, boolean T, boolean M, boolean C, boolean P, boolean R, boolean A) {

        Faction attacker = new Faction();
        Faction defender = new Faction();
        for (Faction faction : Map.getAllFactions()) {
            if (faction.getName().equals(attackerName)) { attacker = faction; }
            if (faction.getName().equals(defenderName)) { defender = faction; }
        }
        if (attackerName.equals("")) { attacker = null; }
        if (defenderName.equals("")) { defender = null; }

        ArrayList<Planet> possibleTargets = new ArrayList<>();
        int scenario = 0;
        if (!attackerName.equals("") && !defenderName.equals("")) {
            //System.out.println("Case 1");
            scenario = 1;
            for (Planet planet : defender.getTerritory()) {
                for (Planet connection : planet.getConnections()) {
                    if (connection.getFaction().equals(attackerName)) {
                        possibleTargets.add(planet);
                        break;
                    }
                }
            }
        }
        else if (attackerName.equals("") && !defenderName.equals("")) {
            //System.out.println("Case 2");
            scenario = 2;
            for (Planet planet : defender.getTerritory()) {
                for (Planet connection : planet.getConnections()) {
                    if (!connection.getFaction().equals(defenderName) && !possibleTargets.contains(planet)) {
                        possibleTargets.add(planet);
                    }
                }
            }
        }
        else if (!attackerName.equals("") && defenderName.equals("")) {
            //System.out.println("Case 3");
            scenario = 3;
            for (Planet planet : attacker.getTerritory()) {
                for (Planet connection : planet.getConnections()) {
                    if (!connection.getFaction().equals(attackerName) && !possibleTargets.contains(connection)) {
                        possibleTargets.add(connection);
                    }
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Faction information not available.");
            alert.showAndWait();
        }

        /*
        //For testing and debugging
        System.out.println("Attacker:");
        if (attacker!=null) {attacker.printFaction();}
        else {System.out.println("(all)");}
        System.out.println("Defender:");
        if (defender!=null) {defender.printFaction();}
        else {System.out.println("(all)");}
        for (Planet target : possibleTargets) { System.out.println(target.getName()); }
        */

        int weightI = 20;
        int weightT = 10;
        int weightM = 10;
        int weightC = 10;
        int weightP = 10;
        int weightR = 10;
        int weightA = 5;
        if (I) { weightI = 40; }
        if (T) { weightT = 30; }
        if (M) { weightM = 30; }
        if (C) { weightC = 30; }
        if (P) { weightP = 30; }
        if (R) { weightR = 30; }
        if (A) { weightA = 30; }

        int prioritySum = 0;
        for (Planet target : possibleTargets) {
            //System.out.println(target.getName());
            target.setFriendlyConnections(0);
            target.setHostileConnections(0);
            target.setPlayerConnections(0);
            target.setTargetPriority(target.getI()*weightI+target.getT()*weightT+target.getM()*weightM
                    +target.getC()*weightC+target.getP()*weightP+target.getR()*weightR+target.getA()*weightA);
            //System.out.println(target.getTargetPriority());
            for (Planet connection : target.getConnections()) {
                if (connection.getFaction().equals(Map.getPlayerFaction())) {
                    target.setPlayerConnections(target.getPlayerConnections()+1);
                }
                if (connection.getFaction().equals(target.getFaction())) {
                    target.setFriendlyConnections(target.getFriendlyConnections()+1);
                    target.setTargetPriority(target.getTargetPriority()+connection.getValue());
                }
                if (scenario == 2 && !connection.getFaction().equals(defenderName)) {
                    target.setHostileConnections(target.getHostileConnections()+1);
                    target.setTargetPriority(target.getTargetPriority()+(target.getValue()*2));
                }
                else if (connection.getFaction().equals(attackerName)) {
                    target.setHostileConnections(target.getHostileConnections()+1);
                    target.setTargetPriority(target.getTargetPriority()+(target.getValue()*2));
                }
                //System.out.println(target.getTargetPriority());
            }
            prioritySum = prioritySum+target.getTargetPriority();
        }

        /*
        //For testng and debugging
        System.out.println("------------------------------");
        System.out.println(prioritySum);
        System.out.println();

        System.out.println("Attacker:");
        System.out.println(attackerName);
        System.out.println("Defender:");
        System.out.println(defenderName);
        */

        for (Planet target : possibleTargets) {
            target.setTargetProbability(100*(double)target.getTargetPriority()/(double)prioritySum);
            //System.out.println(target.getName());
            //System.out.println(target.getTargetProbability());
        }

        int rank = 0;
        priorityTargets.clear();
        while (!possibleTargets.isEmpty()) {
            rank +=1;
            int maxPriority = 0;
            Planet nextTarget = new Planet();
            for (Planet target : possibleTargets) {
                if (target.getTargetPriority()>maxPriority) {
                    nextTarget = target;
                    maxPriority = target.getTargetPriority();
                }
            }
            nextTarget.setTargetRank(rank);
            priorityTargets.add(nextTarget);
            possibleTargets.remove(nextTarget);
        }

        /*
        //For testing and debugging
        for (Planet target : priorityTargets) {
            System.out.println(target.getTargetRank());
            System.out.println(target.getName());
        }
        */
    }
}
