package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;



import main.java.memoranda.BusCollection;
import main.java.memoranda.BusImpl;
import main.java.memoranda.ui.BusesPanel;
import main.java.memoranda.util.Local;

/*
 * AUTHOR: TRESOR CYUBAHIRO
 * SER 316
 * */
public class AddBusDialog extends JDialog {
	
    public boolean CANCELLED = true;    
    public JTextField idField = new JTextField();    
    public JTextField nameField = new JTextField();   
    public JTextField seatsField = new JTextField();
    
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
    JPanel areaPanel = new JPanel(new GridBagLayout());
    
    JLabel header = new JLabel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    
    JButton saveB = new JButton();
    JButton cancelB = new JButton();
    
    ButtonGroup buttonGroup1 = new ButtonGroup();
    
    GridBagConstraints gbc;

    BusesPanel busPanel;
    
    BusImpl newBus = new BusImpl();
    //App.getFrame(), Local.getString("Add New Bus")
    //Frame frame, String title
    public AddBusDialog(BusesPanel busPanel) {
        //super(frame, title, true);
        super();
        this.busPanel = busPanel;
        try {
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
		this.setResizable(false);
		//this.setTitle("Add New Bus");
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Add Bus"));
        header.setIcon(new ImageIcon(main.java.memoranda.ui.AddResourceDialog.class.getResource(
            "/ui/icons/resource48.png")));
        dialogTitlePanel.add(header);
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 2; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        jLabel1.setText(Local.getString("ID")+": ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(5, 20, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(jLabel1, gbc);
        idField.setMinimumSize(new Dimension(4, 24));
        idField.setPreferredSize(new Dimension(100, 24));
        idField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
              
            }
        });
        idField.setText(generateID()+"");
        idField.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(idField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        jLabel2.setText(Local.getString("Name")+":  ");
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.insets = new Insets(5, 20, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(jLabel2, gbc);
        nameField.setMinimumSize(new Dimension(4, 24));
        nameField.setPreferredSize(new Dimension(335, 24));
        nameField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
            	 VerifyInput();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 0, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(nameField, gbc);
        
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        jLabel3.setText(Local.getString("No Of Seats")+":  ");
        areaPanel.add(jLabel3, gbc);
        seatsField.setMinimumSize(new Dimension(4, 24));
        seatsField.setPreferredSize(new Dimension(335, 24));
        seatsField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
                VerifyInput();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 0, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(seatsField, gbc);
        
        
        this.getContentPane().add(areaPanel, BorderLayout.CENTER);
        
        saveB.setEnabled(false);
        saveB.setMaximumSize(new Dimension(100, 26));
        saveB.setMinimumSize(new Dimension(100, 26));
        saveB.setPreferredSize(new Dimension(100, 26));
        saveB.setText(Local.getString("Save Bus"));
        saveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveBus();                
            }
        });
        this.getRootPane().setDefaultButton(saveB);
        cancelB.setMaximumSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setText(Local.getString("Cancel"));
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	cancelAction();
            }
        });
        buttonsPanel.add(saveB);
        buttonsPanel.add(cancelB);
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    private void cancelAction() {
    		this.dispose();
    }
    
    // Method to make sure all inputs all filled before submission
    private void VerifyInput() {
    		if(!nameField.getText().isEmpty() && !seatsField.getText().isEmpty()) {
    			saveB.setEnabled(true);
    		}else {
    			saveB.setEnabled(false);
    		}
    }
    
    // Save the bus by calling method saveBus from the Bus class
    private void saveBus() {
            String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
            BusCollection busCollection = new BusCollection(busCollectionFilePath);
            
    		int id = Integer.parseInt(idField.getText());
    		String name = nameField.getText();
    		int numSeats = Integer.parseInt(seatsField.getText());
    		newBus = new BusImpl(id, name, numSeats);
    		busCollection.addBus(newBus);
    		busCollection.exportBusCollection();
    		generateID();
    		idField.setText(generateID()+"");
    		seatsField.setText("");
    		nameField.setText("");
    		
    		busPanel.refreshTable();
    		
    }
    
    // Method to generate ID for bus
    private int generateID() {
    		Random rnd = new Random();
    		int id = 1 + rnd.nextInt(1000);
    		return id;
    }
}
