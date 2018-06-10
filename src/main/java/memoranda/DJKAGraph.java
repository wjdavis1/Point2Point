package main.java.memoranda;

import java.util.HashSet;
import java.util.Set;

public class DJKAGraph {

    private Set<NodeImpl> nodes = new HashSet<>();
    
    public void addNode(NodeImpl node) {
        nodes.add(node);
    }
    
    public Set<NodeImpl> getNodes(){
        return nodes;
    }
}
