package main.java.memoranda;
/**
+ * File Name: TourCollection.java


+ * Description: The class creates and populates a Vector of TourImpl objects by reading in a JSON file 
+ * 		parsing the data into separate TourImpl objects, as well as writing the Vector's TourImpl objects 
			back to the JSON file as a single JSONObject 
+ * Author: Jesse Sabbath
+ * Date: February 22, 2018
+ * 
+ */

import org.json.JSONObject;
import org.json.JSONTokener;
import main.java.memoranda.TourImpl;
import java.util.Vector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class TourCollection  {
	
	Vector<TourImpl> tourList;
	TourImpl tour;
	String tourCollectionFilePath = "src/main/resources/data/tours/tours.json";
	FileReader in;
	
	public TourCollection() {
		tourList = null;
		tour = null;		
	}
	
	/**
	 * Constructor creates a TourCollection object from the contents of the file 
	 * @param fileName
	 */
	public TourCollection(String fileName){
		try{
			in = new FileReader(fileName);
			importTourCollection();
			in.close();
	     }
		
	     catch (Exception ex) {
	         System.out.println("Exception importing from json: "+ ex.getMessage());
	     }
	  }

	  public void addTour(TourImpl aTour) {
		  tourList.add(aTour);
	  }
	  
	  public void removeTour(Tour aTour) {
		  tourList.remove(aTour);
	  }
	  
	  /**
	   * Returns the tour whose tourID field matches the String argument.
	   * @param tourID A String representation of a tour's ID 
	   * @return
	   */
	  public TourImpl getTour(String tourID) {
		  TourImpl aTour = new TourImpl();
		  for (int i = 0; i < tourList.size(); i++) {
			  if (tourList.elementAt(i).getTourID().equals(tourID))
				  aTour = tourList.elementAt(i);
		  }
		  return aTour;
	  }
	  
	  /**
	   * Returns the TourCollection objects TourImpl type Vector of tours.
	   * @return tourList - A Vector of TourImple objects
	   */
	  public Vector<TourImpl> getTourList( ) {
		  return tourList; 
	  }
	  
	  /**
	   * Imports JSONObject from file and parses it to a Vector of TourImpl objects.
	   */
	  public void importTourCollection() {
		  tourList = new Vector<TourImpl>();
			try {
			        JSONObject obj = new JSONObject(new JSONTokener(in));
			        String [] tours = JSONObject.getNames(obj);
		 
			        for (int i=0; i < tours.length; i++) {
			            tour = new TourImpl((JSONObject)obj.getJSONObject(tours[i]));
			            tourList.addElement(tour);     	 
			        }

			}
			catch(NullPointerException e) {
                e.getMessage();
            }
	  }
	  
	  /**
	   * Creates a meta-JSONObject of TourImpls that have been converted to type JSONObject
	   * and writes to the specified file location.
	   * 
	   */
	  public void exportTourCollection()  {
		  
		  try {
		          FileWriter out = new FileWriter(tourCollectionFilePath);
		          JSONObject obj = new JSONObject();
			 
		          for (int i = 0; i < tourList.size(); i++) {
		              obj.put(tourList.elementAt(i).getTourID(), tourList.elementAt(i).toJSONObject());
		          }
		          out.write(obj.toString());
		          out.close();
		  }
		  catch (FileNotFoundException e){
			  System.out.println(e.getMessage());
		  }
		  catch (IOException e) {
			  System.out.println(e.getMessage());
		  }  
	  }
}


