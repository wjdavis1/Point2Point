package main.java.memoranda.ui;
/**
+ * File Name: SchedPanel.java
+ * Description: The main panel for calendar to schedule functionality.
+ * The user can choose any calendar date and see the Tours/Drivers/Buses in their respective tables.
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
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.memoranda.models.TourImpl;
import main.java.memoranda.util.Local;
import main.java.memoranda.models.TourCollection;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;

public class SchedPanel extends JPanel {
    
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
    CalendarDate currentDate;
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    GridLayout borderLayout3 = new GridLayout(3,0);
    BorderLayout borderLayout5 = new BorderLayout();

    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    
    JPanel controlPanel = new JPanel();
    JPanel cmainPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JNCalendarPanel calendar = new JNCalendarPanel();
    JPanel parentPanel = new JPanel();
    boolean calendarIgnoreChange = false;
    boolean dateChangedByCalendar = false;
    boolean changedByHistory = false;
    
    public SchedPanel(WorkPanel _parentPanel) {

        try {
          parentPanel = _parentPanel;
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
    
        /*
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(tourTable);
        splitPane.setLeftComponent(new JPanel());
        splitPane.setDividerLocation(266);
        */
        this.setLayout(borderLayout1);
        controlPanel.setLayout(borderLayout2);
        mainPanel.setLayout(borderLayout3);
        cmainPanel.setLayout(borderLayout5);
        
       // splitPane.setDividerSize(2);
        cmainPanel.add(calendar, BorderLayout.NORTH);
        mainPanel.add(tourTable, BorderLayout.NORTH);
        
        controlPanel.setPreferredSize(new Dimension(205, 170));
        controlPanel.add(cmainPanel, BorderLayout.CENTER);

        cmainPanel.add(calendar, BorderLayout.NORTH);
        mainPanel.add(tourTable);
        busTable = new BusTable(calendar.get());
        mainPanel.add(busTable);
        driverTable = new DriverTablePanel(calendar.get());
        mainPanel.add(driverTable);
        //mainPanel.add(tourTable, BorderLayout.NORTH);
        splitPane.setDividerLocation(250);
        splitPane.add(mainPanel, JSplitPane.RIGHT);
        splitPane.add(controlPanel, JSplitPane.LEFT);
        this.add(splitPane, BorderLayout.CENTER);
        
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
        
        //Add Toolbar buttons
        tourToolbar = new JToolBar();
        tourToolbar.setFloatable(false);
       // tourToolbar.add(addTour);
       // tourToolbar.add(deleteTour);
       // tourToolbar.add(editTour);
        refreshTourB.setVisible(true);
        refreshTourB.hide();
        tourToolbar.add(refreshTourB);
        
        //Add Toolbar and Horizontal SplitPane
        add(tourToolbar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        
        setVisible(true);
    
        calendar.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (calendarIgnoreChange)
                    return;
                dateChangedByCalendar = true;
                CurrentDate.set(calendar.get());
                System.out.println("Calendar.get: "+calendar.get().toString());
                dateChangedByCalendar = false;

                mainPanel.removeAll();
                tourTable = new TourTable(calendar.get().toString());
                mainPanel.add(tourTable);
                busTable = new BusTable(calendar.get());
                mainPanel.add(busTable);
                driverTable = new DriverTablePanel(calendar.get());
                mainPanel.add(driverTable);
                
                //splitPane.setRightComponent(freshTable);                
            }
        });
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
      /*  String date = null;
        mainPanel.removeAll();
        TourTable tourTable1 = new TourTable(date);
        mainPanel.add(tourTable1);
        busTable = new BusTable(calendar.get());
        mainPanel.add(busTable);
        driverTable = new DriverTablePanel(calendar.get());
        mainPanel.add(driverTable);*/
       

    }
    
    public void refreshTable( ) {
        String date = null;
        mainPanel.removeAll();
        TourTable tourTable1 = new TourTable(date);
        mainPanel.add(tourTable1);
        busTable = new BusTable(calendar.get());
        mainPanel.add(busTable);
        driverTable = new DriverTablePanel(calendar.get());
        mainPanel.add(driverTable);
        splitPane.setDividerLocation(250);
        splitPane.setRightComponent(mainPanel);
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
