package main.java.memoranda.ui;
/*
 * Author: Tresor Cyubahiro
 * SER 316
 * Frankfut
 * Date: 04.14.2018
 * Panel For removing a bus
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.java.memoranda.models.BusImpl;
import main.java.memoranda.models.BusCollection;

public class RemoveBus extends JFrame  {
    JLabel busID;
    JTextField busIDEntry;
    JButton remove, cancel;
    JPanel removePanel, confirmationPanel;
    BorderLayout borderLayout = new BorderLayout();
    private BusCollection busCollection;
    private BusesPanel busPanel;
    
    public RemoveBus(BusesPanel busPanel) {
        super("Remove Bus");
        this.busPanel = busPanel;
        try {
            jbInit();
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void jbInit() {
        setLayout(borderLayout);
        busID = new JLabel("Enter Bus ID: ", SwingConstants.CENTER);
        
        busIDEntry = new JTextField(10);
        
        remove = new JButton("Remove");
        cancel = new JButton ("Cancel");
        
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        removePanel = new JPanel();
        removePanel.add(busID);
        removePanel.add(busIDEntry);
        
        confirmationPanel = new JPanel();
        
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeBus();
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
    /*
     * Method: removeBus
     * Return: none
     * Description: Method to remove bus from storage
     */
    private void removeBus() {
    		String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
        busCollection = new BusCollection(busCollectionFilePath);
        System.out.println(busCollection.doesBusExist(busIDEntry.getText()));
        if(busCollection.doesBusExist(busIDEntry.getText())) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you wish to delete this bus?", "Remove Bus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.YES_OPTION) {
            		BusImpl bus = busCollection.getBus(Integer.parseInt(busIDEntry.getText()));
                busCollection.removeBus(bus);
                busCollection.exportBusCollection();
                JOptionPane.showMessageDialog(this, "Bus has been removed");
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this," Bus is not deleted!");
                busIDEntry.setText(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bus does not exist");
            busIDEntry.setText(null);
        }
        
        busPanel.refreshTable();
    }
}
