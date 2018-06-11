package main.java.memoranda.ui;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Dimension;

import java.util.HashMap;

import main.java.memoranda.DriverCollection;
import main.java.interfaces.Driver;
import main.java.memoranda.DriverImpl;
import main.java.memoranda.TourCollection;
import main.java.memoranda.TourImpl;
import main.java.memoranda.date.CalendarDate;

public class DriverTablePanel extends JPanel {

    private JTable table;
    private HashMap<String,Driver> driverCollection;
    private Object[][] data;
    private int index = 0;
    

    public DriverTablePanel() {
        super(new GridLayout(1,0));
        String[] columnNames = {"Driver ID","First Name", "Last Name", "Phone Number", "Age" };
        driverCollection = DriverCollection.getDrivers();
        data = new Object[driverCollection.keySet().size()][columnNames.length];
        
        for(String driverID : driverCollection.keySet()) {
            Driver driver = driverCollection.get(driverID);
            data[index][0] = driver.getDriverId();
            data[index][1] = driver.getFirstName();
            data[index][2] = driver.getLastName();
            data[index][3] = driver.getPhoneNumber();
            data[index][4] = driver.getAge();
            index++;
        }

        index = 0;

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000,700));
        //Adrian Vasquez 
        //US#78 Task #153
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }
   
    /*
     * Constructor for initializing table with data associated with a specific date
     */
    public DriverTablePanel(CalendarDate date) {
    	
        super();
        
        String[] columnNames = {"Tour ID", "Driver ID","First Name", "Last Name", "Phone Number", "Age" };
        driverCollection = DriverCollection.getDrivers();
        TourCollection tourCollection = new TourCollection("src/main/resources/data/tours/tours.json");
        int rowCount = 0;
        
        // Get Number of entries for that date
        for(int iter = 0; iter < tourCollection.getTourList().size(); iter++) {
        	
            TourImpl tempTour = (TourImpl)tourCollection.getTourList().elementAt(iter);
            String [] tempTourArr = tempTour.getDate().replaceAll("\\s","").split("/");
            
            int tempTourMonth = Integer.parseInt(tempTourArr[0]);
            int tempTourDay = Integer.parseInt(tempTourArr[1]);
            int tempTourYear = Integer.parseInt(tempTourArr[2]);
            
            CalendarDate tempDate = new CalendarDate(tempTourDay, tempTourMonth - 1, tempTourYear);
            
            if(tempDate.equals(date)) {
            	rowCount++;
            }
            
        }

        data = new Object[rowCount][columnNames.length];
        
        int counter = 0;
        
        for(int iter = 0; iter < tourCollection.getTourList().size(); iter++) {
        	
            TourImpl tempTour = (TourImpl)tourCollection.getTourList().elementAt(iter);
            String [] tempTourArr = tempTour.getDate().replaceAll("\\s","").split("/");
            
            int tempTourMonth = Integer.parseInt(tempTourArr[0]);
            int tempTourDay = Integer.parseInt(tempTourArr[1]);
            int tempTourYear = Integer.parseInt(tempTourArr[2]);
            
            CalendarDate tempDate = new CalendarDate(tempTourDay, tempTourMonth - 1, tempTourYear);
            
            if(tempDate.equals(date)) {
            	System.out.println("YEAH!! EQUALS!!!!!");
            	System.out.println(tempTour.getDriverID());
            	
            	if (driverCollection.containsKey(tempTour.getDriverID())) {
                	Driver tempDriver = (DriverImpl)driverCollection.get(tempTour.getDriverID());
                    data[counter][0] = tempTour.getTourID();
                	data[counter][1] = tempDriver.getDriverId();
                    data[counter][2] = tempDriver.getFirstName();
                    data[counter][3] = tempDriver.getLastName();
                    data[counter][4] = tempDriver.getPhoneNumber();
                    data[counter][5] = tempDriver.getAge();
                	counter++;
				}
            }
            
        }
        
        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000,700));
        //Adrian Vasquez 
        //US#78 Task #153
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }
	
}