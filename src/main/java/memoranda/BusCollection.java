package main.java.memoranda;
/**
+ * File Name: BusCollection.java


+ * Description: The class creates and populates a Vector of BusImpl objects by reading in a JSON file 
+ *         parsing the data into separate BusImpl objects, as well as writing the Vector's BusImpl objects 
            back to the JSON file as a single JSONObject 
+ * Author: Jesse Sabbath
+ * Date: February 22, 2018
+ * 
+ */

import main.java.interfaces.Bus;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class BusCollection  {
    
    Vector<BusImpl> busList;
    BusImpl tempBus;
    String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
    FileReader in;
    
    public BusCollection() {
        busList = null;
        try{
            in = new FileReader(busCollectionFilePath);
            importBusCollection();
            in.close();
         }
        
         catch (Exception ex) {
             System.out.println("Exception importing from json: "+ ex.getMessage());
         }
    }
    
    /**
     * Constructor creates a BusCollection object from the contents of the file 
     * @param fileName
     */
    public BusCollection(String fileName){
        try{
            in = new FileReader(fileName);
            importBusCollection();
            in.close();
         }
        
         catch (Exception ex) {
             System.out.println("Exception importing from json: "+ ex.getMessage());
         }
      }

      public void addBus(BusImpl aBus) {
          busList.add(aBus);
      }
      
      public void removeBus(Bus aBus) {
          busList.remove(aBus);
      }
      
      /**
       * Returns the bus whose busID field matches the String argument.
       * @param busID A String representation of a bus's ID 
       * @return
       */
      public BusImpl getBus(int busID) {
          BusImpl aBus = new BusImpl();
          
          for (int i = 0; i < busList.size(); i++) {
              if (busList.elementAt(i).getId() == busID)
                  aBus = busList.elementAt(i);
              System.out.println(aBus.getName());
          }
          return aBus;
      }
      
      /**
       * Returns the BusCollection objects BusImpl type Vector of buss.
       * @return busList - A Vector of BusImple objects
       */
      public Vector<BusImpl> getBusList( ) {
          return busList; 
      }
      
      /**
       * Imports JSONObject from file and parses it to a Vector of BusImpl objects.
       */
      public void importBusCollection() {
          busList = new Vector<BusImpl>();
          
            try {
                    JSONObject obj = new JSONObject(new JSONTokener(in));
                    String [] bus = JSONObject.getNames(obj);
         
                    for (int i=0; i < bus.length; i++) {
                        tempBus = new BusImpl((JSONObject)obj.getJSONObject(bus[i]));
                        busList.addElement(tempBus);   
                     //   System.out.println(busList.elementAt(i).getName());
                    }

            }
            catch(NullPointerException e) {
                e.getMessage();
            }
      }
      
      /**
       * Creates a meta-JSONObject of BusImpls that have been converted to type JSONObject
       * and writes to the specified file location.
       * 
       */
      public void exportBusCollection()  {
          
          try {
                  FileWriter out = new FileWriter(busCollectionFilePath);
                  JSONObject obj = new JSONObject();
             
                  for (int i = 0; i < busList.size(); i++) {
                      
                      obj.put(Integer.toString(busList.elementAt(i).getId()), busList.elementAt(i).toJSONObject());
                     
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
      /*
       * Method: doesBusExist
       * Return: boolean
       * Description: Check whether a bus exists in collection
       */
      public boolean doesBusExist(String busID) {
  	    boolean doesExist = false;
  	        for(BusImpl bus: busList) {
  	        		String id = ""+bus.getId();
  	        		if(id.equalsIgnoreCase(busID)) {
  	        			doesExist = true;	
  	        		}
  	        }
  	    return doesExist;
  	}
      
  	/**
  	 * Method: editBus
  	 * Input: Bus ID, Edit - Information to be edited, 
  	 * Option - JOptionPane integer value.
  	 * Return: boolean
  	 */
  	public boolean editBus(String busID, String edit,int option) {
  	    boolean canEdit = false;
  	    	
  	  for(BusImpl bus: busList) {
    		String id = ""+bus.getId();
    		if(id.equalsIgnoreCase(busID)) {
    			 if(option == 0) {
    	  	            getBus(Integer.parseInt(busID)).setName(edit);
    	  	        } else if( option == 1) {
    	  	        	getBus(Integer.parseInt(busID)).setNumberOfSeats(Integer.parseInt(edit));
    	  	        } 
    			canEdit = true;	
    		}
  	  }  	    
  	    return canEdit;
  	}
}