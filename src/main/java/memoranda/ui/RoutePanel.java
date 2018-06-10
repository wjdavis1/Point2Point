package main.java.memoranda.ui;

/**
 * File Name: RoutePanel.java
 * Description: The main panel for Route information such as finding a route, creating a route,
 * and displaying a list of current routes.
 * Author: James Ortner
 * Date: Feb 21st, 2018
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.*;

import main.java.memoranda.RouteImpl;

import main.java.memoranda.RouteCollection;
import org.json.*;

/**
 * 
 * Class: RoutePanel Description: SEE FILE DESCRIPTION ABOVE
 *
 */
public class RoutePanel extends JPanel {
    /*
     * BorderLayout borderLayout = new BorderLayout(); GridLayout information = new
     * GridLayout(5,2,5,8); JPanel routeInformation = new JPanel(); JPanel routeList
     * = new JPanel(); JPanel emptyPanel = new JPanel(); JToolBar routeToolBar = new
     * JToolBar(); JButton addRoute,removeRoute, findRoute, editRoute, refreshList;
     * JSplitPane routeInfoPane, routePane; ImageIcon add, remove, find, edit,
     * refresh; JLabel destination, routeId, startPoint, destinationText,
     * routeIdText, startPointText; AddRoutePanel newRoute; RouteTable newTable;
     * RemoveRoute deleteRoute; EditRoutePanel editRouteInformation;
     */
    RouteCollection routeCollection = new RouteCollection("routes.json");
    JSplitPane horizPane;
    RouteTable routeTable = new RouteTable();
    AddRoutePanel newRoute;
    RemoveRoute deleteRoute;
    EditRoutePanel editRouteInformation;
    FileInputStream in;
    RouteDisplayWindow routeDisplay;
    JFileChooser fileChooser;
	
	// The work panel is the parent panel for Route and many other
	// panels
	WorkPanel parentPanel = null;
	
	public RoutePanel(WorkPanel _parentPanel){
		try {
		    parentPanel = _parentPanel;
			jbInit();
		}catch(Exception ex) {
			new ExceptionDialog(ex);
		}
	}
	
	/**
	 * Method: jbInit
	 * Input: None
	 * Return: Void
	 * @throws Exception
	 * @description Following naming convention found across the program contain this method to instantiate
	 * the panel 
	 */
	void jbInit() throws Exception{
	    setLayout(new BorderLayout());
        ImageIcon add;
        ImageIcon remove;
        ImageIcon edit;
        ImageIcon refresh;
        /*Future Implementation: 
        ImageIcon find;*/
        ImageIcon importFile;
        JButton addRoute;
        JButton deleteRoute;
        JButton editRoute;
        JButton refreshRouteB;
        JButton importFileBtn;
        JToolBar routeToolbar;

   

        add = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_new.png"));
        remove = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_remove.png"));
        edit = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/editproject.png"));
        refresh = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png"));
        importFile = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/import.png"));
        //Possible Future Implementation: 
        //find = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/help.png"));
        
        //addButtonListeners for Add/Remove/Edit
       
        addRoute = new JButton("Add Route", add);
        deleteRoute = new JButton("Remove Route", remove);
        editRoute = new JButton("Edit Route", edit);
        refreshRouteB = new JButton("View All Routes", refresh);
        importFileBtn = new JButton("Import JSON Nodes", importFile);
        
        addRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });

        
        deleteRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });
    
        editRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

        
        refreshRouteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        
        
        importFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selectFile();
            }
        });
        
        //Add Toolbar buttons
        JPanel tablePanel = new JPanel();
        tablePanel.add(routeTable);
        horizPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        horizPane.setRightComponent(tablePanel);
        horizPane.setLeftComponent(new JPanel());
        horizPane.setDividerLocation(250);
        
        routeToolbar = new JToolBar();
        routeToolbar.setFloatable(false);
        routeToolbar.add(addRoute);
        routeToolbar.add(deleteRoute);
        routeToolbar.add(editRoute);
        routeToolbar.add(refreshRouteB);
        routeToolbar.add(importFileBtn);
        
        //Add Toolbar and Horizontal SplitPane

        add(routeToolbar, BorderLayout.NORTH);
        add(horizPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Method: newRoutePanel Input: None Return: Void Description: Creates a new Add
     * Route Panel when the add route button is pressed
     */
    public void add() {
        newRoute = new AddRoutePanel(this);
    }

    /**
     * Method: remove Input: none Return: void/none
     * <p>
     * 
     * Description: Creates a new RemoveRoute JFrame that accepts user input in the
     * form of the RouteId and removes from there.
     */
    private void remove() {
        deleteRoute = new RemoveRoute(this);
    }

    /**
     * Method: edit Input: None Return: none
     * <p>
     * 
     * Description: Crates a new EditRoutePanel that allows the user to edit
     * information on a route by routeId.
     */
    private void edit() {
        editRouteInformation = new EditRoutePanel(this);
    }

    public void refreshTable() {
        JPanel tablePanel = new JPanel();
        RouteTable freshTable = new RouteTable();
        tablePanel.add(freshTable);
        horizPane.setRightComponent(tablePanel);
        horizPane.setLeftComponent(new JPanel());
        horizPane.setDividerLocation(250);
    }
    
    /**
     * Method: selectFile()
     * Input: None
     * Return: None
     * 
     * Description: Executes the File Selector JFrame to find a file to import
     */
    private void selectFile() {

        fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Choose JSON File");
        int returnVal = fileChooser.showOpenDialog(this);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File nodeFile = fileChooser.getSelectedFile();
            System.out.println("[DEBUG] " + nodeFile.getName());
            try {
                in = new FileInputStream(nodeFile);
                JSONObject obj = new JSONObject(new JSONTokener(in));
                in.close();
                routeDisplay = new RouteDisplayWindow(obj);
                
            }catch(IOException ioe) {
                System.out.println("[DEBUG] " + ioe.getMessage());
            }
        } 
    }

}