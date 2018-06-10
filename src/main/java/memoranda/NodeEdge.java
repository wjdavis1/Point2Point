package main.java.memoranda;
/*
 * Author: Tresor Cyubahiro
 * Description: Class NodeEdges holds data relating to route between two nodes
 * Date: 03.25.2018
 * SER 316 - Frankfurt
 */
public class NodeEdge {
	private String name;
	private NodeImpl source;
	private NodeImpl destination;
	private Double distance;
	
	public NodeEdge(String name, NodeImpl source, NodeImpl destination, Double distance) {
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.distance = source.distanceTo(destination);
	}
	
	/*
	 * Method: getName
	 * Return: string (name of edge/road/network)
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * Method: getDestination
	 * Return: NodeImpl (Destination Node/point)
	 */
	public NodeImpl getDestination () {
		return this.destination;
	}
	
	/*
	 * Method: getSource
	 * Return: NodeImpl (Source Node/point)
	 */
	public NodeImpl getSource () {
		return this.source;
	}
	
	/*
	 * Method: getDistance
	 * Return: Double (length of road in meters)
	 */
	public Double getDistance() {
		return this.distance;
	}
	@Override
	public String toString() {
		return "Name: "+this.name+", \n\tSource: "+this.source.toString()+", \n\tDestination: "+this.destination.toString()+"\n\tDistance: "+this.distance;
	}
}
