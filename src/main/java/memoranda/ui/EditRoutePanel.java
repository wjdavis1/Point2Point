package main.java.memoranda.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.java.memoranda.models.RouteCollection;

import java.io.IOException;

public class EditRoutePanel extends JFrame {

    JLabel routeId;
    JTextField routeIdEntry;
    JButton edit;
    JButton cancel;
    JPanel editPanel;
    JPanel confirmationPanel;
    BorderLayout borderLayout = new BorderLayout();
    private RouteCollection routeCollection;
    private RoutePanel routePanel;
    
    public EditRoutePanel(RoutePanel routePanel) {
        super("Edit Route");
        this.routePanel = routePanel;
        
        try {
            jbInit();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    /**
     * Method: jbInit Input: None Return: None
     * 
     * Description: method for initializing Edit Route popup.
     */
    public void jbInit() {
        setLayout(borderLayout);
        routeId = new JLabel("Enter Route ID: ", SwingConstants.CENTER);

        routeIdEntry = new JTextField(10);

        edit = new JButton("Edit");
        cancel = new JButton("Cancel");

        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        editPanel = new JPanel();
        editPanel.add(routeId);
        editPanel.add(routeIdEntry);

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editRoute();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });

        confirmationPanel = new JPanel();
        confirmationPanel.add(edit);
        confirmationPanel.add(cancel);

        add(editPanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Method: editRoute Input: None Return: None
     * 
     * Description: A private method that is called when the edit Button is pressed,
     * the way this is handled is through various JOptionPanes that ask the user
     * what information they are editing.
     */
    private void editRoute() {
        routeCollection = new RouteCollection("routes.json");
        String response;
        String[] editOptions = { "Start Point", "Name", "Length", "Duration" };

        if (routeCollection.doesRouteExist(routeIdEntry.getText())) {
            int option = JOptionPane.showOptionDialog(this, 
                    "Select which piece of information you would like to edit",
                    "Edit Route " + routeIdEntry.getText(), 0, 
                    JOptionPane.INFORMATION_MESSAGE, null, editOptions,
                    editOptions[0]);
            if (option == 0) {
                response = JOptionPane.showInputDialog(this, "Enter new Start Point: ");
                routeCollection.editRoute(routeIdEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            }
            else if (option == 1) {
                response = JOptionPane.showInputDialog(this, "Enter new Name: ");
                routeCollection.editRoute(routeIdEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            }
            else if (option == 2) {
                response = JOptionPane.showInputDialog(this, "Enter new Length: ");
                routeCollection.editRoute(routeIdEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            }
            else if (option == 3) {
                response = JOptionPane.showInputDialog(this, "Enter new Duration: ");
                routeCollection.editRoute(routeIdEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            }
            else {
                JOptionPane.showMessageDialog(this, "Edit Of Route is Cancelled");
            }

            try {
                routeCollection.saveToFile();
            }
            catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Route does not exist");
            routeIdEntry.setText(null);
        }
        
        routePanel.refreshTable();
    }

}
