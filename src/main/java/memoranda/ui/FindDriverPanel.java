package main.java.memoranda.ui;

/**
 * File Name: FindDriverPanel.java
 * Description: A new JFrame window that allows the user to find a driver by ID
 * Author: Tresor Cyubahiro
 * Date: Mar 14th, 2018
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.java.memoranda.models.DriverImpl;
import main.java.memoranda.models.DriverCollection;

/**
 * Class: FindDriverPanel
 */
public class FindDriverPanel extends JFrame {
	
	JLabel idInputLabel;
	GridLayout gridLayout = new GridLayout(5,2, 8, 10);
	BorderLayout borderLayout = new BorderLayout();
	JPanel inputPanel, confirmationPanel;
	JTextField idInputEntry;
	JButton find, cancel;
	private DriverCollection driverCollection;
	
	public FindDriverPanel() {
		super("Find Driver by ID");
		try {
			jbInit();
			
		}catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
	}
	
	
	/**
	 * Method: jbInit
	 * Inputs: None
	 * Returns: Void
	 */
	public void jbInit() {
		setLayout(borderLayout);
		idInputLabel = new JLabel("Enter Driver ID: ", SwingConstants.LEFT);
		idInputEntry = new JTextField(10);
		find = new JButton("Add");
		cancel = new JButton("Cancel");
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputPanel = new JPanel();
		confirmationPanel = new JPanel();
		
		inputPanel.add(idInputLabel);
		inputPanel.add(idInputEntry);

		find = new JButton("Find Driver");
		cancel = new JButton("Cancel");
		
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findDriver(idInputEntry.getText());
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		
		confirmationPanel.add(find);
		confirmationPanel.add(cancel);
		inputPanel.setPreferredSize(new Dimension(600,150));
		inputPanel.setLayout(gridLayout);
		inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		add(inputPanel, BorderLayout.CENTER);
		add(confirmationPanel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	/**
	 * Method: findDriver
	 * Input: String
	 * Return: None
	 * Description: Looks through the driver data set and displays data associated with driver id if found or display an error message
	 */
	private void findDriver(String driverID) {
		DriverCollection driverCollection = new DriverCollection("drivers.json");
		driverCollection.readFromFile();
		driverCollection = new DriverCollection("drivers.json");
		
		if(driverCollection.doesDriverExist(driverID)) {
			System.out.println("Yes");
			DriverImpl driver = (DriverImpl) driverCollection.getDriver(driverID);
			JOptionPane.showMessageDialog(this, "FirstName: "+driver.getFirstName()+"\nLastName: "+driver.getLastName()+
					"\nPhoneNumber: "+driver.getPhoneNumber()+"\nAge: "+driver.getAge()+"\n ");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Driver with ID: "+driverID+" does not exits");
		}
	}

}
