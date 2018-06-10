package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/* $Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $ */
public class WorkPanel extends JPanel {

    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    JPanel panel = new JPanel();
    CardLayout cardLayout1 = new CardLayout();

    public JButton notesB = new JButton();
    public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
    public ResourcesPanel filesPanel = new ResourcesPanel();

    public DriversPanel driverPanel = new DriversPanel(this);

    public RoutePanel routePanel = new RoutePanel(this);

    public TourPanel tourPanel = new TourPanel(this);
    public SchedPanel welcomePanel = new SchedPanel(this); 
    //public JButton welcomeB = new JButton();
    //public JButton tasksB = new JButton();
    //public JButton eventsB = new JButton();
    //public JButton filesB = new JButton();
    // Buses Panel declaration - Tresor Cyubahiro
    public BusesPanel busesPanel = new BusesPanel();
    // Buses Button Tresor Cyubahiro
    public JButton busesB = new JButton();

    public JButton driversB = new JButton();
    public JButton routesB = new JButton();
    public JButton toursB = new JButton();
    public JButton welcomeB = new JButton();

    JButton currentB = null;
    Border border1;

    public WorkPanel() {
        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        border1 = BorderFactory
                .createCompoundBorder(
                        BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
                                new Color(124, 124, 124), new Color(178, 178, 178)),
                        BorderFactory.createEmptyBorder(0, 2, 0, 0));

        this.setLayout(borderLayout1);
        toolBar.setOrientation(JToolBar.VERTICAL);
        toolBar.setBackground(Color.white);

        toolBar.setBorderPainted(false);
        toolBar.setFloatable(false);
        panel.setLayout(cardLayout1);

        welcomeB.setBackground(Color.white);
        welcomeB.setMaximumSize(new Dimension(60, 80));
        welcomeB.setMinimumSize(new Dimension(30, 30));

        welcomeB.setFont(new java.awt.Font("Dialog", 1, 10));
        welcomeB.setPreferredSize(new Dimension(50, 50));
        welcomeB.setBorderPainted(false);
        welcomeB.setContentAreaFilled(false);
        welcomeB.setFocusPainted(false);
        welcomeB.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeB.setText(Local.getString("Sched"));
        welcomeB.setVerticalAlignment(SwingConstants.TOP);
        welcomeB.setVerticalTextPosition(SwingConstants.BOTTOM);
        welcomeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeB_actionPerformed(e);
            }
        });
        welcomeB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tabSchedIcon3.png")));
        welcomeB.setOpaque(false);
        welcomeB.setMargin(new Insets(0, 0, 0, 0));
        welcomeB.setSelected(true);
