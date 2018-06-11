package main.java.memoranda.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;

import main.java.memoranda.models.BusImpl;
import main.java.memoranda.models.TourCollection;
import main.java.memoranda.models.TourImpl;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.models.BusCollection;



/*Bus Table. SER316 Frankfurt 
 * AUTHOR: TRESOR CYUBAHIRO
 * SER 316
 * */

/*Bus Table Refactoring SER316 Frankfurt
 * AUTHOR: jsabbath
 * April 6, 2018
 */

public class BusTable extends JPanel {
  
    private Object[][] data;
    private BusCollection busCollection;
    String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
    Vector<BusImpl> busesList;
    
    public BusTable() {
        super();
        String[] columnNames = {"ID", "Name", "Number Of Seats"};
        busCollection = new BusCollection(busCollectionFilePath);
        data = new Object[busCollection.getBusList().size()][columnNames.length];
        
        for(int i = 0; i < busCollection.getBusList().size(); i++) {
            BusImpl tempBus = (BusImpl)busCollection.getBusList().elementAt(i);
                data[i][0] = tempBus.getId();
                data[i][1] = tempBus.getName();
                data[i][2] = tempBus.getNumberOfSeats();
           
        }
     
        JTable busTable = new JTable(data,columnNames);
        
        busTable.setShowVerticalLines(true);
        busTable.setShowHorizontalLines(true);
        busTable.setGridColor(Color.GRAY);
        busTable.setAutoCreateRowSorter(true);
        
        busTable.setPreferredScrollableViewportSize(new Dimension(1000,700));
        busTable.setFillsViewportHeight(true);
        busTable.setEnabled(false);
        busTable.getRowSorter().toggleSortOrder(0);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(busTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
    }
    
    /*
     * Constructor for initializing table with data associated with a specific date
     */
    public BusTable(CalendarDate date) {
    	
        super();
        
        String[] columnNames = {"Tour ID", "Bus ID", "Bus Name", "Number Of Seats"};
        busCollection = new BusCollection(busCollectionFilePath);
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
            	System.out.println(tempTour.getBusID());
            	if (busCollection.doesBusExist(tempTour.getBusID())) {
                	BusImpl tempBus = (BusImpl)busCollection.getBus(Integer.parseInt(tempTour.getBusID()));
                    data[counter][0] = tempTour.getTourID();
                	data[counter][1] = tempBus.getId();
                    data[counter][2] = tempBus.getName();
                    data[counter][3] = tempBus.getNumberOfSeats();
                	counter++;
				}
            }
            
        }
        
        JTable busTable = new JTable(data,columnNames);
        
        busTable.setShowVerticalLines(true);
        busTable.setShowHorizontalLines(true);
        busTable.setGridColor(Color.GRAY);
        busTable.setAutoCreateRowSorter(true);
        
        busTable.setPreferredScrollableViewportSize(new Dimension(1000,700));
        busTable.setFillsViewportHeight(true);
        busTable.setEnabled(false);
        busTable.getRowSorter().toggleSortOrder(0);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(busTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
    }

    //Deprecated methods from before BusCollection class existed
    //jsabbath April 6, 2018
    
 /*   	// Method to add titles to table
    void initColums() {
            for (int i = 0; i < columnNames.length; i++) {
                TableColumn col = new TableColumn();
                //col.setWidth(500);
                col.setHeaderValue(columnNames[i]);
               // this.addColumn(col);
        }
    }

    	// Method to repopulate table
    public void tableChanged() {
        initTable();
        initColums();
        updateUI();
    }

    	// Method to initialize table with data
    public void initTable() {
    		
    }

    // Method to load all current added buses
    private void loadBuses() {
            readFile();

    }
    
    // Method to read data file
    private void readFile() {
        String dataStorageFile = "/data/buses/buses.json";
        FileInputStream dataIn = null;
        FileOutputStream dataOut = null;
        File busesFile;
            busesFile = new File(App.class.getResource(dataStorageFile).getFile());
            try {
                dataIn = new FileInputStream(busesFile);
                dataObj = new JSONObject(new JSONTokener(dataIn));
                
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }*/
}