/*
 * AUTHOR: TRESOR CYUBAHIRO
 * SER 316
 * SPRING 2018
 * FRANKFURT
 * Panel with GUI for Adding new Buses 
 */
package main.java.memoranda.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.java.memoranda.BusImpl;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBusPanel extends JPanel {
	private JTextField busIDTextField;
	private JTextField numberOfSeatsTextField;

	/**
	 * Create the panel.
	 */
	public AddBusPanel() {
		setLayout(null);
		
		JLabel busIDLabel = new JLabel("Bus ID: ");
		busIDLabel.setBounds(30, 29, 58, 33);
		busIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(busIDLabel);
		
		busIDTextField = new JTextField();
		busIDTextField.setEnabled(false);
		busIDTextField.setEditable(false);
		busIDTextField.setBounds(125, 32, 130, 26);
		add(busIDTextField);
		busIDTextField.setColumns(10);
		
		JButton generateIDButton = new JButton("Generate ID");
		/*
		 * Action listener for "GenerateID" Button
		 */
		generateIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		generateIDButton.setBounds(267, 32, 117, 29);
		add(generateIDButton);
		
		JLabel NoOfSeatsLabel = new JLabel("No Of Seats:");
		NoOfSeatsLabel.setBounds(30, 74, 85, 16);
		add(NoOfSeatsLabel);
		
		numberOfSeatsTextField = new JTextField();
		numberOfSeatsTextField.setBounds(125, 70, 130, 26);
		add(numberOfSeatsTextField);
		numberOfSeatsTextField.setColumns(10);
		
		JButton addBusButton = new JButton("Add Bus");
		/*
		 * Action listener for "Add Bus" Button
		 */
		addBusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BusImpl newBus = new BusImpl();						// Create new Bus and populate its values below
				newBus.setNumberOfSeats(Integer.parseInt(numberOfSeatsTextField.getText()));
				newBus.setID(Integer.parseInt(busIDTextField.getText()));
			}
		});
		
		addBusButton.setBounds(267, 163, 117, 29);
		add(addBusButton);

	}
}