/*
        eventsB.setBackground(Color.white);
        eventsB.setMaximumSize(new Dimension(60, 80));
        eventsB.setMinimumSize(new Dimension(30, 30));

        eventsB.setFont(new java.awt.Font("Dialog", 1, 10));
        eventsB.setPreferredSize(new Dimension(50, 50));
        eventsB.setBorderPainted(false);
        eventsB.setContentAreaFilled(false);
        eventsB.setFocusPainted(false);
        eventsB.setHorizontalTextPosition(SwingConstants.CENTER);
        eventsB.setText(Local.getString("Events"));
        eventsB.setVerticalAlignment(SwingConstants.TOP);
        eventsB.setVerticalTextPosition(SwingConstants.BOTTOM);
        eventsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventsB_actionPerformed(e);
            }
        });
        eventsB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/events.png")));
        eventsB.setOpaque(false);
        eventsB.setMargin(new Insets(0, 0, 0, 0));
        // eventsB.setSelected(true);

        tasksB.setSelected(true);
        tasksB.setFont(new java.awt.Font("Dialog", 1, 10));
        tasksB.setMargin(new Insets(0, 0, 0, 0));
        tasksB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tasks.png")));
        tasksB.setVerticalTextPosition(SwingConstants.BOTTOM);
        tasksB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tasksB_actionPerformed(e);
            }
        });
        tasksB.setVerticalAlignment(SwingConstants.TOP);
        tasksB.setText(Local.getString("Tasks"));
        tasksB.setHorizontalTextPosition(SwingConstants.CENTER);
        tasksB.setFocusPainted(false);
        tasksB.setBorderPainted(false);
        tasksB.setContentAreaFilled(false);
        tasksB.setPreferredSize(new Dimension(50, 50));
        tasksB.setMinimumSize(new Dimension(30, 30));
        tasksB.setOpaque(false);
        tasksB.setMaximumSize(new Dimension(60, 80));
        tasksB.setBackground(Color.white);

        notesB.setFont(new java.awt.Font("Dialog", 1, 10));
        notesB.setBackground(Color.white);
        notesB.setBorder(null);
        notesB.setMaximumSize(new Dimension(60, 80));
        notesB.setMinimumSize(new Dimension(30, 30));
        notesB.setOpaque(false);
        notesB.setPreferredSize(new Dimension(60, 50));
        notesB.setBorderPainted(false);
        notesB.setContentAreaFilled(false);
        notesB.setFocusPainted(false);
        notesB.setHorizontalTextPosition(SwingConstants.CENTER);
        notesB.setText(Local.getString("Notes"));
        notesB.setVerticalAlignment(SwingConstants.TOP);
        notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
        notesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesB_actionPerformed(e);
            }
        });
        notesB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/notes.png")));
        notesB.setMargin(new Insets(0, 0, 0, 0));
        notesB.setSelected(true);
        this.setPreferredSize(new Dimension(1073, 300));

        filesB.setSelected(true);
        filesB.setMargin(new Insets(0, 0, 0, 0));
        filesB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/files.png")));
        filesB.setVerticalTextPosition(SwingConstants.BOTTOM);
        filesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filesB_actionPerformed(e);
            }
        });
        filesB.setFont(new java.awt.Font("Dialog", 1, 10));
        filesB.setVerticalAlignment(SwingConstants.TOP);
        filesB.setText(Local.getString("Resources"));
        filesB.setHorizontalTextPosition(SwingConstants.CENTER);
        filesB.setFocusPainted(false);
        filesB.setBorderPainted(false);
        filesB.setContentAreaFilled(false);
        filesB.setPreferredSize(new Dimension(50, 50));
        filesB.setMinimumSize(new Dimension(30, 30));
        filesB.setOpaque(false);
        filesB.setMaximumSize(new Dimension(60, 80));
        filesB.setBackground(Color.white);
*/
        // Buses Button Settings - Tresor Cyubahiro
        busesB.setSelected(true);
        busesB.setMargin(new Insets(0, 0, 0, 0));
        busesB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tabBusIcon.png")));

        busesB.setVerticalTextPosition(SwingConstants.BOTTOM);
        busesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busesB_actionPerformed(e);
            }
        });
        busesB.setFont(new java.awt.Font("Dialog", 1, 10));
        busesB.setVerticalAlignment(SwingConstants.TOP);
        busesB.setText(Local.getString("Buses"));
        busesB.setHorizontalTextPosition(SwingConstants.CENTER);
        busesB.setFocusPainted(false);
        busesB.setBorderPainted(false);
        busesB.setContentAreaFilled(false);
        busesB.setPreferredSize(new Dimension(50, 50));
        busesB.setMinimumSize(new Dimension(30, 30));
        busesB.setOpaque(false);
        busesB.setMaximumSize(new Dimension(60, 80));
        busesB.setBackground(Color.white);

        // Testing Adding Driver GUI Button
        driversB.setSelected(true);
        driversB.setMargin(new Insets(0, 0, 0, 0));
        driversB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tabDriverIcon.png")));
        driversB.setVerticalTextPosition(SwingConstants.BOTTOM);
        driversB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                driversB_actionPerformed(e);
            }
        });
        driversB.setFont(new java.awt.Font("Dialog", 1, 10));
        driversB.setVerticalAlignment(SwingConstants.TOP);
        driversB.setText(Local.getString("Drivers"));
        driversB.setHorizontalTextPosition(SwingConstants.CENTER);
        driversB.setFocusPainted(false);
        driversB.setBorderPainted(false);
        driversB.setContentAreaFilled(false);
        driversB.setPreferredSize(new Dimension(50, 50));
        driversB.setMinimumSize(new Dimension(30, 30));
        driversB.setOpaque(false);
        driversB.setMaximumSize(new Dimension(60, 80));
        driversB.setBackground(Color.white);

        routesB.setSelected(true);
        routesB.setMargin(new Insets(0, 0, 0, 0));
        routesB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tabRouteIcon.png")));
        routesB.setVerticalTextPosition(SwingConstants.BOTTOM);
        routesB.addActionListener(new java.awt.event.ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                routesB_actionPerformed(e);
            }
        });
      
        routesB.setFont(new java.awt.Font("Dialog", 1, 10));
        routesB.setVerticalAlignment(SwingConstants.TOP);
        routesB.setText(Local.getString("Routes"));
        routesB.setHorizontalTextPosition(SwingConstants.CENTER);
        routesB.setFocusPainted(false);
        routesB.setBorderPainted(false);
        routesB.setContentAreaFilled(false);
        routesB.setPreferredSize(new Dimension(50, 50));
        routesB.setMinimumSize(new Dimension(30, 30));
        routesB.setOpaque(false);
        routesB.setMaximumSize(new Dimension(60, 80));
        routesB.setBackground(Color.white);

        toursB.setBackground(Color.white);
        toursB.setMaximumSize(new Dimension(60, 80));
        toursB.setMinimumSize(new Dimension(30, 30));

        toursB.setFont(new java.awt.Font("Dialog", 1, 10));
        toursB.setPreferredSize(new Dimension(50, 50));
        toursB.setBorderPainted(false);
        toursB.setContentAreaFilled(false);
        toursB.setFocusPainted(false);
        toursB.setHorizontalTextPosition(SwingConstants.CENTER);
        toursB.setText(Local.getString("Tours"));
        toursB.setVerticalAlignment(SwingConstants.TOP);
        toursB.setVerticalTextPosition(SwingConstants.BOTTOM);
        toursB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toursB_actionPerformed(e);
            }
        });
        toursB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tabTourIcon.png")));
        toursB.setOpaque(false);
        toursB.setMargin(new Insets(0, 0, 0, 0));
        toursB.setSelected(true);

        this.add(toolBar, BorderLayout.WEST);
        this.add(panel, BorderLayout.CENTER);
        panel.add(dailyItemsPanel, "DAILYITEMS");
        panel.add(filesPanel, "FILES");

        // ADD Bus Panel Tresor Cyubahiro
        panel.add(busesPanel, "BUSES");

        panel.add(driverPanel, "DRIVERS");

        panel.add(routePanel, "ROUTES");

        panel.add(tourPanel, "TOURS");

        panel.add(welcomePanel, "WELCOME");
        toolBar.add(welcomeB, null);
        //toolBar.add(eventsB, null);
        //toolBar.add(tasksB, null);
        //toolBar.add(notesB, null);
        //toolBar.add(filesB, null);

        // Add Buses Button - Tresor Cyubahiro
        toolBar.add(busesB, null);

        toolBar.add(driversB, null);
        toolBar.add(routesB, null);
        toolBar.add(toursB, null);

        currentB = busesB;
        // Default blue color
        currentB.setBackground(new Color(215, 225, 250));
        currentB.setOpaque(true);

        toolBar.setBorder(null);
        panel.setBorder(null);
        dailyItemsPanel.setBorder(null);
        filesPanel.setBorder(null);
        busesPanel.setBorder(null);

    }

    public void selectPanel(String pan) {
        if (pan != null) {
            /*if (pan.equals("NOTES"))
                notesB_actionPerformed(null);
            else if (pan.equals("TASKS"))
                tasksB_actionPerformed(null);
            else if (pan.equals("EVENTS"))
                eventsB_actionPerformed(null);
            else if (pan.equals("FILES"))
                filesB_actionPerformed(null);
            */
            if (pan.equals("BUSES"))
                busesB_actionPerformed(null);
            else if (pan.equals("DRIVERS"))
                driversB_actionPerformed(null);
            else if (pan.equals("ROUTES"))

                routesB_actionPerformed(null);
            else if (pan.equals("TOURS"))
                toursB_actionPerformed(null);
            else if (pan.equals("WELCOME"))
                welcomeB_actionPerformed(null);
        }
    }

    public void welcomeB_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "WELCOME");
        dailyItemsPanel.selectPanel("WELCOME");
        setCurrentButton(welcomeB);
        Context.put("CURRENT_PANEL", "WELCOME");
    }
