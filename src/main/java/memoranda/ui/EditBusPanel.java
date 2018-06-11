package main.java.memoranda.ui;
/*
 * Author: Tresor Cyubahiro
 * Date: 04.14.2018
 * Description: Panel to enable user to edit existing Bus info
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.java.memoranda.models.BusCollection;

public class EditBusPanel extends JFrame {
    
    JLabel busID;
    JTextField busIDEntry;
    JButton edit, cancel;
    JPanel editPanel, confirmationPanel;
    BorderLayout borderLayout = new BorderLayout();
    private BusCollection busCollection;
    private BusesPanel busPanel; 
    
    public EditBusPanel(BusesPanel busPanel) {
        
        super("Edit Bus");
        this.busPanel = busPanel;
        try {
            jbInit();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
    
    public void jbInit() {
        setLayout(borderLayout);
        busID = new JLabel("Enter Bus ID: ", SwingConstants.CENTER);
        
        busIDEntry = new JTextField(10);
        
        edit = new JButton("Edit");
        cancel = new JButton("Cancel");
        
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        editPanel = new JPanel();
        editPanel.add(busID);
        editPanel.add(busIDEntry);
        
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editBus();
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
     * Method: editBus
     * Input: None
     * Return: None
     */
    private void editBus() {
    		String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
        busCollection = new BusCollection(busCollectionFilePath);
        String response;
        String[] editOptions = {"Name", "Number Of Seats"};
        
        if(busCollection.doesBusExist(busIDEntry.getText())) {
            int option = JOptionPane.showOptionDialog(this, "Select which piece of information you would like to edit",
                    "Edit Bus " + busIDEntry.getText(), 0, JOptionPane.INFORMATION_MESSAGE, null, editOptions, editOptions[0]);
            if(option == 0) {
                response = JOptionPane.showInputDialog(this, "Enter new Name: ");
                busCollection.editBus(busIDEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            } else if(option == 1) {
                response = JOptionPane.showInputDialog(this, "Enter new Number of Seats: ");
                busCollection.editBus(busIDEntry.getText(), response, option);
                JOptionPane.showMessageDialog(this, "Edit Successfully Completed");
            } else {
                JOptionPane.showMessageDialog(this, "Edit Of Bus is Cancelled");
            }
            
            busCollection.exportBusCollection();

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Bus does not exist");
            busIDEntry.setText(null);
        }
        
        busPanel.refreshTable();
    }

}
