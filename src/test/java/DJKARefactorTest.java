package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import main.java.memoranda.DJKAGraph;
import main.java.memoranda.NodeImpl;
import main.java.memoranda.RefactoredDjkAlgo;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import org.json.*;



class DJKARefactorTest {
    
    static DJKAGraph graph;
    static List<NodeImpl> testNodes;
    @BeforeAll
    static void init() throws IOException{
        File testJsonFile = new File("testNodes2.json");
        assertTrue(testJsonFile != null);
        FileInputStream in = new FileInputStream(testJsonFile);
        
        JSONObject inObj = new JSONObject(new JSONTokener(in));
        
        in.close();
        
        JSONArray nodeArray = inObj.getJSONArray("nodes");
        
        testNodes = new ArrayList<>();
        
        
        for(int i = 0; i < nodeArray.length(); i++) {
            JSONObject node = nodeArray.getJSONObject(i);
            NodeImpl newNode = new NodeImpl("Node " + i, node);
            testNodes.add(newNode);
        }
        
        
        for(int j = 0; j < testNodes.size(); j++) {
            
            for(int i = 1; i < testNodes.size() - 1; i++) {
                testNodes.get(j).addDestination(testNodes.get(i), testNodes.get(j).distanceTo(testNodes.get(i)));
            }
        }
        
        graph = new DJKAGraph();
        
        
    }
    
    @Test
    public void TestNodes() {
        for(NodeImpl node : testNodes) {
            System.out.println(node);
            graph.addNode(node);
        }
        
        graph = RefactoredDjkAlgo.calculateShortestPath(graph, testNodes.get(0));
        
        assertNotNull(graph);
        
        
        for(NodeImpl node: graph.getNodes()) {
            System.out.println(node);
        }
        
    }

}
