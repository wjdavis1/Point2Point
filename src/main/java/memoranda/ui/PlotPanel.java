package main.java.memoranda.ui;

/**
 * FileName: PlotPanel.java
 * Author: Wesley Davis
 * Created: Mar 27th, 2018
 *  @see https://www.java-forums.org/new-java/7995-how-plot-graph-java-given-samples.html
 *  
 * Description: The majority of this class implementation is take from the source shown above.
 * This class is a basic XY plotter, that displays points on the graph within the X-Y plane.
 * This class has been adjusted to work with our project, but much credit goes to the original
 * implementation found in the source above. 
 */
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.*;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;

import java.util.ArrayList;
import java.util.LinkedList;

import main.java.memoranda.models.NodeImpl;
import main.java.memoranda.models.NodeCollection;
import main.java.memoranda.models.NodeEdge;
import main.java.memoranda.models.DijkstraAlgorithm;

public class PlotPanel extends JPanel {
    
    double xMin = -40.0;
    double xMax = 40.0;
    double yMin = -150;
    double yMax = 150.0;
    final int PAD = 20;
    final boolean DEBUG = false;
    boolean firstTime = true;  // Set at end of setData method.
    
    private JSONArray nodeObjArray;
    private List<NodeImpl> impNodeList;
    private List<NodeEdge> nodeEdges;
    private LinkedList<NodeImpl> shortPath;
    private NodeCollection nodeColl;
    private DijkstraAlgorithm djka;
    
    
    public PlotPanel(JSONObject obj) {
        setBackground(Color.WHITE);
        impNodeList = new ArrayList<>();
        nodeEdges = new ArrayList<>();
        nodeObjArray = obj.getJSONArray("nodes");
        
        for(int i = 0; i < nodeObjArray.length(); i++) {
            JSONObject node = nodeObjArray.getJSONObject(i);
            NodeImpl newNode = new NodeImpl("Point"+i,node);
            impNodeList.add(newNode);
        }
        
        sortNodes(impNodeList);
        
        for(int i = 0; i < impNodeList.size() - 1; i++) {
            for(int j = 1; j < impNodeList.size(); j++) {
                if(!impNodeList.get(i).equals(impNodeList.get(j))) {
                    Double nodeDistance = impNodeList.get(i).distanceTo(impNodeList.get(j));
                    NodeEdge edges = new NodeEdge("Edge " + impNodeList.get(i).getID() + "-" + impNodeList.get(j).getID(),impNodeList.get(i),impNodeList.get(j), nodeDistance);
                    nodeEdges.add(edges);
                }
            }
         }
        
        
        nodeColl = new NodeCollection(impNodeList, nodeEdges);
        
        System.out.println("[DEBUG] From Cartestian Plane");
        for(NodeImpl impNode : nodeColl.getNodes()) {
            System.out.println("[DEBUG] ID: " + impNode.getID() + " LAT: " + impNode.getLat() + " LON: " + impNode.getLon());
        }
        
        djka = new DijkstraAlgorithm(nodeColl);
        
        djka.traverseNodes(impNodeList.get(0));
        int destination = impNodeList.size();
        System.out.println(destination);
        shortPath = djka.getPath(impNodeList.get(destination - 1));
        
        for(NodeImpl node : shortPath) {
            System.out.println(node);
        }
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
     * Method: paintComponent
     * Input: Graphics Object
     * Return none
     * 
     * Description: Overrides the paint method in the panel object.
     * SEE REFERENCE ABOVE.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        double xScale = (w - 2*PAD)/(xMax - xMin);
        double yScale = (h - 2*PAD)/(yMax - yMin);
        if(firstTime)
            System.out.printf("xScale = %.1f  yScale = %.1f%n",
                               xScale, yScale);
        Point2D.Double origin = new Point2D.Double(); // Axes origin.
        Point2D.Double offset = new Point2D.Double(); // Locate data.
        if(xMax < 0) {
            origin.x = w - PAD;
            offset.x = origin.x - xScale*xMax;
        } else if(xMin < 0) {
            origin.x = PAD - xScale*xMin;
            offset.x = origin.x;
        } else {
            origin.x = PAD;
            offset.x = PAD - xScale*xMin;
        }
        if(yMax < 0) {
            origin.y = h - PAD;
            offset.y = origin.y - yScale*yMax;
        } else if(yMin < 0) {
            origin.y = PAD - yScale*yMin;
            offset.y = origin.y;
        } else {
            origin.y = PAD;
            offset.y = PAD - yScale*yMin;
        }
        if(firstTime) {
            System.out.printf("origin = [%6.1f, %6.1f]%n", origin.x, origin.y);
            System.out.printf("offset = [%6.1f, %6.1f]%n", offset.x, offset.y);
        }
 
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, origin.y, w-PAD, origin.y));
        // Draw ordinate.
        g2.draw(new Line2D.Double(origin.x, PAD, origin.x, h-PAD));
        g2.setPaint(Color.red);
        // Mark origin.
        g2.fill(new Ellipse2D.Double(origin.x-2, origin.y-2, 4, 4));
 
        // Plot data.
//        g2.setPaint(Color.blue);
//        for(int i = 0; i < x.length; i++) {
//            double x1 = offset.x + xScale*x[i];
//            double y1 = offset.y + yScale*y[i];
//            if(firstTime)
//                System.out.printf("i = %d  x1 = %6.1f  y1 = %.1f%n", i, x1, y1);
//            g2.fill(new Ellipse2D.Double(x1-2, y1-2, 4, 4));
//            g2.drawString(String.valueOf(i), (float)x1+3, (float)y1-3);
//        }
        
        //Plotting Node Data
        g2.setPaint(Color.BLUE);
        for(NodeImpl node : shortPath) {
            double x1 = offset.x + xScale*node.getLat();
            double y1 = offset.y + yScale*node.getLon();
            
          if(firstTime)
              System.out.printf("i = %s  x1 = %6.1f  y1 = %.1f%n", node.getID(), x1, y1);
          g2.fill(new Ellipse2D.Double(x1-2, y1-2, 4, 4));
          g2.drawString(node.getID(), (float)x1+3, (float)y1-3);
        }
 
        // Draw extreme data values.
        g2.setPaint(Color.black);
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        String s = String.format("%.1f", xMin);
        float width = (float)font.getStringBounds(s, frc).getWidth();
        double x = offset.x + xScale*xMin;
        g2.drawString(s, (float)x, (float)origin.y+lm.getAscent());
        s = String.format("%.1f", xMax);
        width = (float)font.getStringBounds(s, frc).getWidth();
        x = offset.x + xScale*xMax;
        g2.drawString(s, (float)x-width, (float)origin.y+lm.getAscent());
        s = String.format("%.1f", yMin);
        width = (float)font.getStringBounds(s, frc).getWidth();
        double y = offset.y + yScale*yMin;
        g2.drawString(s, (float)origin.x+1, (float)y+lm.getAscent());
        s = String.format("%.1f", yMax);
        width = (float)font.getStringBounds(s, frc).getWidth();
        y = offset.y + yScale*yMax;
        g2.drawString(s, (float)origin.x+1, (float)y);
        if(firstTime)
            System.out.println("------------------------------");
        firstTime = false;
    }
    
    public LinkedList<NodeImpl> getShortPath(){
        return shortPath;
    }
}
