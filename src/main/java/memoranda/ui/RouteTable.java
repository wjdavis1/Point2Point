package main.java.memoranda.ui;

/** Description: A table to display the contents of the RouteCollection Hashmap on the Route Panel
* @author: James Ortner
* Date: February 28, 2018
* 
*/
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import java.util.HashMap;

import main.java.memoranda.RouteCollection;
import main.java.memoranda.NodeImpl;
import main.java.memoranda.Route;

public class RouteTable extends JPanel {

    private JTable table;
    private HashMap<String,Route> routeCollection;
    private String[] columnNames = {"Route ID", "Start Point", "Route Name", "Length (miles)", "Duration (minutes)"};
    private Object[][] data;
    private GoogleStaticMap gsm;
    private int index = 0;


    public RouteTable() {
        super(new GridLayout(1,0));
        
        routeCollection = RouteCollection.getRoutes();
        data = new Object[routeCollection.keySet().size()][columnNames.length];
        
        for(String routeId : routeCollection.keySet()) {
            Route route = routeCollection.get(routeId);
            data[index][0] = route.getRouteId();
            data[index][1] = route.getStartPoint();
            data[index][2] = route.getName();
            data[index][3] = route.getLength();
            data[index][4] = route.getDuration();
            index++;
        }

        index = 0;

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000,700));
        table.setFillsViewportHeight(true);
        
        table.setRowSelectionAllowed(true);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if(SwingUtilities.isLeftMouseButton(me)) {
                    Point point = me.getPoint();
                    int column = 0;
                    int row = table.rowAtPoint(point);

                    String routeID = (String) table.getValueAt(row, column);

                    routeCollection = RouteCollection.getRoutes();

                    System.out.println("[DEBUG] " + routeID);
                    Route selectedRoute = routeCollection.get(routeID);
                    System.out.println("[DEBUG] Selected Route: " + selectedRoute.getName());
                    if(selectedRoute.getRouteNodes().size() == 0) {
                        JOptionPane.showMessageDialog(table, "Nodes do Not Exist", "Nodes Not Found", JOptionPane.ERROR_MESSAGE);
                    } else {
                        gsm = new GoogleStaticMap(selectedRoute.getRouteNodes(),selectedRoute.getLength(),selectedRoute.getDuration());
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }  
}