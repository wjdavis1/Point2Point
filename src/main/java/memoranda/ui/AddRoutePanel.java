package main.java.memoranda.ui;

/**
 * File Name: AddRoutePanel.java
 * Description: A new JFrame window that allows the user to add Route information
 * Author: James Ortner
 * Date: Feb 21st, 2018
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;

import main.java.memoranda.models.RouteImpl;
import main.java.memoranda.models.RouteCollection;
import java.util.Set;
import main.java.memoranda.models.NodeImpl;

/**
 * Class: AddRoutePanel Description: SEE ABOVE FILE DESCRIPTION
 */
public class AddRoutePanel extends JFrame {
    JLabel routeId;
    JLabel startPoint;
    JLabel name;
    JLabel length;
    JLabel duration;
    GridLayout gridLayout = new GridLayout(5, 2, 8, 10);
    GridLayout addInformation = new GridLayout(2, 1, 5, 5);
    BorderLayout borderLayout = new BorderLayout();
    FlowLayout flowLayout = new FlowLayout();
    JPanel inputPanel;
    JPanel confirmationPanel;
    JTextField routeIdEntry;
    JTextField startPointEntry;
    JTextField nameEntry;
    JTextField lengthEntry;
    JTextField durationEntry;
    JButton add;
    JButton cancel;
    private RoutePanel routePanel;
    private RouteCollection routeCollection;
    private GoogleStaticMap plotPanel;
    private Set<NodeImpl> nodes;
    private DecimalFormat df = new DecimalFormat("#.##");

    public AddRoutePanel() {
        super("Add Route Information");
        try {
            jbInit();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public AddRoutePanel(RoutePanel routePanel) {
		super("Add Route Information");
		this.routePanel = routePanel;
		
		try {
			jbInit();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}	
	}

    public AddRoutePanel(Set<NodeImpl> nodes) {
        super("Add Route Information");
        try {
            jbInit();
            this.nodes = nodes;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public AddRoutePanel(Set<NodeImpl> nodes, GoogleStaticMap plotPanel) {
        super("Add Route Information");
        try {
            this.nodes = nodes;
            this.plotPanel = plotPanel;
            jbInit2();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Method: jbInit Inputs: None Returns: Void Description: Following convention
     * from other frame initializers in this program, this method initializes all of
     * the components for the Frame while also initializing action listeners for the
     * buttons associated with creating a route.
     */
    public void jbInit() {

        this.setLayout(borderLayout);
        routeId = new JLabel("Route ID: ", SwingConstants.LEFT);
        startPoint = new JLabel("Start Point: ", SwingConstants.LEFT);
        name = new JLabel("Route Name: ", SwingConstants.LEFT);
        length = new JLabel("Route Length: ", SwingConstants.LEFT);
        duration = new JLabel("Route Duration: ", SwingConstants.LEFT);

        routeIdEntry = new JTextField(10);
        startPointEntry = new JTextField(10);
        nameEntry = new JTextField(10);
        lengthEntry = new JTextField(10);
        durationEntry = new JTextField(10);

        add = new JButton("Add");
        cancel = new JButton("Cancel");

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputPanel = new JPanel();
        confirmationPanel = new JPanel();

        inputPanel.add(routeId);
        inputPanel.add(routeIdEntry);
        inputPanel.add(name);
        inputPanel.add(nameEntry);
        inputPanel.add(startPoint);
        inputPanel.add(startPointEntry);
        inputPanel.add(length);
        inputPanel.add(lengthEntry);
        inputPanel.add(duration);
        inputPanel.add(durationEntry);

        add = new JButton("Create Route");
        cancel = new JButton("Cancel");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addRoute();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });

        confirmationPanel.add(add);
        confirmationPanel.add(cancel);
        inputPanel.setPreferredSize(new Dimension(600, 150));
        inputPanel.setLayout(gridLayout);
        inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(inputPanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void jbInit2() {

        this.setLayout(borderLayout);
        routeId = new JLabel("Route ID: ", SwingConstants.LEFT);
        startPoint = new JLabel("Start Point: ", SwingConstants.LEFT);
        name = new JLabel("Route Name: ", SwingConstants.LEFT);
        length = new JLabel("Route Length: ", SwingConstants.LEFT);
        duration = new JLabel("Route Duration: ", SwingConstants.LEFT);

        routeIdEntry = new JTextField(10);
        startPointEntry = new JTextField(10);
        nameEntry = new JTextField(10);
        lengthEntry = new JTextField(10);
        durationEntry = new JTextField(10);

        add = new JButton("Add");
        cancel = new JButton("Cancel");

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputPanel = new JPanel();
        confirmationPanel = new JPanel();
        
        lengthEntry.setText(df.format(plotPanel.getTotalDistance()));
        durationEntry.setText(df.format(plotPanel.getDuration(plotPanel.getTotalDistance())));

        inputPanel.add(routeId);
        inputPanel.add(routeIdEntry);
        inputPanel.add(name);
        inputPanel.add(nameEntry);
        inputPanel.add(startPoint);
        inputPanel.add(startPointEntry);
        inputPanel.add(length);
        inputPanel.add(lengthEntry);
        inputPanel.add(duration);
        inputPanel.add(durationEntry);

        add = new JButton("Create Route");
        cancel = new JButton("Cancel");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addRoute();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });

        confirmationPanel.add(add);
        confirmationPanel.add(cancel);
        inputPanel.setPreferredSize(new Dimension(600, 150));
        inputPanel.setLayout(gridLayout);
        inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(inputPanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Method: addRoute Description: Adds a route to the route collection stored in
     * a file within the system
     */
    private void addRoute() {
        RouteImpl newRoute = new RouteImpl(routeIdEntry.getText(),
                startPointEntry.getText(), nameEntry.getText(),
                Double.parseDouble(lengthEntry.getText()), 
                Double.parseDouble(durationEntry.getText()), nodes);
        routeCollection = new RouteCollection("routes.json");
        if (routeCollection.addRoute(newRoute)) {
            JOptionPane.showMessageDialog(this, "Route " + newRoute.getRouteId() + " has been created! ");
            System.out.println("Route ID: " + newRoute.getRouteId());
            System.out.println("Start Point: " + newRoute.getStartPoint());
            System.out.println("Name: " + newRoute.getName());
            System.out.println("Length: " + newRoute.getLength());
            System.out.println("Duration: " + newRoute.getDuration());
            try {
                routeCollection.saveToFile();
                System.out.println("[DEBUG] Save successfull");
            }
            catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Route cannot be added, Route " + newRoute.getRouteId() + " Already Exists");
            routeIdEntry.setText(null);
            startPointEntry.setText(null);
            nameEntry.setText(null);
            lengthEntry.setText(null);
            durationEntry.setText(null);
        }
		
		routePanel.refreshTable();

    }

}
