package main.java.memoranda.ui;
/**
 * File Name: DriversPanel.java
 * Description: The main panel for Driver information such as finding a driver, creating a driver,
 * and displaying a list of current drivers.
 * Author: Wesley Davis
 * Date: Feb 18th, 2018
 * 
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * Class: Drivers Panel
 * Description: SEE FILE DESCRIPTION ABOVE
 *
 */
public class DriversPanel extends JPanel {
	
	BorderLayout borderLayout = new BorderLayout();
	GridLayout information = new GridLayout(5,2,5,8);
	JPanel driverInformation = new JPanel();
	JPanel driverList = new JPanel();
	JPanel emptyPanel = new JPanel();
	JToolBar driversToolBar = new JToolBar();
	JButton addDriver,removeDriver, findDriver,editDriver,refreshList;
	JSplitPane driverInfoPane,driverPane;
	ImageIcon add, remove, find, edit,refresh;
	JLabel firstName, lastName, driverID, age,driverImage,phoneNumber, first, last, id, driverAge, driverPhoneNumber;
	AddDriverPanel newDriver;
	FindDriverPanel findADriver;
	DriverTablePanel newTable;
	RemoveDriver deleteDriver;
	EditDriverPanel editDriverInformation;
	
	// The work panel is the parent panel for Driver and many other
	// panels
	WorkPanel parentPanel = null;
	
	public DriversPanel(WorkPanel _parentPanel){
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
		System.out.println("[DEBUG] Driver Panel has Been Created!");
		
		this.setLayout(borderLayout);
		add = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_new.png"));
		remove = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_remove.png"));
		find = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/help.png"));
		edit = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/editproject.png"));
		refresh = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png"));
		
		addDriver = new JButton("Add Driver", add);
		removeDriver = new JButton("Remove Driver", remove);
		findDriver = new JButton("Find Driver", find);
		editDriver = new JButton("Edit Driver", edit);
		refreshList = new JButton("View All Drivers", refresh);
		
		firstName = new JLabel("First Name: ");
		lastName = new JLabel("Last Name: ");
		driverID = new JLabel("Driver ID: ");
		age = new JLabel("Age: ");
		phoneNumber = new JLabel("Phone #: ");
		
		first = new JLabel("John");
		last = new JLabel("Smith");
		id = new JLabel("123456");
		driverAge = new JLabel("32");
		driverPhoneNumber = new JLabel("(555)-555-5555");
		
		first.setHorizontalTextPosition(SwingConstants.RIGHT);

		addDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newDriverPanel();
			}
		});
		
		refreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		
		removeDriver.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        remove();
		    }
		});
		
		editDriver.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        edit();
		    }
		});
		
		findDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDriverPanel();
				
			}
		});
		
		driversToolBar.setFloatable(false);
		driversToolBar.addSeparator(new Dimension(8,24));
		driversToolBar.addSeparator(new Dimension(8,24));
		driversToolBar.add(addDriver);
		driversToolBar.add(removeDriver);
		driversToolBar.add(editDriver);
		driversToolBar.addSeparator();
		driversToolBar.add(findDriver);
		driversToolBar.addSeparator();
		driversToolBar.add(refreshList);

		driverInformation.setLayout(information);
		driverInformation.add(firstName);
		driverInformation.add(first);
		driverInformation.add(lastName);
		driverInformation.add(last);
		driverInformation.add(age);
		driverInformation.add(driverAge);
		driverInformation.add(driverID);
		driverInformation.add(id);
		driverInformation.add(phoneNumber);
		driverInformation.add(driverPhoneNumber);
		
		newTable = new DriverTablePanel();
	
		//driverList.add(new JLabel("Table of Drivers Goes Here", JLabel.CENTER));

		driverList.add(newTable);
		
		driverInfoPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,driverInformation, emptyPanel);
		driverPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, driverInfoPane,driverList);
		driverPane.setDividerLocation(250);
		driverPane.setOneTouchExpandable(false);
		this.add(driversToolBar, BorderLayout.NORTH);
		this.add(driverPane, BorderLayout.CENTER);
	}
	/**
	 * Method: newDriverPanel
	 * Input: None
	 * Return: Void
	 * Description: Creates a new Add Driver Panel when the add driver button is pressed
	 */
	private void newDriverPanel() {
		newDriver = new AddDriverPanel(this);
	}
	
	/* Author: Tresor Cyubahiro
	 * Method: findDriverPanel
	 * Input: None
	 * Return: Void
	 * Description: Finds a driver whose ID is provided by the user
	 */
	private void findDriverPanel() {
		findADriver = new FindDriverPanel();
	}
	/**
	 * Method: remove
	 * Input: none
	 * Return: void/none
	 * 
	 * Description: Creates a new RemoveDriver JFrame that accepts user input in the form
	 * of the DriverID and removes from there.
	 */
	private void remove() {
	    deleteDriver = new RemoveDriver(this);
	}
	/**
	 * Method: edit
	 * Input: None
	 * Return: none
	 * 
	 * Description: Crates a new EditDriverPanel that allows the user to edit information
	 * on a driver by driverID.
	 */
	private void edit() {
	    editDriverInformation = new EditDriverPanel(this);
	}
	
	protected void refreshTable() {
	    DriverTablePanel freshTable = new DriverTablePanel();
	    
	    driverList.removeAll();
	    driverList.add(freshTable);
	    driverPane.setDividerLocation(250);
	    driverPane.setRightComponent(driverList);
	    this.add(driverPane, BorderLayout.CENTER);
	    // this.repaint();
	}

}
