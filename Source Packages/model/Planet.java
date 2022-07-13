package model;

import java.util.ArrayList;

/**
 * This class holds data about an individual planet. This includes general information applicable to all planets,
 * as well as fields used only to calculate routes and assess targets.
 * @author Benjamin Gamman
 */
public class Planet {

    //These variables hold the planet's general information.
    private String name = "";
    private int universe = 0;
    private String faction = "";

    //These variables hold information about the resources the planet produces.
    private int I = 0;
    private int T = 0;
    private int M = 0;
    private int C = 0;
    private int P = 0;
    private int R = 0;
    private int A = 0;
    private int value = 0;

    //These variables define the planet's connections to others.
    private ArrayList<String> connectionsList = new ArrayList<>();
    private ArrayList<Planet> connections = new ArrayList<>();

    //These variables are used to calculate routes.
    private double dist = Double.POSITIVE_INFINITY;
    private Planet prev = null;
    private int stop = -1;

    //These variables are used to select targets and assess vulnerabilities.
    private int targetPriority = 0;
    private double targetProbability = 0.0;
    private int targetRank = -1;
    private int totalConnections = 0;
    private int friendlyConnections = 0;
    private int hostileConnections = 0;
    private int playerConnections = 0;

    //Getters for each variable.
    public String getName() { return name; }
    public int getUniverse() { return universe; }
    public String getFaction() { return faction; }
    public int getI() { return I; }
    public int getT() { return T; }
    public int getM() { return M; }
    public int getC() { return C; }
    public int getP() { return P; }
    public int getR() { return R; }
    public int getA() { return A; }
    public int getValue() { return value; }
    public ArrayList<String> getConnectionsList() { return connectionsList; }
    public ArrayList<Planet> getConnections() { return connections; }
    public double getDist() { return dist; }
    public Planet getPrev() { return prev; }
    public int getStop() { return stop; }
    public int getTargetPriority() { return targetPriority; }
    public double getTargetProbability() { return targetProbability; }
    public int getTargetRank() { return targetRank; }
    public int getTotalConnections() { return totalConnections; }
    public int getFriendlyConnections() { return friendlyConnections; }
    public int getHostileConnections() { return hostileConnections; }
    public int getPlayerConnections() { return playerConnections; }

    //Setters for each variable.
    public void setName(String name) { this.name = name; }
    public void setUniverse(int universe) { this.universe = universe; }
    public void setFaction(String faction) { this.faction = faction; }
    public void setI(int I) { this.I = I; }
    public void setT(int T) { this.T = T; }
    public void setM(int M) { this.M = M; }
    public void setC(int C) { this.C = C; }
    public void setP(int P) { this.P = P; }
    public void setR(int R) { this.R = R; }
    public void setA(int A) { this.A = A; }
    public void setValue(int value) { this.value = value; }
    public void addConnectionName(String planetName) { connectionsList.add(planetName); }
    public void addConnection(Planet planet) { connections.add(planet); }
    public void setDist(double dist) { this.dist = dist; }
    public void setPrev(Planet prev) {this.prev = prev; }
    public void setStop(int stop) { this.stop = stop; }
    public void setTargetPriority(int priority) { targetPriority = priority; }
    public void setTargetProbability(double probability) { targetProbability = probability; }
    public void setTargetRank(int rank) { targetRank = rank; }
    public void setTotalConnections(int totalConnections) { this.totalConnections = totalConnections; }
    public void setFriendlyConnections(int friendlyConnections) { this.friendlyConnections = friendlyConnections; }
    public void setHostileConnections(int hostileConnections) { this.hostileConnections = hostileConnections; }
    public void setPlayerConnections(int playerConnections) { this.playerConnections = playerConnections; }

    /**
     * Prints information about a planet to console.
     * Used only for testing and debugging.
     */
    public void printPlanet() {
        System.out.println(name);
        System.out.println(universe);
        System.out.println(faction);
        System.out.println(I);
        System.out.println(T);
        System.out.println(M);
        System.out.println(C);
        System.out.println(P);
        System.out.println(R);
        System.out.println(A);
        System.out.println(value);
        System.out.println(connectionsList);
        System.out.println();
        System.out.println("Connected Planets:");
        for (Planet planet : connections) {
            System.out.println(planet.getName());
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

}
