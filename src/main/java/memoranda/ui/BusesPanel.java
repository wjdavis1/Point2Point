package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

import org.json.*;

import javafx.scene.control.SplitPane;

import java.io.*;

/*BusesPanel 
 * AUTHOR: TRESOR CYUBAHIRO
 * 
 * */
public class BusesPanel extends JPanel {
    
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    JButton newBusB = new JButton();
    JButton refreshB = new JButton();
    BusTable busTable = new BusTable();
    JScrollPane scrollPane = new JScrollPane();
    JPanel bussesPanel = new JPanel();
    JSplitPane splitPane = new JSplitPane();
    
    //jsabbath US#143 Adding matching buttons and refactoring to support fullfunctionality and table
    //April 6, 2018
    
    JButton addBus;
    JButton removeBus;
    JButton editBus;
    JButton refreshBus;
    JButton findBus;
    
    ImageIcon add;
    ImageIcon remove;
    ImageIcon edit;
    ImageIcon refresh;
    ImageIcon  find;
    /*Future Implementation: 
    ImageIcon find;*/

  JPopupMenu busPPMenu = new JPopupMenu();
  JMenuItem ppNewBus = new JMenuItem();
  JMenuItem ppRefresh = new JMenuItem();
  RemoveBus deleteBus;
  FindBusPanel findABus;
  EditBusPanel editBusInfo;

    public BusesPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }
    
    //Build Bus Panel
    void jbInit() throws Exception {
        
        
        //Create Image Icons
        add = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_new.png"));
        remove = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/todo_remove.png"));
        edit = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/editproject.png"));
        refresh = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png"));
        find = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/help.png"));
        
        //Create buttons
        addBus = new JButton("Add Bus", add);
        removeBus = new JButton("Remove Bus", remove);
        editBus = new JButton("Edit Bus", edit);
        refreshBus = new JButton("View All Busses", refresh);
        findBus = new JButton("Find Bus", find);
        
      //addButtonListeners for Add/Remove/Edit
        addBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBus_actionPerformed(e);
            }
        });
              
        removeBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeBus_actionPerformed(e);
            }
        });
            
        editBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editBus_actionPerformed(e);
            }
        });
        
        findBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findBusPanel();
            }
        });
            
        refreshBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshBus_actionPerformed(e);
            }
        });
                      
        
        
        
        
     /*   newBusB.setText(Local.getString("New"));
        newBusB.setEnabled(true);
        newBusB.setMaximumSize(new Dimension(24, 24));
        newBusB.setMinimumSize(new Dimension(24, 24));
        newBusB.setToolTipText(Local.getString("New Bus"));
        newBusB.setRequestFocusEnabled(false);
        newBusB.setPreferredSize(new Dimension(24, 24));
        newBusB.setFocusable(false);
        newBusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newBusB_actionPerformed(e);
            }
        });
        newBusB.setBorderPainted(false);

        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        refreshB.setBorderPainted(false);
        refreshB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshB_actionPerformed(e);
            }
        });
        refreshB.setFocusable(false);
        refreshB.setPreferredSize(new Dimension(24, 24));
        refreshB.setRequestFocusEnabled(false);
        refreshB.setToolTipText(Local.getString("Refresh"));
        refreshB.setMinimumSize(new Dimension(24, 24));
        refreshB.setMaximumSize(new Dimension(24, 24));
        refreshB.setEnabled(true);
        refreshB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png")));
        busPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
    ppNewBus.setFont(new java.awt.Font("Dialog", 1, 11));
    ppNewBus.setText(Local.getString("New resource")+"...");
    ppNewBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewBus_actionPerformed(e);
            }
        });
    ppNewBus.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/addresource.png")));

    ppRefresh.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRefresh.setText(Local.getString("Refresh"));
    ppRefresh.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ppRefresh_actionPerformed(e);
      }
    });
    ppRefresh.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png")));
*/
  //  toolBar.add(newBusB, null);
    
      //  toolBar.addSeparator();
        
        
       
   
        // busTable.setMaximumSize(new Dimension(32767, 32767));
    //busTable.setRowHeight(24);
    //scrollPane.getViewport().setBackground(Color.white);
        //toolBar.addSeparator(new Dimension(8, 24));
       // toolBar.addSeparator(new Dimension(8, 24));
    //splitPane.setDividerSize(2);
        this.setLayout(new BorderLayout());
        
        bussesPanel.add(busTable);
        toolBar.add(addBus);
        toolBar.add(removeBus);
        toolBar.add(editBus);
        toolBar.add(findBus);
        toolBar.add(refreshBus);
        toolBar.setFloatable(false);
        
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBorder(null);
        splitPane.setLeftComponent(new JPanel());
        splitPane.setDividerLocation(250);      
        splitPane.setRightComponent(bussesPanel);
        this.add(splitPane, BorderLayout.CENTER);
        this.add(toolBar, BorderLayout.NORTH);
        
        
 /*   busPPMenu.addSeparator();
    busPPMenu.add(ppNewBus);
    busPPMenu.addSeparator();
    busPPMenu.add(ppRefresh);*/
	
    }

    void addBus_actionPerformed(ActionEvent e) {
    	AddBusDialog dlg = new AddBusDialog(this);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
    }
    
    void removeBus_actionPerformed(ActionEvent e) {
    		remove();
    }
    
	/**
	 * Method: remove
	 * Input: none
	 * Return: void/none
	 */
	private void remove() {
	    deleteBus = new RemoveBus(this);
	}
    
	/* Author: Tresor Cyubahiro
	 * Method: findDriverPanel
	 * Input: None
	 * Return: Void
	 * Description: Finds a driver whose ID is provided by the user
	 */
	private void findBusPanel() {
		findABus = new FindBusPanel();
	}
	
    void editBus_actionPerformed(ActionEvent e) {
    		editBusInfo = new EditBusPanel(this);
    }
    
    void refreshBus_actionPerformed(ActionEvent e) {   
        
            refreshTable();
          
}
    
    void refreshTable() {
       
        BusTable freshTable = new BusTable();
        bussesPanel.removeAll();
        bussesPanel.add(freshTable);
        splitPane.setDividerLocation(250);
        splitPane.setRightComponent(bussesPanel);
        this.add(splitPane, BorderLayout.CENTER);
    }


    
    
    
    
    
    class PopupListener extends MouseAdapter {

                public void mousePressed(MouseEvent e) {
                    
                }

                public void mouseReleased(MouseEvent e) {
                  
                }


    }
    void refreshB_actionPerformed(ActionEvent e) {
    	
    }

  void ppRun_actionPerformed(ActionEvent e) {

  }
  /*void ppNewBus_actionPerformed(ActionEvent e) {
    newBusB_actionPerformed(e);
  }*/

  void ppRefresh_actionPerformed(ActionEvent e) {

  }
}