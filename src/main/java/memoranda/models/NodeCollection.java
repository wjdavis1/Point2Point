package main.java.memoranda.models;

import java.util.List;

import org.json.JSONObject;

/*
 * Author: Tresor Cyubahiro
 * Description:  Class to hold collection of Nodes and their edges
 * Date: 03.25.2018
 * SER 316 - Frankfurt
 */
public class NodeCollection {
	
	private final List<NodeImpl> nodes;
	private final List<NodeEdge> edges; 
	
	public NodeCollection (List<NodeImpl> nodes, List<NodeEdge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	
	/*
	 * Method: getNodes
	 * Return: list of nodes in a Node collection
	 */
	public List<NodeImpl> getNodes() {
		return this.nodes;
	}
	
	/*
	 * Method: getEdges
	 * Return: list of edges in a node collection
	 */
	public List<NodeEdge> getEdges() {
		return this.edges;
	}
	
	/*
	 * Method: findShortestPath
	 * Description: Find shortest path between a collection of nodes using the Dyskstra algorithm, 
	 * 	and returns a subset of the Nodes as a list of points consisted in the shrtest path
	 * Return: JSONObject 
	 */
	static public JSONObject findShortestPath() {
		return new JSONObject();
	}
	
	/*
	 * Dummy method added for displaying Points collection
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer outPut = new StringBuffer();
		outPut.append("\nPoints:\n");
		for(NodeImpl node: nodes) {
			outPut.append(node.toString()+"\n");
		}
		outPut.append("\nEdges:\n");
		for(NodeEdge edge: edges) {
			outPut.append(edge.toString()+"\n");
		}
		return outPut.toString();
	}
}
