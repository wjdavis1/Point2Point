package main.java.memoranda;

import java.util.*;
import java.util.Map.Entry;

public class RefactoredDjkAlgo {

    public static DJKAGraph calculateShortestPath(DJKAGraph graph, NodeImpl source) {
        source.setDistance(0);
        
        Set<NodeImpl> settledNodes = new HashSet<>();
        Set<NodeImpl> unsettledNodes = new HashSet<>();
        
        unsettledNodes.add(source);
        
        while(unsettledNodes.size() != 0) {
            NodeImpl currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for(Entry<NodeImpl,Double> adjacentPair:
                currentNode.getAdjacentNodes().entrySet()) {
                NodeImpl adjacentNode = adjacentPair.getKey();
                Double edgeWeight = adjacentPair.getValue();
                
                if(!settledNodes.contains(adjacentNode)) {
                    calculateMinDistance(adjacentNode,edgeWeight,currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            
            settledNodes.add(currentNode);
            
        }
        
        return graph;
    }
    
    private static NodeImpl getLowestDistanceNode(Set<NodeImpl> unsettledNodes) {
        NodeImpl lowestDistanceNode = null;
        double lowestDistance = Double.MAX_VALUE;
        for(NodeImpl node : unsettledNodes) {
            double nodeDistance = node.getDistance();
            if(nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        
        return lowestDistanceNode;
    }
    
    private static void calculateMinDistance(NodeImpl evalNode, Double edgeWeight, NodeImpl sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if(sourceDistance + edgeWeight < evalNode.getDistance()) {
            evalNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<NodeImpl> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evalNode.setShortestPath(shortestPath);
        }
    }
}
