package main.java.memoranda.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.java.memoranda.models.DriverCollection;

import java.io.IOException;

public class EditDriverPanel extends JFrame {
    
    JLabel driverID;
    JTextField driverIDEntry;
    JButton edit, cancel;
    JPanel editPanel, confirmationPanel;
    BorderLayout borderLayout = new BorderLayout();
    private DriverCollection driverCollection;
    private DriversPanel driversPanel;
    
    public EditDriverPanel(DriversPanel driversPanel) {
        super("Edit Driver");
        this.driversPanel = driversPanel;
        try {
            jbInit();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public void jbInit() {
        setLayout(borderLayout);
        driverID = new JLabel("Enter Driver ID: ", SwingConstants.CENTER);
        
        driverIDEntry = new JTextField(10);
        
        edit = new JButton("Edit");
        cancel = new JButton("Cancel");
        
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        editPanel = new JPanel();
        editPanel.add(driverID);
        editPanel.add(driverIDEntry);
        
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editDriver();
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
     * Method: editDriver
     * Input: None
     * Return: None
     * 
     * Description: A private method that is called when the edit
     * Button is pressed, the way this is handled is through various
     * JOptionPanes that ask the user what information they are editing
     */
    private void editDriver() {
        driverCollection = new DriverCollection("drivers.json");
        String response;
        String[] editOptions = {"First Name", "Last Name", "Age", "Phone Number","Cancel"};

        
        if(driverCollection.doesDriverExist(driverIDEntry.getText())) {
            int option = JOptionPane.showOptionDialog(this, "Select which piece of information you would like to edit",
                    "Edit Driver " + driverIDEntry.getText(), 0, JOptionPane.INFORMATION_MESSAGE, null, editOptions, editOptions[0]);
            if(option == 0) {
                response = JOptionPane.showInputDialog(this, "Enter new First Name: ");
                driverCollection.editDriver(driverIDEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            } else if(option == 1) {
                response = JOptionPane.showInputDialog(this, "Enter new Last Name: ");
                driverCollection.editDriver(driverIDEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            } else if(option == 2) {
                response = JOptionPane.showInputDialog(this, "Enter new Age: ");
                driverCollection.editDriver(driverIDEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            } else if(option == 3) {
                response = JOptionPane.showInputDialog(this, "Enter new Phone Number: ");
                driverCollection.editDriver(driverIDEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            } else {
                JOptionPane.showMessageDialog(this, "Edit Of Driver is Cancelled");
            }
            
            try {
                driverCollection.saveToFile();
            }catch(IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Driver does not exist");
            driverIDEntry.setText(null);
        }
        
        driversPanel.refreshTable();
    }

}
