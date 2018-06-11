package main.java.memoranda.ui;
/**
 * File Name: CartesianPlotPanel
 * Author: Wesley Davis
 * Created: Mar 20th, 2018
 * 
 * 
 * @see http://www.dreamincode.net/forums/topic/379672-cartesian-plane/
 * 
 * 
 * Description: This class creates a basic Cartesian Plot for plotting 
 * the route points. The much of the implementation for this class was 
 * taken from the link above and much credit goes to the original writer
 * of the initial class. This class has been augmented to include the plotting
 * of our nodes from an imported json file.
 * 
 * EDIT: This graph has been edited using the JFree chart package. This class now utilizes and
 * xy chart to display the data.
 * EDITED: 04/08/2018
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BasicStroke;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import main.java.memoranda.models.NodeImpl;
import main.java.memoranda.models.RefactoredDjkAlgo;
import main.java.memoranda.models.DJKAGraph;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.json.JSONObject;
import org.json.JSONArray;


public class CartesianPlotPanel extends JPanel {

    private JSONArray nodeObjArray;
    private List<NodeImpl> impNodeList;
    private Set<NodeImpl> shortPath;
    private DJKAGraph dGraph;
    private NodeCoordinateTable nodeTable;
    private DecimalFormat df = new DecimalFormat("#.##");
    double tourRoundDistance;

    public CartesianPlotPanel(JSONObject obj) {
        super(new GridLayout(1,2));
        impNodeList = new ArrayList<>();
        
        nodeObjArray = obj.getJSONArray("nodes");
        dGraph = new DJKAGraph();

        JFreeChart xyChart = ChartFactory.createXYLineChart("",
                "", 
                "", 
                createDataSet(), 
                PlotOrientation.VERTICAL, 
                false, true, false);


        ChartPanel chartPanel = new ChartPanel(xyChart);
        //chartPanel.setPreferredSize(new Dimension(1000,1000));

        final XYPlot plot = xyChart.getXYPlot();

        ValueAxis yAxis = plot.getRangeAxis();
        

        yAxis.setRange(getMinLon() - 0.05, getMaxLon() + 0.05);
        

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));

        plot.setRenderer(renderer);

        nodeTable = new NodeCoordinateTable(impNodeList,tourRoundDistance,getDuration(tourRoundDistance));

        this.add(chartPanel);
        this.add(nodeTable);

    }

    /**
     * createDataSet()
     * Input: None
     * Return: XYDataset
     * 
     * Description: Creates the data set needed for the JFreeChart constructor,
     * this imports the data from the import file and adds it to the appropriate
     * data collections.
     */
    private XYDataset createDataSet() {
        final XYSeries route = new XYSeries("Route");

        for(int i = 0; i < nodeObjArray.length(); i++) {
            JSONObject node = nodeObjArray.getJSONObject(i);
            NodeImpl newNode = new NodeImpl("Point "+i,node);
            impNodeList.add(newNode);
        }

        sortNodes(impNodeList);

        for(int i = 1; i < impNodeList.size(); i++) {
            impNodeList.get(0).addDestination(impNodeList.get(i), impNodeList.get(0).distanceTo(impNodeList.get(i)));
        }

        for(NodeImpl node : impNodeList) {
            System.out.println("[DEBUG] " + node);
            dGraph.addNode(node);
        }

        dGraph = RefactoredDjkAlgo.calculateShortestPath(dGraph, impNodeList.get(0));

        shortPath = dGraph.getNodes();

        for(NodeImpl node : shortPath) {
            route.add(node.getLat(), node.getLon());
        }
        
        tourRoundDistance = getTotalDistance(shortPath);
        
        System.out.println("[DEGUG] Duration: " + df.format(getDuration(tourRoundDistance)));

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(route);

        return dataset;
    }

    /**
     * Method: sortNodes
     * Input: List of Nodes
     * Return: None
     * 
     * Description: Sorts the nodes in the list by latitude.
     */
    public void sortNodes(List<NodeImpl> nodes) {
        int size = nodes.size();

        for(int i = 0; i < size - 1; i++) {
            int min = i;

            for(int j = i + 1; j < size; j++) {
                if(nodes.get(j).getLat() < nodes.get(min).getLat()) {
                    min = j;
                }
            }

            NodeImpl temp = nodes.get(min);
            nodes.set(min, nodes.get(i));
            nodes.set(i, temp);
        }
    }

    /**
     * Method: getShortPath
     * Input: None
     * Return: Linked list of imported nodes
     * 
     * Description: Returns a list of short path nodes to send to the route object to save
     * @return
     */
    public Set<NodeImpl> getShortPath(){
        return shortPath;
    }
    
    public double getTotalDistance(Set<NodeImpl> path) {
        
        double routeLength = 0;
        double totalDistance = 0;
        
        for(NodeImpl node : path) {
            System.out.println(node + " Distance: " + node.getDistance());
            routeLength += node.getDistance();
        }

        totalDistance = ((2*routeLength)/1000)*0.621371;
        
        System.out.println("[DEBUG] Route Length: " + df.format(totalDistance) + " miles.");
        
        return totalDistance;
    }
    
    public double getDuration(double tourDistance) {
        double busSpeed = 30;
        
        double duration = tourDistance / busSpeed;
        
        if(duration < 1) {
            duration = duration * 60;
        }
        
        return duration;
    }

    /**
     * Method: getMaxLon()
     * Input: None
     * Return: Double
     * 
     * Description: Goes through the node imported data and determines the maximum longitude
     * for y-axis range purposes.
     */
    private Double getMaxLon() {

        double max = impNodeList.get(0).getLon();

        for(int i = 0; i < impNodeList.size(); i++) {
            if(impNodeList.get(i).getLon() > max){
                max = impNodeList.get(i).getLon();
            }
        }

        return max;
    }

    /**
     * Method: getMinLon()
     * Input: None
     * Return: Double
     * 
     * Description: Goes through the node imported data and determines the minimum longitude
     * for y-axis range purposes
     */
    private Double getMinLon() {
        double min = impNodeList.get(0).getLon();

        for(int i = 0 ; i < impNodeList.size(); i++) {
            if(impNodeList.get(i).getLon() < min) {
                min = impNodeList.get(i).getLon();
            }
        }

        return min;
    }

}
