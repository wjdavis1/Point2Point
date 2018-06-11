package main.java.memoranda.ui;

/**
 * File Name: AddDriverPanel.java
 * Description: A new JFrame window that allows the user to add Driver information
 * Author: Wesley Davis
 * Date: Feb 18th, 2018
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.java.memoranda.models.DriverImpl;
import main.java.memoranda.models.DriverCollection;

import java.io.IOException;
/**
 * Class: AddDriverPanel
 * Description: SEE ABOVE FILE DESCRIPTION
 */
public class AddDriverPanel extends JFrame {
	
	JLabel firstName,lastName, driverAge, driverID, phoneNumber;
	GridLayout gridLayout = new GridLayout(5,2, 8, 10);
	GridLayout addInformation =  new GridLayout(2,1,5,5);
	BorderLayout borderLayout = new BorderLayout();
	FlowLayout flowLayout = new FlowLayout();
	JPanel inputPanel, confirmationPanel;
	JTextField firstNameEntry, lastNameEntry, driverAgeEntry, driverIDEntry, driverPhone;
	JButton add, cancel;
	private DriverCollection driverCollection;
	private DriversPanel driversPanel;
	
	public AddDriverPanel(DriversPanel driverPane) {
		super("Add Driver Information");
		this.driversPanel = driverPane;
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
	 * Description: Following convention from other frame initializers in this program, this
	 * method initializes all of the components for the Frame while also initializing action
	 * listeners for the buttons associated with creating a driver.
	 */
	public void jbInit() {
		
		setLayout(borderLayout);
		//setResizable(false);
		firstName = new JLabel("First Name: ", SwingConstants.LEFT);
		lastName = new JLabel("Last Name: ", SwingConstants.LEFT);
		driverAge = new JLabel("Age: ", SwingConstants.LEFT);
		driverID = new JLabel("ID: ", SwingConstants.LEFT);
		phoneNumber = new JLabel("Phone #: ", SwingConstants.LEFT);
		
		firstNameEntry = new JTextField(10);
		lastNameEntry = new JTextField(10);
		driverAgeEntry = new JTextField(10);
		driverIDEntry = new JTextField(10);
		driverPhone = new JTextField(10);
		
		add = new JButton("Add");
		cancel = new JButton("Cancel");
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputPanel = new JPanel();
		confirmationPanel = new JPanel();
		
		inputPanel.add(firstName);
		inputPanel.add(firstNameEntry);
		inputPanel.add(lastName);
		inputPanel.add(lastNameEntry);
		inputPanel.add(driverAge);
		inputPanel.add(driverAgeEntry);
		inputPanel.add(driverID);
		inputPanel.add(driverIDEntry);
		inputPanel.add(phoneNumber);
		inputPanel.add(driverPhone);
		
		
		add = new JButton("Create Driver");
		cancel = new JButton("Cancel");
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addDriver();
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		
		confirmationPanel.add(add);
		confirmationPanel.add(cancel);
		//confirmationPanel.setLayout(addInformation);
		inputPanel.setPreferredSize(new Dimension(600,150));;
		inputPanel.setLayout(gridLayout);
		inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		add(inputPanel, BorderLayout.CENTER);
		add(confirmationPanel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	/**
	 * Method: addDriver
	 * Input: None
	 * Return: None
	 * Description: Adds a driver to the driver collection stored in a file within the system
	 */
	private void addDriver() {
		DriverImpl newDriver = new DriverImpl(firstNameEntry.getText(),lastNameEntry.getText(),
				driverIDEntry.getText(),Integer.parseInt(driverAgeEntry.getText()),driverPhone.getText());
		//DriverCollection driverCollection = new DriverCollection("drivers.json");
		//driverCollection.readFromFile();
		driverCollection = new DriverCollection("drivers.json");
		
		if(driverCollection.addDriver(newDriver)) {
			JOptionPane.showMessageDialog(this, "Driver: " + newDriver.getFullName() + " has been created! ");
			System.out.println("[DEBUG] Driver Full Name: " + newDriver.getFullName());
			System.out.println("[DEBUG] Driver ID: " + newDriver.getDriverId());
			System.out.println("[DEBUG] Age: " + newDriver.getAge());
			System.out.println("[DEBUG] Phone #: " +  newDriver.getPhoneNumber());
			try {
				driverCollection.saveToFile();
				System.out.println("[DEBUG] Save successfull");
			}catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}
			dispose();
		}else {
			JOptionPane.showMessageDialog(this, "Driver cannot be added, Driver Already Exists");
			firstNameEntry.setText(null);
			lastNameEntry.setText(null);
			driverIDEntry.setText(null);
			driverAgeEntry.setText(null);
			driverPhone.setText(null);
		}
		
		driversPanel.refreshTable();
	
	}

}
