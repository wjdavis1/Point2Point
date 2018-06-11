package main.java.memoranda.models;
/*
 * Author: Tresor Cyubahiro
 * Description: Class that implements the Dijkstra algoritm for finding the shortest path between two nodes in a collection of Nodes
 * Date: 03.25.2018
 * SER 316 - Frankfurt
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
	
	private final List<NodeImpl> nodes;
	private final List<NodeEdge> edges;
	private Set<NodeImpl> visitedNodes;
	private Set<NodeImpl> unvisitedNodes;
	private Map<NodeImpl, NodeImpl> predecessors;
	private Map<NodeImpl, Double> distance;
	
	public DijkstraAlgorithm (NodeCollection collection) {
		this.nodes = new ArrayList<NodeImpl>(collection.getNodes());
		this.edges = new ArrayList<NodeEdge>(collection.getEdges());
	}
	
	/*
	 * Method: traverseNodes
	 * Return: void
	 * Description: traverse through the Nodes from the given source, and note the connections and distances
	 */
	public void traverseNodes (NodeImpl source) {
		visitedNodes = new HashSet<NodeImpl>();
		unvisitedNodes = new HashSet<NodeImpl>();
		distance = new HashMap<NodeImpl, Double>();
		predecessors = new HashMap<NodeImpl, NodeImpl>();
		distance.put(source, 0.0);
		unvisitedNodes.add(source);
		
		while(unvisitedNodes.size() > 0) {
			NodeImpl node = getMinimum(unvisitedNodes);
			visitedNodes.add(node);
			unvisitedNodes.remove(node);
			calculateMinimumDistances(node);
		}
		
	}
	
	public void calculateMinimumDistances(NodeImpl node) {
        List<NodeImpl> adjacentNodes = getConnectingNodes(node);
        for (NodeImpl target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unvisitedNodes.add(target);
            }
        }

    }

    public Double getDistance(NodeImpl node, NodeImpl target) {
        for (NodeEdge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getDistance();
            }
        }
        throw new RuntimeException("Something went wrong :(");
    }

    public List<NodeImpl> getConnectingNodes(NodeImpl node) {
        List<NodeImpl> connectingNodes = new ArrayList<NodeImpl>();
        for (NodeEdge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isVisited(edge.getDestination())) {
            		//System.out.println(edge.getSource());
            		connectingNodes.add(edge.getDestination());
            }
        }
        return connectingNodes;
    }

    public NodeImpl getMinimum(Set<NodeImpl> nodes) {
    		NodeImpl minimum = null;
        for (NodeImpl node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    public boolean isVisited(NodeImpl node) {
        return visitedNodes.contains(node);
    }

    public Double getShortestDistance(NodeImpl destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<NodeImpl> getPath(NodeImpl target) {
        LinkedList<NodeImpl> path = new LinkedList<NodeImpl>();
        NodeImpl step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
