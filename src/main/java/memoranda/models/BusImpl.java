/*
 * AUTHOR: TRESOR CYUBAHIRO
 * SER 316
 * SPRING 2018
 * FRANKFURT
 * Bus implememntation
 * 
 * Edited April 2018 jsabbath
 * to transfer data input/out to the Bus Collection class
 * and add Json arg constructor and toJson() method
 */
package main.java.memoranda.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

import main.java.interfaces.Bus;
import org.json.*;

public class BusImpl implements Bus {
    
    private int id;
    private String name;
    private int numberOfSeats;
    public static Vector<Date> schedule = new Vector<Date>();
    private final String dataStorageFile = "/data/buses/buses.json";
    FileInputStream dataIn = null;
    FileOutputStream dataOut = null;
    JSONObject dataObj = null;
    private File busesFile;
    
    public BusImpl() {
        id = 0;
        numberOfSeats = 0;
        name = " ";
        dataObj = new JSONObject();
        //readFile();
    }
    
   
    public BusImpl(int id, String name,int seats) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = seats;
    }
    
    public BusImpl(JSONObject busObject) {
        id = busObject.getInt("id");
        numberOfSeats = busObject.getInt("seats");
        name = busObject.getString("name");
        
    }
    
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("seats", numberOfSeats);
        obj.put("name", name);
        return obj; 
    }
    
	/*
	 * (non-Javadoc)
	 * @see main.java.interfaces.Bus#setName()
	 */
	public void setName(String name) {
		this.name = name;
	}
	
    /*
     * (non-Javadoc)
     * @see main.java.interfaces.Bus#getname()
     */
	public String getName() {
		
		return name;
	}
    
    /*
     * @see main.java.interfaces.Bus#getNumberOfSeats()
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    
    /*
     * @see main.java.interfaces.Bus#getId()
     */
    public int getId() {
        
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    /*
     * @see main.java.interfaces.Bus#setNumberOfSeats()
     */
    public void setNumberOfSeats(int seats) {
        numberOfSeats = seats;
    }
    /*
     * @see main.java.interfaces.Bus#addToSchedule(java.sql.Date)
     */
    public void addToSchedule(Date scheduleDate, Time scheduleTime) {
        schedule.add(scheduleDate);
    }
    /*
     * @see main.java.interfaces.Bus#removeFromSchedule(java.sql.Date)
     */
    public void removeFromSchedule(Date scheduleDate, Time scheduleTime) {
        
    }
    /*
     * @see main.java.interfaces.Bus#getSchedule()
     */
    public Vector<Date> getSchedule() {
        
        return schedule;
    }
    
    /*
     * (non-Javadoc)
     * @see main.java.interfaces.Bus#saveBus()
     */
    public void saveBus(int id, String name, int numSeats) {
        JSONObject bus = new JSONObject();
        bus.put("name", name);
        bus.put("id", id);
        bus.put("seats", numSeats);
        String newBusId = id+"";
        dataObj.put(newBusId, bus);
        //writeToFile();
    }
    
//    /*
//     * (non-Javadoc)
//     * @see main.java.interfaces.Bus#readBusData()
//     */
//    public JSONObject readBusData() {
//        JSONObject bus = null;
//        readFile();
//        bus = (JSONObject) dataObj.get(name);
//        return bus;
//    }
//    
//    // Method to read data file
//    private void readFile() {
//    		busesFile = new File(App.class.getResource(dataStorageFile).getFile());
//    		try {
//				dataIn = new FileInputStream(busesFile);
//				dataObj = new JSONObject(new JSONTokener(dataIn));
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    }
//    
//    // Method to write data to file
//    private void writeToFile() {
//    		busesFile = new File(App.class.getResource(dataStorageFile).getFile());
//        try {
//            dataOut = new FileOutputStream(busesFile);
//            dataOut.write(dataObj.toString().getBytes());
//            System.out.println(dataObj.toString(2));
//            //populatePoints();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
