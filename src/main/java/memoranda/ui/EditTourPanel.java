package main.java.memoranda.ui;
/**
+ * File Name: AddTourPanel.java
+ * Description: A pop-up dialog where users can add new Tours to the TourCollection Vector
+ * Author: Jesse Sabbath
+ * Date: February 22, 2018
+ * 
+ */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import main.java.memoranda.util.Local;
import main.java.memoranda.BusCollection;
import main.java.memoranda.BusImpl;
import main.java.interfaces.Driver;
import main.java.memoranda.DriverCollection;
import main.java.interfaces.Route;
import main.java.memoranda.RouteCollection;
import main.java.memoranda.TourCollection;
import main.java.memoranda.TourImpl;
import javax.swing.JOptionPane;
public class EditTourPanel extends JDialog {
	
	JLabel header = new JLabel();
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel areaPanel = new JPanel(new GridBagLayout());
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
    ButtonGroup buttonGroup1 = new ButtonGroup();
    JButton okB = new JButton();
    JButton cancelB = new JButton();
    GridBagConstraints gbc;
    JTextField tourIDField = new JTextField(8);
    JTextField routeIDField = new JTextField(8);
    JTextField driverIDField = new JTextField(8);
    JTextField busIDField = new JTextField(8);
    JLabel tourIDLabel = new JLabel("Tour ID: ");
    JLabel routeIDLabel = new JLabel("Route ID: ");
    JLabel driverIDLabel = new JLabel("Driver ID: ");
    JLabel busIDLabel = new JLabel("Bus ID: ");
    JLabel timeLabel = new JLabel("Time: ");
    JLabel dayLabel = new JLabel("Day: ");
    JLabel monthLabel = new JLabel("Month: ");
    JLabel yearLabel = new JLabel("Year: ");
    JComboBox timeSelectorCB = new JComboBox();
    JComboBox daySelectorCB = new JComboBox();
    JComboBox yearSelectorCB = new JComboBox();
    JComboBox monthSelectorCB = new JComboBox();
    JComboBox busSelectorCB = new JComboBox();
    JComboBox driverSelectorCB = new JComboBox();
    JComboBox routeSelectorCB = new JComboBox();
    
    TourCollection tourCollection; 
    DriverCollection driverCollection = new DriverCollection("drivers.json");
    BusCollection busCollection = new BusCollection();
    RouteCollection routeCollection = new RouteCollection("routes.json");
    
    public boolean CANCELLED = true;

    public EditTourPanel(Frame frame, String title, TourCollection tourCollection) {
        super(frame, title, true);
        try {
        	this.tourCollection = tourCollection;
            jbInit();
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
            ex.printStackTrace();
        }		
    }

	/**
	 * setup user interface and init dialog
	 */
	 
