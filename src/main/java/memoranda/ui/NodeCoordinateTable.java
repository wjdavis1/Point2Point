package main.java.memoranda.ui;

import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.List;


import main.java.memoranda.models.NodeImpl;


public class NodeCoordinateTable extends JPanel {

    private JTable nodeTable,informationTable;
    private List<NodeImpl> nodes;
    private String[] columnNames = {"Latitude","Longitude"};
    private String[] routeDescription = {"Tour Length(miles)", "Tour Duration(min)"};
    private Object[][] nodeData,informationData = new Object[1][2];
    private JScrollPane nodePane, infoPane;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    private int index = 0;
    private double duration;
    private double length;


    public NodeCoordinateTable(List<NodeImpl> nodes,double duration,double length) {
        super(new GridLayout(2,0));
        this.nodes = nodes;
        this.duration = duration;
        this.length = length;

        nodeData = new Object[nodes.size()][2];

        for(NodeImpl node : nodes) {
            nodeData[index][0] = node.getLat();
            nodeData[index][1] = node.getLon();
            index++;
        }
        
        
        informationData[0][0] = df.format(duration);
        informationData[0][1] = df.format(length);
        

        nodeTable = new JTable(nodeData, columnNames);
        informationTable = new JTable(informationData, routeDescription);
        
        nodePane = new JScrollPane(nodeTable);
        infoPane = new JScrollPane(informationTable);
        
        //JScrollPane scrollPane = new JScrollPane(table);

        add(nodePane);
        add(infoPane);
    }
}
