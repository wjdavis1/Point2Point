package main.java.memoranda.ui;

/**
 * File Name: FindBusPanel.java
 * Description: A new JFrame window that allows the user to find a bus by ID
 * Author: Tresor Cyubahiro
 * Date: April 14th, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.java.memoranda.BusImpl;
import main.java.memoranda.BusCollection;
import java.io.IOException;

public class FindBusPanel extends JFrame {
	
	JLabel idInputLabel;
	GridLayout gridLayout = new GridLayout(5,2, 8, 10);
	BorderLayout borderLayout = new BorderLayout();
	JPanel inputPanel, confirmationPanel;
	JTextField idInputEntry;
	JButton find, cancel;
	private BusCollection busCollection;
	
	public FindBusPanel() {
		super("Find Bus by ID");
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
		idInputLabel = new JLabel("Enter Bus ID: ", SwingConstants.LEFT);
		idInputEntry = new JTextField(10);
		find = new JButton("Find");
		cancel = new JButton("Cancel");
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputPanel = new JPanel();
		confirmationPanel = new JPanel();
		
		inputPanel.add(idInputLabel);
		inputPanel.add(idInputEntry);

		find = new JButton("Find Bus");
		cancel = new JButton("Cancel");
		
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findBus(idInputEntry.getText());
				
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
	 * Method: findBus
	 * Input: String
	 * Return: None
	 * Description: Looks through the bus data set and displays data associated with bus id if found or display an error message
	 */
	private void findBus(String busID) {
		String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
		BusCollection  busCollection = new BusCollection(busCollectionFilePath);
		if(busCollection.doesBusExist(busID)) {
			System.out.println("Yes");
			BusImpl bus = busCollection.getBus(Integer.parseInt(busID));
			JOptionPane.showMessageDialog(this, "ID: "+bus.getId()+"\nName: "+bus.getName()+
					"\nNumber Of Seats: "+bus.getNumberOfSeats()+"\n ");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Bus with ID: "+busID+" does not exits");
		}
	}

}
