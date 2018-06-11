package main.java.memoranda.ui;
/**
+ * File Name: TourPanel.java
+ * Description: The main panel for the display of tour objects with buttons to add, delete, and edit Tours. 
			Buttons instantiate an AddTourPanel pop-up where the user can input Tour information and contains
			a TourTable for displaying the Tour Collection
+ * Author: Jesse Sabbath
+ * Date: February 22, 2018
+ * 
+ */
import javax.swing.JPanel; 
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import main.java.memoranda.models.TourImpl;
import main.java.memoranda.util.Local;
import main.java.memoranda.models.TourCollection;

public class TourPanel extends JPanel {
	
	TourCollection tourCollection = new TourCollection("src/main/resources/data/tours/tours.json");
	JSplitPane splitPane = new JSplitPane();
	String date = null;
	TourTable tourTable = new TourTable(date);
	BusTable busTable; 
	DriverTablePanel driverTable;
	
	/*
	 * Adrian Vasquez 03/26/2018
	 * US Story #81 Task #109
	 */
	
	/*
	 * Calendar moved to SchedPanel
	 * JSabbath 04/18/2018
	 */
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	GridLayout borderLayout3 = new GridLayout(3,0);
	BorderLayout borderLayout5 = new BorderLayout();

	

	
	public TourPanel(WorkPanel _parentPanel) {

	    try {
	      jbInit();
	    }
	    catch(Exception ex) {
	      new ExceptionDialog(ex);
	    }
	}
		  
