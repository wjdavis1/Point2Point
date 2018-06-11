package main.java.memoranda.models;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/*
 * Author: Tresor Cyubahiro
 * Description: Implementation of Node Interface from Node.java
 * Date:  03.20.2018
 * SER 316 - Frankfurt
 */
import main.java.interfaces.Node;
import org.json.JSONObject;

public class NodeImpl implements Node {
	
	private String alias;
	private String id;
	private Double lat;
	private Double lon;
//	Vector<NodeImpl> previousNodes = new Vector<NodeImpl>();
//	Vector<NodeImpl> nextNodes= new Vector<NodeImpl>();
	
	private Double distance = Double.MAX_VALUE;
	private List<NodeImpl> shortestPath = new LinkedList<>();
	Map<NodeImpl, Double> adjacentNodes = new HashMap<>();
	
	
	public NodeImpl() {
		this.alias = "";
		this.lat = 0.0;
		this.lon = 0.0;
	}
	
	public NodeImpl(String alias, Double lat, Double lon) {
		this.alias = alias;
		this.lat = lat;
		this.lon  = lon;
	}
	
	public NodeImpl(Double lat, Double lon){
		this.lat = lat;
		this.lon = lon;
	}
	
	public NodeImpl(String alias,JSONObject obj){
	    this.alias = alias;
	    this.id = String.valueOf(obj.get("id"));
	    this.lat = Double.parseDouble((String)obj.get("lat"));
	    this.lon = Double.parseDouble((String)obj.get("lon"));
	}
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#setLat(double)
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#setLon(double)
	 */
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#setAlias(java.lang.String)
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#getLat()
	 */
	public Double getLat() {
		return this.lat;
	}
	
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#getLon()
	 */
	public Double getLon() {
		return this.lon;
	}
	
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#getAlias()
	 */
	public String getAlias() {
		return this.alias;
	}
	
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Node#distanceTo(main.java.interfaces.Node)
	 */
	
	
	public String getID() {
	    return this.id;
	}
	
	public void addDestination(NodeImpl destination, double distance) {
	    adjacentNodes.put(destination, distance);
	}
	
	public void setDistance(double distance) {
	    this.distance = distance;
	}
	
	public Double getDistance() {
	    return distance;
	}
	
	public List<NodeImpl> getShortestPath(){
	    return this.shortestPath;
	}
	
	public void setShortestPath(List<NodeImpl> shortestPath) {
	    this.shortestPath = shortestPath;
	}
	public Map<NodeImpl, Double> getAdjacentNodes() {
	    return this.adjacentNodes;
	}
	// Calculate ditance between two points using the haversine formula
	// Reference: https://www.movable-type.co.uk/scripts/latlong.html
	public Double distanceTo(Node toNode) {
		Double lat1 = this.lat;
		Double lat2 = toNode.getLat();
		Double lon1 = this.lon;
		Double lon2 = toNode.getLon();
		
		Double R =6371000.0;//in meters
		Double distance = 0.0;
		Double bearing = 0.0;
		   
		Double lat1Rad = Math.toRadians(lat1);
		Double lat2Rad = Math.toRadians(lat2);
		Double changePhi = Math.toRadians(lat2 - lat1);
		Double changeLambda = Math.toRadians(lon2 - lon1);
		   
		Double a = (Math.sin(changePhi/2) * Math.sin(changePhi/2)) + (Math.cos(lat1Rad) * Math.cos(lat2Rad)) * (Math.sin(changeLambda/2) * Math.sin(changeLambda/2));
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		distance = R*c;
	    //END FO DISTANCE CALCS

		return distance;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int prime = 37;
		int hash = 1;
		hash = prime * hash + ((this.alias == "") ? 0: this.alias.hashCode());
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		NodeImpl check = (NodeImpl) obj;
				
		if (this.lat.equals(check.getLat()) && this.lon.equals(check.getLon())) {
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Name: "+this.alias+", Lat: "+this.lat+", Lon: "+this.lon;
	}
}
