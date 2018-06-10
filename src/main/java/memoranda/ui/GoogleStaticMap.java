package main.java.memoranda.ui;
/**
 * FileName: GoogleStaticMap.java
 * Created: 04/16/2018
 * Author: Wesley Davis
 * 
 * Description: Creates a Static Google Map based on the lat and lon points provided by the JSON file.
 * Displays the route as a short path creation, this path is then displayed by the Google Static Map API.
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.memoranda.DJKAGraph;
import main.java.memoranda.NodeImpl;
import main.java.memoranda.RefactoredDjkAlgo;

import org.json.JSONObject;
import org.json.JSONArray;


public class GoogleStaticMap extends JPanel {

    private JSONArray nodeObjArray;
    private List<NodeImpl> impNodeList;
    private Set<NodeImpl> nodes;
    private URL url;
    private ImageIcon imageIcon;
    private InputStream in;
    private OutputStream out;
    private DecimalFormat df = new DecimalFormat("#.##");
    private List<NodeImpl> shortPath;
    private DJKAGraph dGraph;
    private NodeCoordinateTable nodeTable;
    
    private byte[] b = new byte[2048];
    private int length; 
    
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String imageDirectory = "src/main/resources/mapImage/";
    private String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=";
    private String imageSettings = "&zoom=15&size=640x640&scale=2&maptype=roadmap";
    private String marker = "&markers=size:mid%7Ccolor:red%7Clabel:";
    private String[] markers;
    private String pathSetting = "&path=color:red|weight:5";
    private String destinationFile = "image.jpg";
    private String key = "&key=AIzaSyBzyKW5cGv6rf1RwgfQGOiojq7BduowMhE";
    
    double totalDistance;
    public GoogleStaticMap(JSONObject obj) {
        super(new GridLayout(1,2));
        this.setPreferredSize(new Dimension(1000,640));
        impNodeList = new ArrayList<>();
        nodeObjArray = obj.getJSONArray("nodes");
        dGraph = new DJKAGraph();
        
        generateNodes();
        sortNodes();
        shortestPath();
        try {
            getStaticMap(shortPath);
            imageIcon = new ImageIcon((new ImageIcon(imageDirectory + destinationFile))
                    .getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH));
            System.out.println(getTotalDistance());
            System.out.println(df.format(getDuration(getTotalDistance())));
            totalDistance = getTotalDistance();
            nodeTable = new NodeCoordinateTable(impNodeList,totalDistance,getDuration(totalDistance));
            this.add(new JLabel(imageIcon));
            this.add(nodeTable);

        }catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        
    }
    
    public GoogleStaticMap(List<NodeImpl> nodes, double distance, double duration) {
        super(new GridLayout(1,2));
        this.setPreferredSize(new Dimension(1000,640));
        
        impNodeList = nodes;
        shortPath = nodes;
        
        shortPath.add(shortPath.get(0));
        
        try {
            getStaticMap(shortPath);
            imageIcon = new ImageIcon((new ImageIcon(imageDirectory + destinationFile))
                    .getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH));
            nodeTable = new NodeCoordinateTable(impNodeList,distance,duration);
            this.add(new JLabel(imageIcon));
            this.add(nodeTable);

        }catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        
        JFrame displayRoute = new JFrame("Route");
        
        displayRoute.add(this);
        displayRoute.setLocationRelativeTo(null);
        displayRoute.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayRoute.pack();
        displayRoute.setVisible(true);
    }
    
    /**
     * Method: generateNodes()
     * Input: None
     * Return: None
     * 
     * Description: Generates the nodes from the imported json into node objects.
     */
    private void generateNodes() {
        
        for(int i = 0; i < nodeObjArray.length(); i++) {
            JSONObject node = nodeObjArray.getJSONObject(i);
            NodeImpl newNode = new NodeImpl("Point " + i, node);
            impNodeList.add(newNode);
        }
    }
    
    /**
     * Method: sortNodes
     * 
     * Input: None
     * Return: None
     * 
     * Description: Sorts the nodes in order using a basic selection sort
     * by latitude.
     */
    private void sortNodes() {
        int size = impNodeList.size();
        for(int i = 0; i < size - 1; i++) {
            int min = i;

            for(int j = i + 1; j < size; j++) {
                if(impNodeList.get(j).getLat() < impNodeList.get(min).getLat()) {
                    min = j;
                }
            }

            NodeImpl temp = impNodeList.get(min);
            impNodeList.set(min, impNodeList.get(i));
            impNodeList.set(i, temp);
        }
    }
    
    /**
     * Method: shortestPath
     * Input: None
     * Return: None
     * 
     * Description: Creates a graph to determine the shortest path between nodes based on the 0
     * index node. This is to be considered the initial starting point of the tour.
     * This method will then determine the distance from one node to another.
     */
    private void shortestPath() {
        for(int i = 0; i < impNodeList.size(); i++) {
           
            for(int j = 1; j < impNodeList.size(); j++) {
                impNodeList.get(i).addDestination(impNodeList.get(j), impNodeList.get(i).distanceTo(impNodeList.get(j)));
            }

        }
        
        for(NodeImpl node : impNodeList) {
            if(node.getDistance() != 0) {
                dGraph.addNode(node);
            }
        }
        NodeImpl sourceNode = impNodeList.get(0);
        dGraph = RefactoredDjkAlgo.calculateShortestPath(dGraph, sourceNode);
        
        nodes = dGraph.getNodes();
        shortPath = new ArrayList<>(nodes);
        
        shortPath.add(shortPath.get(0));
        for(NodeImpl node : shortPath) {
            System.out.println(node);
        }
        
    }    /**
     * Method: getShortPath
     * Input: None
     * Return: Linked list of imported nodes
     * 
     * Description: Returns a list of short path nodes to send to the route object to save
     * @return
     */
    public Set<NodeImpl> getShortPath(){
        return new HashSet<NodeImpl>(shortPath);
    }
    
    /**
     * Method: getTotalDistance
     * Input: None
     * Return: Total distance of the tour
     * 
     * Description: Calculates the total distance of the tour provided by the nodes
     * There is a slight error in distance since the map does not completely calculate
     * all connected node values. This is taken into account and calculated in the method.
     */
    public double getTotalDistance() {
        
        double routeLength = 0;
        double totalDistance = 0;
        
        double lastSegDistance = shortPath.get(shortPath.size() - 2).distanceTo(shortPath.get(shortPath.size() - 1));
        shortPath.get(shortPath.size() - 1).setDistance(lastSegDistance);
        
        for(NodeImpl node : shortPath) {
            System.out.println(node + " Distance: " + node.getDistance());
            routeLength += node.getDistance();
        }
        

        totalDistance = ((routeLength)/1000)*0.621371;
        
        System.out.println("[DEBUG] Route Length: " + df.format(totalDistance) + " miles.");
        
        return totalDistance;
    }
    
    /**
     * Method: getDuration
     * Input: Round Trip distance of the tour
     * Return: Calculated tour duration based on bus speed.
     * 
     * Description: Calculates the duration of the tour based on the total
     * distances from the nodes used to create the tour.
     */
    public double getDuration(double tourDistance) {
        double busSpeed = 30;
        
        double duration = tourDistance / busSpeed;
        
        if(duration < 1) {
            duration = duration * 60;
        }
        
        return duration;
    }
    
    /**
     * Method: getStaticMap()
     * Input: List - Contains Imported Nodes
     * @throws IOException
     * Return: None
     * 
     * Description: Connects to Google Static Map API, plots the points
     * to a google map and sends over static image to be displayed.
     */
    private void getStaticMap(List<NodeImpl> path) throws IOException {
        int size = path.size();
        String centeredNode = path.get(0).getLat() + "," + path.get(0).getLon();
        imageUrl += centeredNode;
        imageUrl += imageSettings;
        
        markers = new String[size];
        
        for(int i = 0; i < size; i++) {
            String markerLocation = marker + alpha.charAt(i) + "%7C" + path.get(i).getLat() + "," + path.get(i).getLon();
            markers[i] = markerLocation;
        }
        
        for(String marker : markers) {
            imageUrl += marker;
        }
        
        String markerLocation = marker + path.get(0).getLat() + "," + path.get(0).getLon();
        imageUrl += markerLocation;
        
        for(int j = 0; j < size; j++) {
            String pathLocation = path.get(j).getLat() + "," + path.get(j).getLon();
            pathSetting += "|" + pathLocation;
        }

        imageUrl += pathSetting + key;
        
        System.out.println("[DEBUG] " + imageUrl);
        
        url = new URL(imageUrl);
        in = url.openStream();
        out = new FileOutputStream(imageDirectory + destinationFile);
        
        while((length = in.read(b)) != -1) {
            out.write(b, 0, length);
        }
        
        in.close();
        out.close();
        
    }
}