	void jbInit() throws Exception {
		
		ImageIcon add;
		ImageIcon remove;
		ImageIcon edit;
		ImageIcon refresh;
		/*Future Implementation: 
		ImageIcon find;*/
	
		JButton addTour;
		JButton deleteTour;
		JButton editTour;
		JButton refreshTourB;
		JToolBar tourToolbar;
	
		
		
		
		add = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_new.png"));
		remove = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_remove.png"));
		edit = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/editproject.png"));
        refresh = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png"));
		//Possible Future Implementation: 
        //find = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/help.png"));
        
        addTour = new JButton("Add Tour", add);
        deleteTour = new JButton("Remove Tour", remove);
        editTour = new JButton("Edit Tour", edit);
        refreshTourB = new JButton("View All Tours", refresh);
        
		//addButtonListeners for Add/Remove/Edit/Refresh
	    addTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTourB_actionPerformed(e);
            }
        });
	    		
	    deleteTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	deleteTourB_actionPerformed(e);
            }
        });
	    		
	    editTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTourB_actionPerformed(e);
            }
        });	    
	    
        refreshTourB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTourB_actionPerformed(e);
            }
        });
        
        
        this.setLayout(borderLayout1);
        JPanel tablePanel = new JPanel();
        tablePanel.add(tourTable);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(tablePanel);
        splitPane.setLeftComponent(new JPanel());
        splitPane.setDividerLocation(250);
        splitPane.setBorder(null);
       
        this.add(splitPane, BorderLayout.CENTER);
	    //Add Toolbar buttons
        tourToolbar = new JToolBar();
        tourToolbar.setFloatable(false);
		tourToolbar.add(addTour);
		tourToolbar.add(deleteTour);
		tourToolbar.add(editTour);
		tourToolbar.add(refreshTourB);
		
		//Add Toolbar and Horizontal SplitPane
		add(tourToolbar, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		
		setVisible(true);
	}
        
	
	/**
	 * 
	 * @param e
	 */
	void addTourB_actionPerformed(ActionEvent e) {
		
		EditTourPanel addTourPanel = new EditTourPanel(App.getFrame(), Local.getString("Add New Tour"), tourCollection);
		Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        addTourPanel.daySelectorCB.setBackground(Color.WHITE);
        addTourPanel.monthSelectorCB.setBackground(Color.WHITE);
        addTourPanel.yearSelectorCB.setBackground(Color.WHITE);
        addTourPanel.timeSelectorCB.setBackground(Color.WHITE);
        addTourPanel.routeSelectorCB.setBackground(Color.WHITE);
        addTourPanel.busSelectorCB.setBackground(Color.WHITE);
        addTourPanel.driverSelectorCB.setBackground(Color.WHITE);
        addTourPanel.setLocation((frmSize.width - addTourPanel.getSize().width) / 2 + loc.x, (frmSize.height - addTourPanel.getSize().height) / 2 + loc.y);
        addTourPanel.setVisible(true);
        
        refreshTable();
	}
	
	void deleteTourB_actionPerformed(ActionEvent e) {
		EditTourPanel addTourPanel = new EditTourPanel(App.getFrame(), Local.getString(""), tourCollection);
		Dimension frmSize = App.getFrame().getSize();
		Point loc = App.getFrame().getLocation();
		addTourPanel.routeIDField.setEditable(false);
		addTourPanel.driverIDField.setEditable(false);
		addTourPanel.busIDField.setEditable(false);
		addTourPanel.daySelectorCB.setEditable(false);
		addTourPanel.monthSelectorCB.setEditable(false);
		addTourPanel.yearSelectorCB.setEditable(false);
		addTourPanel.timeSelectorCB.setEditable(false);
		
		addTourPanel.header.setText(Local.getString("Remove Tour"));
		addTourPanel.setLocation((frmSize.width - addTourPanel.getSize().width) / 2 + loc.x, (frmSize.height - addTourPanel.getSize().height) / 2 + loc.y);
        addTourPanel.setVisible(true);	
        
        refreshTable();
        
	}
	
	void editTourB_actionPerformed(ActionEvent e) {
	    
	    String tourToEdit = JOptionPane.showInputDialog("Enter Tour Id to Edit");   	        
		EditTourPanel editTourPanel = new EditTourPanel(App.getFrame(), Local.getString(""), tourCollection);
		TourImpl foundTour = null;
	
		for (int i = 0; i < tourCollection.getTourList().size(); i++)
		{
		    if ((tourCollection.getTourList().elementAt(i).getTourID()).equals(tourToEdit))
		    {
		        foundTour = tourCollection.getTour(tourToEdit);
		    }
		}
		
		if (foundTour == null)
		    JOptionPane.showMessageDialog(getParent(), "Tour Not Found");
		else {
		Dimension frmSize = App.getFrame().getSize();
		
		Point loc = App.getFrame().getLocation();
		fillTourInfo(foundTour, editTourPanel);
		
		editTourPanel.tourIDField.setEditable(false);
		editTourPanel.tourIDField.setBackground(new Color(195,225,250));
		editTourPanel.routeSelectorCB.setBackground(new Color(195,225,250));
        editTourPanel.driverSelectorCB.setBackground(new Color(195,225,250));
        editTourPanel.busSelectorCB.setBackground(new Color(195,225,250));
		editTourPanel.daySelectorCB.setBackground(new Color(195,225,250));
        editTourPanel.monthSelectorCB.setBackground(new Color(195,225,250));
        editTourPanel.yearSelectorCB.setBackground(new Color(195,225,250));
        editTourPanel.timeSelectorCB.setBackground(new Color(195,225,250));     
		editTourPanel.header.setText(Local.getString("Edit Tour"));
		
		editTourPanel.setLocation((frmSize.width - editTourPanel.getSize().width) / 2 + loc.x, (frmSize.height - editTourPanel.getSize().height) / 2 + loc.y);			
        editTourPanel.setVisible(true);	
        editTourPanel.dispose();
        
        refreshTable();
        }
	}
	
	void refreshTourB_actionPerformed(ActionEvent e) {
	    refreshTable(); 
    }
	
	public void refreshTable( ) {
	    TourTable freshTable = new TourTable(null);
	    splitPane.setDividerLocation(250);
        splitPane.setRightComponent(freshTable);
	}
	
	public void fillTourInfo(TourImpl foundTour, EditTourPanel tourEditPanel) {
	    
	    String tourDate = foundTour.getDate();
        String tourTime = foundTour.getTime();
        String driver = foundTour.getDriverID();
        String bus = foundTour.getBusID();
        String route = foundTour.getRouteID();
        String date[] = tourDate.split(" / ");
        
        tourEditPanel.monthSelectorCB.setSelectedIndex(Integer.parseInt(date[0]));
        tourEditPanel.daySelectorCB.setSelectedIndex(Integer.parseInt(date[1])-1);
        tourEditPanel.yearSelectorCB.setSelectedItem(Integer.parseInt(date[2]));
        tourEditPanel.timeSelectorCB.setSelectedItem(tourTime);
        tourEditPanel.busSelectorCB.setSelectedItem(bus);
        tourEditPanel.driverSelectorCB.setSelectedItem(driver);
        tourEditPanel.routeSelectorCB.setSelectedItem(route);    
        tourEditPanel.tourIDField.setText(foundTour.getTourID());
	}
	
	
}
