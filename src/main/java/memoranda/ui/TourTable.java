package main.java.memoranda.ui;


 /* Description: A table to display the contents of the TourCollection Vector on the Tour Panel
 * Author: Jesse Sabbath
 * Date: February 22, 2018
 * 
 */
import main.java.memoranda.TourCollection;
import main.java.memoranda.TourImpl;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.memoranda.TourCollection;
public class TourTable extends JPanel {
	
	JTable tourTable; 
	TourCollection tourCollection;
	AbstractTableModel model1;
	String date = "";
	
	public TourTable(String inDate) {
	    try {
	    date = inDate;	
	      jbInit(date);
	    }
	    catch(Exception ex) {
	      new ExceptionDialog(ex);
	    }
	}
	
	public void jbInit(String selDate) {
		tourCollection = new TourCollection("src/main/resources/data/tours/tours.json");
		int size = tourCollection.getTourList().size();
		String[] colNames = {"Tour ID", "Date", "Time", "Route ID", "Driver ID", "Bus ID"};
		Object[][] data = new Object[size][colNames.length];

		if(selDate == null)
		{    
		    for(int i = 0; i < tourCollection.getTourList().size(); i++) {
    			TourImpl tempTour = (TourImpl)tourCollection.getTourList().elementAt(i);
    				data[i][0] = tempTour.getTourID();
    				data[i][1] = tempTour.getDate();
    				data[i][2] = tempTour.getTime();
    				data[i][3] = tempTour.getRouteID();
    				data[i][4] = tempTour.getDriverID();
    				data[i][5] = tempTour.getBusID();	
    				
    		}
		}
		else
		{
		    //Get size of new Table
		    size=0;
		    //Get int values of selected date
		    String [] selDateArr = selDate.split("/");
		    int selDay = Integer.parseInt(selDateArr[0]);
		    int selMonth = Integer.parseInt(selDateArr[1]);
		    int selYear = Integer.parseInt(selDateArr[2]);
            for(int iter = 0; iter < tourCollection.getTourList().size(); iter++) {
                TourImpl tempTour = (TourImpl)tourCollection.getTourList().elementAt(iter);
                String [] tempTourArr = tempTour.getDate().replaceAll("\\s","").split("/");;
                int tempTourMonth = Integer.parseInt(tempTourArr[0]);
                int tempTourDay = Integer.parseInt(tempTourArr[1]);
                int tempTourYear = Integer.parseInt(tempTourArr[2]);
                if(selDay == tempTourDay && selMonth == tempTourMonth-1 && selYear == tempTourYear) {
                	size++;
                	System.out.println(tempTourDay+" - "+tempTourMonth+" - "+tempTourYear);
                }
            }
            //Create new table and only add entries equal to selected date
            data = new Object[size][colNames.length];
            if(size>0) {
                for(int iter = 0,index = 0; iter < tourCollection.getTourList().size(); iter++) {
                    TourImpl tempTour = (TourImpl)tourCollection.getTourList().elementAt(iter);
                    String [] tempTourArr = tempTour.getDate().replaceAll("\\s","").split("/");
                    int tempTourMonth = Integer.parseInt(tempTourArr[0]);
                    int tempTourDay = Integer.parseInt(tempTourArr[1]);
                    int tempTourYear = Integer.parseInt(tempTourArr[2]);
                    if(selDay == tempTourDay && selMonth == tempTourMonth-1 && selYear == tempTourYear) { 
                        data[index][0] = tempTour.getTourID();
                        data[index][1] = tempTour.getDate();
                        data[index][2] = tempTour.getTime();
                        data[index][3] = tempTour.getRouteID();
                        data[index][4] = tempTour.getDriverID();
                        data[index][5] = tempTour.getBusID();
                        index++;
                    }    
                }
            }
		}
		
		JTable tourTable = new JTable(data,colNames);
		tourTable.setShowVerticalLines(true);
		tourTable.setShowHorizontalLines(true);
		tourTable.setGridColor(Color.GRAY);
		tourTable.setAutoCreateRowSorter(true);
		tourTable.setPreferredScrollableViewportSize(new Dimension(1000,700));
		tourTable.setFillsViewportHeight(true);
		tourTable.setEnabled(false);
		tourTable.getRowSorter().toggleSortOrder(0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(tourTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane);
	
	}
		
}