    void jbInit() throws Exception {
    	
    	//set up main AddTourPanel and header
		this.setResizable(false);
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Add Tour"));
        header.setIcon(new ImageIcon(main.java.memoranda.ui.AddResourceDialog.class.getResource(
            "/ui/icons/resource48.png")));
        dialogTitlePanel.add(header);
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        
        //Populate ComboBoxes
        setDateCB();
        populateData();
        
        //Add tourID components
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(tourIDLabel, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(tourIDField, gbc);
        
        //add routeID components
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 2; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(routeIDLabel, gbc);
        
//        gbc = new GridBagConstraints();
//        gbc.gridwidth = 1;
//        gbc.gridx = 3; gbc.gridy = 0;
//        gbc.insets = new Insets(10, 15, 5, 15);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        areaPanel.add(routeIDField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 3; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(routeSelectorCB, gbc);
        
        //add driveID components
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(driverIDLabel, gbc);
        
//        gbc = new GridBagConstraints();
//        gbc.gridwidth = 1;
//        gbc.gridx = 1; gbc.gridy = 1;
//        gbc.insets = new Insets(10, 15, 5, 15);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        areaPanel.add(driverIDField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(driverSelectorCB, gbc);

        //add busID components
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(busIDLabel, gbc);
        
//        gbc = new GridBagConstraints();
//        gbc.gridwidth = 1;
//        gbc.gridx = 3; gbc.gridy = 1;
//        gbc.insets = new Insets(10, 15, 5, 15);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        areaPanel.add(busIDField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 3; gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(busSelectorCB, gbc);
        
             
        //add date components
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(dayLabel, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(daySelectorCB, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(monthLabel, gbc);
  
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(monthSelectorCB, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(yearLabel, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 6;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(yearSelectorCB, gbc);
        
        //add time components
        gbc = new GridBagConstraints();
        gbc.gridwidth = 3;
        gbc.gridx = 2; gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(timeLabel, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 3;
        gbc.gridx = 3; gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(timeSelectorCB, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 3;
        gbc.gridx = 3; gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(timeSelectorCB, gbc);
        
        monthSelectorCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                monthSelectorCB_actionPerformed(e);
            }
        });
        
        this.getContentPane().add(areaPanel, BorderLayout.CENTER);
        
        
        okB.setEnabled(true);
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(okB);
        cancelB.setMaximumSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setText(Local.getString("Cancel"));
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        
        //add button components
        buttonsPanel.setLayout(new FlowLayout());
	    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); 
	    buttonsPanel.add(okB);
        buttonsPanel.add(cancelB);
        
		//enableFields();
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }
	 
    /**
     * OkayButtonChoice
     * @param e
     */
    void okB_actionPerformed(ActionEvent e) {
    	if (this.header.getText().equals("Add Tour"))
    		addTour();
    	else if (this.header.getText().equals("Remove Tour"))
    		removeTour();
    	else 
    		editTour(); 
    }

    
	/**
	 * close the dialog window
	 */
    void cancelB_actionPerformed(ActionEvent e) {
        this.dispose();
    }
    
    /**
     * set CANCELLED variable to false so we can know the user 
     * pressed the ok button and close this dialog.
     */
    void addTour() {
    String date = monthSelectorCB.getSelectedIndex() + " / " +  daySelectorCB.getSelectedItem() + " / " + yearSelectorCB.getSelectedItem();
    String time = timeSelectorCB.getSelectedItem().toString();
    TourImpl newTour = new TourImpl(tourIDField.getText(), routeSelectorCB.getSelectedItem().toString(), driverSelectorCB.getSelectedItem().toString(), busSelectorCB.getSelectedItem().toString(), date, time);
    	JDialog duplicateDialog = new JDialog(new JFrame(), "Error", true);
    	JButton okB = new JButton("Okay");
    	String dupeTourID = "Tour ID is taken.\nPlease edit Tour ID\n\n";
    	String dupeTourDetails = "Duplicate Tour: Please edit tour details\n\n";
    	duplicateDialog.setSize(300,200);   
    	duplicateDialog.setLayout(new FlowLayout());
		duplicateDialog.setLocationRelativeTo(null);
		
    	for (int j = 0; j < tourCollection.getTourList().size(); j++) {
    	    
    	    TourImpl existingTour = (TourImpl)tourCollection.getTourList().elementAt(j);
    	    
    		    if(newTour.getTourID().equals(existingTour.getTourID())) {
	    			duplicateDialog.add(new JLabel(dupeTourID));
	    			duplicateDialog.add(okB);
	    			duplicateDialog.setVisible(true);
	    			return;
	    			}
	    			
	    			if(newTour.getRouteID().equals(existingTour.getRouteID()) &&
	    					(newTour.getDriverID().equals(existingTour.getDriverID()) ||
	    					newTour.getBusID().equals(existingTour.getBusID()))) {
	    				duplicateDialog.add(new JLabel(dupeTourDetails));
	    				duplicateDialog.add(okB);
	    				duplicateDialog.setVisible(true);
	    				return;
	    	}	    		
        }
    	
    	tourCollection.addTour(newTour);
    	tourCollection.exportTourCollection();

		JFrame tourAddedFrame = new JFrame();
		JOptionPane.showMessageDialog(tourAddedFrame, "Tour Added!");
		tourAddedFrame.setVisible(true);
    	CANCELLED = false;
    	tourAddedFrame.dispose();
		this.dispose();
        
       

    }
    
    void removeTour() {
    	String tourID = tourIDField.getText();
    	   
        for (int i = 0; i < tourCollection.getTourList().size(); i++) {
            
         	TourImpl thisTour = (TourImpl)tourCollection.getTourList().elementAt(i);
         	
        	if	(thisTour.getTourID().equals(tourID))
        		tourCollection.removeTour(thisTour);
        		tourCollection.exportTourCollection();	
        }  
        
        JFrame tourAddedFrame = new JFrame();
        JOptionPane.showMessageDialog(tourAddedFrame, "Tour Removed!");
        tourAddedFrame.setVisible(true);
        tourAddedFrame.dispose();
        this.dispose();
    }
    
    /**
     * 
     */
    void editTour() {  
   
    	//create date/time table strings
        String date = monthSelectorCB.getSelectedIndex() + " / " + daySelectorCB.getSelectedItem() + " / " + yearSelectorCB.getSelectedItem();
        String time = timeSelectorCB.getSelectedItem().toString();
        //setDateCB();
        
        
        //create new Tour object
    	TourImpl newTour = new TourImpl(tourIDField.getText(), routeSelectorCB.getSelectedItem().toString(), driverSelectorCB.getSelectedItem().toString(), busSelectorCB.getSelectedItem().toString(), date, time);
    	
    	//get searched Tour
    	String tourID = tourIDField.getText();
    	
    	//swap out old Tour for new
    	for (int i = 0; i < tourCollection.getTourList().size(); i++) {
           	TourImpl thisTour = (TourImpl)tourCollection.getTourList().elementAt(i);
           	
          	if	(thisTour.getTourID().equals(tourID)) {
          		tourCollection.removeTour(thisTour);
          		tourCollection.addTour(newTour);
          		tourCollection.exportTourCollection();
          	}
    	}  
    	
        JFrame tourAddedFrame = new JFrame();
        JOptionPane.showMessageDialog(tourAddedFrame, "Tour Edited!");
        tourAddedFrame.setVisible(true);
        tourAddedFrame.dispose();
        this.dispose();
    }
    
    /*
     * Method: populateData
     * Return: none
     * Description; populate data combo boxes
     */
    public void populateData() {
    	
    		busSelectorCB.addItem("Select Bus");
    		driverSelectorCB.addItem("Select Driver");
    		routeSelectorCB.addItem("Select Route");
    		
    		Vector<BusImpl> buses = busCollection.getBusList();
    		
    		for(BusImpl bus : busCollection.getBusList()) {
    			System.out.println(bus.getId());
    			busSelectorCB.addItem(""+bus.getId());
    		}
    		
    		HashMap<String,Driver> drivers = driverCollection.getDrivers();
    		Map<String,Route> routes = routeCollection.getRoutes();
    		
    		for(Driver driver : drivers.values()) {
    			System.out.println(driver.getDriverId());
    			driverSelectorCB.addItem(""+driver.getDriverId());
    		}
    		
    		for(Route route : routes.values()) {
    			System.out.println(route.getRouteId());
    			routeSelectorCB.addItem(""+route.getRouteId());
    		}
    		
    }
    
    /**
     * Populates the daySelectorCB
     * @param e
     */
    public void monthSelectorCB_actionPerformed(ActionEvent e) {
        int month = monthSelectorCB.getSelectedIndex();
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            daySelectorCB.removeAllItems();
            for (int i = 1; i < 32; i++) {
                
                daySelectorCB.addItem(new Integer(i));
            }
        }
        else if (month == 4 || month == 6 || month ==9 || month == 11) {
            daySelectorCB.removeAllItems();
            for (int i = 1; i < 31; i++) {
                
                daySelectorCB.addItem(new Integer(i));
            }
        }
        else 
        {
            daySelectorCB.removeAllItems();
            for (int i = 1; i < 29; i++) {
                
                daySelectorCB.addItem(new Integer(i));
            }
        
         }
    }
    public void setDateCB( ) {
        monthSelectorCB.addItem("Month");
        
        for (Month month : Month.values()) {
            monthSelectorCB.addItem(month);
        }
        
        yearSelectorCB.addItem("Year");
        for (int i = 2018; i < 2200; i++) {
            yearSelectorCB.addItem(new Integer(i));
        }
        
        daySelectorCB.addItem("Day");
        timeSelectorCB.setSize(new Dimension(10,15));
        timeSelectorCB.addItem("Please Choose a Time");
        
        for (int i = 8; i < 12; i++) {
            String onHour = i + ":00am";
            String halfHour = i + ":30am";
            timeSelectorCB.addItem(onHour);
            timeSelectorCB.addItem(halfHour);
        }
        
        for (int i = 12; i < 13; i++)
        {
            String onHour = i + ":00pm";
            String halfHour = i + ":30pm";
            timeSelectorCB.addItem(onHour);
            timeSelectorCB.addItem(halfHour);
        }
            
        
        for (int i = 1; i < 12; i++) {
            String onHour = i + ":00pm";
            String halfHour = i + ":30pm";
            timeSelectorCB.addItem(onHour);
            timeSelectorCB.addItem(halfHour);
        }
    }
    
}