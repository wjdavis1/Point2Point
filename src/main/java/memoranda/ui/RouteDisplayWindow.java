package main.java.memoranda.ui;

/**
 * File Name: RouteDisplayWindow.java
 * Author: Wesley Davis
 * Created: Mar 20th, 2018
 * 
 * Description: Creates a separate window for users to see the plotted points on the graph based on
 * on the imported nodes specified by the selected file by user.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.java.memoranda.models.NodeImpl;
import main.java.memoranda.models.RouteImpl;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class RouteDisplayWindow extends JFrame{

    JToolBar routeToolBar;
    JButton saveRoute,newRoute,cancel;
    ImageIcon save, newImport, cancelRoute;
    CartesianPlotPanel plot;
    AddRoutePanel addRoute;
    JFileChooser importRoute;
    JSONObject nodeObj;
    RouteImpl route;
    FileInputStream in;
    private GoogleStaticMap gsm;
    
    
    public RouteDisplayWindow(JSONObject obj) {
        super("Displayed Route");
        try {
            nodeObj = obj;
            jbInit();
        }catch(Exception ex) {
            System.out.println("[DEBUG] " + ex.getMessage());
        }
    }
    
    public RouteDisplayWindow(RouteImpl route) {
        super("Displayed Route");
        try {
            this.route = route;
            jbInit();
        }catch(Exception ex) {
            System.out.println("[DEBUG] " + ex.getMessage());
        }
    }
    private void jbInit() throws Exception{
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        routeToolBar = new JToolBar();
        
        //plot = new CartesianPlotPanel(nodeObj);
        gsm = new GoogleStaticMap(nodeObj);
        //plotPanel = new PlotPanel(nodeObj);
        
        save = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_new.png"));
        newImport = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_new_sub.png"));
        cancelRoute = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_remove.png"));
        
        saveRoute = new JButton("Save Route", save);
        newRoute = new JButton("Import New Route", newImport);
        cancel = new JButton("Cancel", cancelRoute);
        
        saveRoute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveRouteInformation();
            }
        });
        
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        routeToolBar.add(saveRoute);
        //routeToolBar.add(newRoute);
        routeToolBar.add(cancel);
        
        routeToolBar.setFloatable(false);
        
        add(routeToolBar, BorderLayout.NORTH);
        add(gsm);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        
    }
    
    /**
     * Method: saveRouteInformation
     * Input: None
     * Return: None
     * 
     * Description: Saves route information to an existing file
     */
    private void saveRouteInformation() {
        addRoute = new AddRoutePanel(gsm.getShortPath(),gsm);
        Set<NodeImpl> nodes = new HashSet<NodeImpl>();
        JSONArray savedNodes = nodeObj.getJSONArray("nodes");
        System.out.println(savedNodes.toString());
       
        for (int i = 0; i < savedNodes.length(); i++) {
			//System.out.println(savedNodes.get(i).toString());
			JSONObject nodeData = (JSONObject)savedNodes.get(i);
			//System.out.println(nodeData.toString());
			String id = nodeData.getString("id");
			//System.out.println(id);
			NodeImpl newNode = new NodeImpl(id,nodeData);
			nodes.add(newNode);
		}
        
        //addRoute = new AddRoutePanel(nodes,plot);
        dispose();
    }
}
