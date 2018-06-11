package main.java.memoranda.ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.java.memoranda.models.DriverCollection;

import java.io.IOException;

public class RemoveDriver extends JFrame  {
    JLabel driverID;
    JTextField driverIDEntry;
    JButton remove, cancel;
    JPanel removePanel, confirmationPanel;
    BorderLayout borderLayout = new BorderLayout();
    private DriverCollection driverCollection;
    private DriversPanel driversPanel;
    
    public RemoveDriver(DriversPanel driversPanel) {
        super("RemoveDriver");
        this.driversPanel = driversPanel;
        try {
            jbInit();
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void jbInit() {
        setLayout(borderLayout);
        driverID = new JLabel("Enter Driver ID: ", SwingConstants.CENTER);
        
        driverIDEntry = new JTextField(10);
        
        remove = new JButton("Remove");
        cancel = new JButton ("Cancel");
        
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        removePanel = new JPanel();
        removePanel.add(driverID);
        removePanel.add(driverIDEntry);
        
        confirmationPanel = new JPanel();
        
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeDriver();
            }
        });
        
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        confirmationPanel.add(remove);
        confirmationPanel.add(cancel);
        
        removePanel.setPreferredSize(new Dimension(300,150));
        add(removePanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void removeDriver() {
        driverCollection = new DriverCollection("drivers.json");
        if(driverCollection.doesDriverExist(driverIDEntry.getText())) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you wish to delete the driver?", "Remove Driver", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.YES_OPTION) {
                driverCollection.removeDriver(driverIDEntry.getText());
                try {
                    driverCollection.saveToFile();
                }catch(IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
                JOptionPane.showMessageDialog(this, "Driver has been removed");
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this," Driver is not deleted!");
                driverIDEntry.setText(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Driver does not exist");
            driverIDEntry.setText(null);
        }
        
        driversPanel.refreshTable();
    }
}