/*
    public void notesB_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "DAILYITEMS");
        dailyItemsPanel.selectPanel("NOTES");
        setCurrentButton(notesB);
        Context.put("CURRENT_PANEL", "NOTES");
    }

    public void tasksB_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "DAILYITEMS");
        dailyItemsPanel.selectPanel("TASKS");
        setCurrentButton(tasksB);
        Context.put("CURRENT_PANEL", "TASKS");
    }

    public void eventsB_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "DAILYITEMS");
        dailyItemsPanel.selectPanel("EVENTS");
        setCurrentButton(eventsB);
        Context.put("CURRENT_PANEL", "EVENTS");
    }

    public void filesB_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "FILES");
        setCurrentButton(filesB);
        Context.put("CURRENT_PANEL", "FILES");
    }
*/
    // buses ToolBar Button Action
    public void busesB_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "BUSES");
        setCurrentButton(busesB);
        Context.put("CURRENT_PANEL", "BUSES");
    }

    // Driver Action Event When Button is pressed
    public void driversB_actionPerformed(ActionEvent e) {
        System.out.println("[DEBUG] Drivers has been pressed");
        cardLayout1.show(panel, "DRIVERS");
        dailyItemsPanel.selectPanel("DRIVERS");
        setCurrentButton(driversB);
        Context.put("CURRENT_PANEL", "DRIVERS");
    }

    public void routesB_actionPerformed(ActionEvent e) {
        System.out.println("[DEBUG] Routes has been pressed");
        cardLayout1.show(panel, "ROUTES");
        dailyItemsPanel.selectPanel("ROUTES");
        setCurrentButton(routesB);
        Context.put("CURRENT_PANEL", "ROUTES");
    }

	public void toursB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "TOURS");
		dailyItemsPanel.selectPanel("TOURS");
		setCurrentButton(toursB);
		Context.put("CURRENT_PANEL", "TOURS");
	}
	
	void setCurrentButton(JButton cb) {
		currentB.setBackground(Color.white);
		currentB.setOpaque(false);
		currentB = cb;
		// Default color blue
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);
	}
}