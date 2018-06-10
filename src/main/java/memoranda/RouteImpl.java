package main.java.memoranda;
/**
 * File Name: RouteImpl.java
 * Description: Implementation of the Route Interface, used for 
 * storing Route data such as the destination, routeId, and the
 * start and end points
 * @author James Ortner
 * Date: Feb 21st, 2018
 * 
 * EDIT: 03/14/2018 - Wesley Davis
 * Enhanced RouteImpl to have a Route Node class to store and use as user is importing
 * a file with Latitude and Longitude node sets
 * 
 */

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import main.java.memoranda.NodeImpl;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Class: RouteImpl Description: SEE FILE DESCRIPTION ABOVE
 */
public class RouteImpl implements Route, Serializable {

    private String routeId;
    private String startPoint;
    private String name;
    private double length;
    private double duration;
    private List<NodeImpl> routeNodes;
    private Set<NodeImpl> nodes;

    public RouteImpl() {
        // destination = null;
        routeId = null;
        startPoint = null;
        routeNodes = new ArrayList<>();
        name = null;
    }

    public RouteImpl(JSONObject obj) {
        // destination = (String)obj.get("destination");
        routeId = (String) obj.get("routeId");
        startPoint = (String) obj.get("startPoint");
        name = (String) obj.get("name");
        length = Double.parseDouble(String.valueOf(obj.get("length")));
        duration = Double.parseDouble(String.valueOf(obj.get("duration")));
        //routeNodes = new ArrayList<>();
        nodes = new HashSet<>();
        routeNodes = new ArrayList<>();
        JSONArray nodeArray = obj.getJSONArray("nodes");
        
        for(int i = 0; i < nodeArray.length(); i++) {
            JSONObject node = nodeArray.getJSONObject(i);
            NodeImpl newNode = new NodeImpl("Point " + i,node);
            routeNodes.add(newNode);
        }
        
        //routeNodes = new ArrayList<>(nodes);
    }

    public RouteImpl(String routeId, String startPoint, String name, double length, double duration) {
        // this.destination = destination;
        this.routeId = routeId;
        this.startPoint = startPoint;
        this.name = name;
        this.length = length;
        this.duration = duration;
    }
	
	public RouteImpl(String destination, String routeId, String startPoint, Set<NodeImpl> nodes) {
		this.routeId = routeId;
		this.startPoint = startPoint;
		this.nodes = nodes;
		//routeNodes = new ArrayList<>();
	}
  
      public RouteImpl(String routeId, String startPoint, String name, double length, double duration, Set<NodeImpl> nodes) {
        // this.destination = destination;
        this.routeId = routeId;
        this.startPoint = startPoint;
        this.nodes = nodes;
        this.name = name;
        this.length = length;
        this.duration = duration;
    }

    /**
     * Method: getDestination() Input: None Return: String Description: Gets the
     * Destination.
     */
    /*
     * @Override public String getDestination() { return destination; }
     */
    /**
     * Method: getRouteId Input: None Return: String Description: Gets the Route's
     * ID
     */
    @Override
    public String getRouteId() {
        return routeId;
    }

    /**
     * Method: getStartPoint Input: None Return: String Description: Gets the Start
     * Point of the route.
     */
    @Override
    public String getStartPoint() {
        return startPoint;
    }
    
    /**
     * Method: getName Input: None Return: String Description: Gets the Name
     * of the route.
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Method: getLength Input: None Return: String Description: Gets the
     * length of the route.
     */
    @Override
    public double getLength() {
        return length;
    }
    
    /**
     * Method: getDuration Input: None Return: String Description: Gets the
     * duration of the route.
     */
    @Override
    public double getDuration() {
        return duration;
    }

    /**
     * Method: setDestination() Input: destination Return: none Description: Sets
     * the Destination
     */
    /*
     * @Override public void setDestination(String destination) { this.destination =
     * destination; }
     */
    /**
     * Method: setRouteId() Input: routeId Return: none Description: Sets the Route
     * ID.
     */
    @Override
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    /**
     * Method: setStartPoint() Input: startPoint Return: none Description: Sets the
     * route's start point.
     */
    @Override
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }
    
    /**
     * Method: setLength() Input: name Return: none Description: Sets the
     * route's length.
     */
    @Override
    public void setLength(double length) {
        this.length = length;
    }
    
    /**
     * Method: setDuration() Input: name Return: none Description: Sets the
     * route's duration.
     */
    @Override
    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    /**
     * Method: setName() Input: name Return: none Description: Sets the
     * route's name.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method: getRouteNodes() Input: None Return: RouteNodes
     * 
     * Description: Returns the Vector set of nodes that are being stored in this
     * route for other classes that may need them.
     */
    public List<NodeImpl> getRouteNodes() {
        return routeNodes;
    }

    /**
     * Method: containtsRouteNode() Input: ID of the Node as an Int Return: Boolean
     * verifying node exists.
     */
    public NodeImpl getRouteNode(int id) {
        
        NodeImpl foundNode = null;
      
        for(NodeImpl node : nodes) {
            if(Integer.parseInt(node.getID()) == id) {
                foundNode = node;
            }
        }
        
        return foundNode;
    }

    /**
     * Method: toJsonObject Input: None Return: JSONObject Description: Returns a
     * JSONObject of the Route class. EDIT: 03/14/2018 - Wesley Davis toJsonObject
     * now allows nodes to be added to the JSON File as an array of JSON Objects
     */
    public JSONObject toJsonObject() {
        int index = 0;

        JSONObject obj = new JSONObject();
        JSONObject[] nodeObjectArray = new JSONObject[this.nodes.size()];
        for(NodeImpl node : this.nodes) {
            JSONObject nodeObj = new JSONObject();
            nodeObj.put("id", node.getID());
            nodeObj.put("lat", String.valueOf(node.getLat()));
            nodeObj.put("lon", String.valueOf(node.getLon()));
            nodeObjectArray[index] = nodeObj;
            index++;
        }
        JSONArray outNodeArray = new JSONArray(nodeObjectArray);
        // obj.put("destination", destination);
        obj.put("routeId", routeId);
        obj.put("startPoint", startPoint);
        obj.put("name", name);
        obj.put("length", length);
        obj.put("duration", duration);
        obj.put("nodes", outNodeArray);
        return obj;
    }

    /**
     * Method: toJSONString Input: None Return: None
     * 
     * Description: Converts the JSONObject of this class into a string.
     */
    public String toJSONString() {
        String jsonString;
        jsonString = toJsonObject().toString();
        return jsonString;
    }
}
