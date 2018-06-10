package main.java.memoranda;
/*
 * Author:  Tresor Cyubahiro
 * Description: Interface for Class to hold a node (graphical point) data
 * Date: 03.20.2018
 * SER 316 - Frankfurt
 */
public interface Node {
	
	public void setLat(Double lat);					// Method to set Node's Latitude
	public void setLon(Double lon);					// Method to set Node's Longitude
	public void setAlias(String alias);				// Method to set Node's Alias (Title)
	public Double getLat();					// Method to retrieve Node's Latitude
	public Double getLon();					// Method to retrieve Node's Longitude
	public String getAlias();				// Method to retrieve Node's Alias
	public Double distanceTo(Node toNode);	// Method to calculate distance from this Node to the provided
	
}
