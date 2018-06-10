package main.java.memoranda;
/**
 * File Name: RouteCollection.java
 * Author: James Ortner
 * Date: February 21st, 2018
 * 
 * Description: Holds a collection of routes, stores them into a file, 
 * and retrieves the file contents that holds route
 * information. When the Route panel loads up, a table of routes will be listed.
 * 
 */

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import main.java.memoranda.RouteImpl;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Class: RouteCollection Description: A class that holds a HashSet of Routes,
 * organized by their ID and the Route Object.
 *
 */
public class RouteCollection implements Serializable {

    private Map<String, Route> routeCollection;
    private Set<String> routeIds;

    private String routeDataPath = "src/main/resources/data/routes/";
    private File routesFile;
    private FileInputStream in;
    private String fileName;

    public RouteCollection(String fileName) {
        routeDataPath += fileName;
        System.out.println("[DEBUG]" + routeDataPath);
        routeCollection = new HashMap<>();
        this.fileName = fileName;
        readFromFile();
    }

    public boolean doesRouteExist(String routeId) {
        boolean doesExist = false;
        if (routeCollection.containsKey(routeId)) {
            doesExist = true;
        }
        return doesExist;
    }

    /**
     * Method: addRoute
     * 
     * Input: Route Object Return: Boolean Description: Adds a new route to the
     * RouteCollection HashMap, checks to see if the route is already in the set by
     * route id. If the route is not in the system, the method adds it in and
     * returns true, else false. The boolean return is for GUI purposes
     */
    public boolean addRoute(RouteImpl newRoute) {
        boolean canAdd = false;

        if (!routeCollection.containsKey(newRoute.getRouteId())) {
            routeCollection.put(newRoute.getRouteId(), newRoute);
            canAdd = true;
        }
        return canAdd;
    }

    /**
     * Method: removeRoute
     * 
     * Input: RouteId Return: Boolean
     * 
     * Description: Looks for the Route in the collection by route id, once found
     * the route is then removed from the system. if the route is not found, then
     * error message is displayed with no edits done.
     */
    public boolean removeRoute(String routeId) {
        boolean canRemove = false;

        if (routeCollection.containsKey(routeId)) {
            routeCollection.remove(routeId);
            canRemove = true;
        }
        return canRemove;
    }

    /**
     * Method: edit Route Input: Route ID, Edit - Information to be edited, Option -
     * JOptionPane integer value. Return boolean - determines if the edit can be
     * done and sent to GUI
     * 
     * Description: Looks through the route collection to find the route based off
     * of ID, then if the route is in the system, the edit of information based of
     * the option integer can be made. The option integer is selected from the
     * JOptionPane in the EditRoutePanel
     */
    public boolean editRoute(String routeId, String edit, int option) {
        boolean canEdit = false;

        if (routeCollection.containsKey(routeId)) {
            if (option == 0) {
                routeCollection.get(routeId).setStartPoint(edit);
            } 
            else if (option == 1) {
                routeCollection.get(routeId).setName(edit);
            }
            else if (option == 2) {
                routeCollection.get(routeId).setLength(Double.parseDouble(edit));
            }
            else if (option == 3) {
                routeCollection.get(routeId).setDuration(Double.parseDouble(edit));
            }

            canEdit = true;
        }

        return canEdit;
    }

    /**
     * Method saveToFile
     * 
     * Input: None Return: None
     * 
     * @throws IOException
     *             Description: Converts the RouteCollection into a string and
     *             writes it to the JSONFile
     */
    public void saveToFile() throws IOException {
        FileWriter out = new FileWriter(routesFile);
        JSONObject obj = new JSONObject();
        for (String routeId : routeCollection.keySet()) {
            obj.put(routeId, routeCollection.get(routeId).toJsonObject());
        }
        out.write(obj.toString());
        out.flush();
        out.close();
    }

    /**
     * Method: readFromFile Input: None Return: None Description: Reads in the JSON
     * Objects from the file and converts them into the RouteCollection.
     */
    public void readFromFile() {
        routesFile = new File(routeDataPath);
        if (routesFile.exists() && !routesFile.isDirectory()) {
            System.out.println("[DEBUG] File Exists!");
            try {
                in = new FileInputStream(routesFile);
                JSONObject obj = new JSONObject(new JSONTokener(in));

                if (obj.length() == 0) {

                    System.out.println("[DEBUG] No Routes Available");

                } else {
                    String[] routeIds = JSONObject.getNames(obj);
                    for (int i = 0; i < routeIds.length; i++) {
                        System.out.println("[DEBUG] " + routeIds[i]);
                        Route route = new RouteImpl((JSONObject) obj.getJSONObject(routeIds[i]));
                        routeCollection.put(routeIds[i], route);
                    }
                }

                in.close();

                for (String routeId : routeCollection.keySet()) {
                    System.out
                            .println("[DEBUG] " + routeId + " Route ID: " + routeCollection.get(routeId).getRouteId());
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } else {
            routesFile = new File(this.routeDataPath + fileName);
            try {
                if (routesFile.createNewFile()) {
                    System.out.println("Created: " + fileName + " Found in: " + routeDataPath);
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }

    }

    /**
     * Method: getRoutes Input: None Return: HashMap with route ids and information
     * Description: Static method that creates a new HashMap Routes to be used with
     * other panels and other classes for data retrieval.
     */
    public static HashMap<String, Route> getRoutes() {
        Map<String, Route> routes = new HashMap<String, Route>();
        String filePath = "src/main/resources/data/routes/routes.json";
        File file = new File(filePath);

        try {
            FileInputStream in = new FileInputStream(file);
            JSONObject obj = new JSONObject(new JSONTokener(in));

            String[] ids = JSONObject.getNames(obj);
            for (int i = 0; i < ids.length; i++) {
                Route newRoute = new RouteImpl((JSONObject) obj.getJSONObject(ids[i]));
                routes.put(ids[i], newRoute);
            }

            in.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return (HashMap<String, Route>) routes;
    }
}