package test.java;
/*
 * Author: Tresor Cyubahiro
 * Description: Class to test the Dijskstra Algorithm
 * Date: 03.25.2018
 * SER 316 - Frankfurt
 */

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;
import main.java.memoranda.NodeImpl;
import main.java.memoranda.DijkstraAlgorithm;
import main.java.memoranda.NodeCollection;
import main.java.memoranda.NodeEdge;

public class DijkstraAlgorithmTest {

	private List<NodeImpl> nodes;
	private List<NodeEdge> edges;
	
	public DijkstraAlgorithmTest() {
		nodes = new ArrayList<NodeImpl>();
		edges = new ArrayList<NodeEdge>();
		
		for (int i = 0; i < 6; i++) {
			int count = i+1;
			NodeImpl point = new NodeImpl("Point"+i, count*12.0, count*15.0);
			nodes.add(point);
		}
		
		NodeImpl newRoadSource = new NodeImpl("Point0", 12.0, 15.0);
		NodeImpl newRoaDestination = new NodeImpl("Point1", 24.0, 30.0);
		Double distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_0", newRoadSource, newRoaDestination, distance);
		
		newRoadSource = new NodeImpl("Point0", 12.0, 15.0);
		newRoaDestination = new NodeImpl("Point2", 36.0, 45.0);
		distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_1", newRoadSource, newRoaDestination, distance);
		
		newRoadSource = new NodeImpl("Point0", 12.0, 15.0);
		newRoaDestination = new NodeImpl("Point3", 48.0, 60.0);
		distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_2", newRoadSource, newRoaDestination, distance);
		
		newRoadSource = new NodeImpl("Point3", 48.0, 60.0);
		newRoaDestination = new NodeImpl("Point4", 60.0, 75.0);
		distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_3", newRoadSource, newRoaDestination, distance);
		
		newRoadSource = new NodeImpl("Point3", 48.0, 60.0);
		newRoaDestination = new NodeImpl("Point5", 72.0, 90.0);
		distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_4", newRoadSource, newRoaDestination, distance);
		
		newRoadSource = new NodeImpl("Point4", 60.0, 75.0);
		newRoaDestination = new NodeImpl("Point5", 72.0, 90.0);
		distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_5", newRoadSource, newRoaDestination, distance);
		
		
		newRoadSource = new NodeImpl("Point2", 36.0, 45.0);
		newRoaDestination = new NodeImpl("Point5", 72.0, 90.0);
		distance = newRoadSource.distanceTo(newRoaDestination);
		
		addRoad("Raod_6", newRoadSource, newRoaDestination, distance);
	}
	
//	@Test
//	public void getConnectingNodes () {
//		NodeCollection network = new NodeCollection(nodes, edges);
//		DijkstraAlgorithm algo = new DijkstraAlgorithm(network);
//		
//		NodeImpl newPoint = new NodeImpl("Point0", 12.0, 15.0);
//		List<NodeImpl> connectingNodes = algo.getConnectingNodes(newPoint);
//		System.out.println("NODES CONNECTED TO Point0\n");
//		System.out.println(connectingNodes.size());
//	}
	
	@Test
	public void testTraverseNodes () {
		
		NodeCollection network = new NodeCollection(nodes, edges);
		DijkstraAlgorithm algo = new DijkstraAlgorithm(network);
		algo.traverseNodes(nodes.get(0));
		LinkedList<NodeImpl> path = algo.getPath(nodes.get(5));
		assertNotNull(path);
		assertTrue(path.size() > 0);
		System.out.println("\nSHORTEST PATH FROM Point0 to Point5\n");
		for (NodeImpl node: path) {
			System.out.println(node);
		}
		
	}
	
	private void addRoad(String roadName, NodeImpl sourceNode, NodeImpl destNode, Double distance) {
		NodeEdge road = new NodeEdge(roadName, sourceNode, destNode, distance);
		edges.add(road);
	}
	
}
