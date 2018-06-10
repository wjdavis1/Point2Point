package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import main.java.memoranda.TourImpl;

class TourImplTest {

    JSONObject jObj = new JSONObject();
  
    TourImpl noArgTour = new TourImpl();
    TourImpl strArgTour = new TourImpl("0001", "R0003", "D0004", "B0042", "2/17/2018", "3:30");
    TourImpl JSONObjectArgTour; 
    TourImpl testTourImpl;
    
    @Test
    /**
     * Tests to make sure values of Tour match default values after construction.
     * All values should be blank strings
     */
    void TestNoArgConstructor() {
        
        assertNotNull(noArgTour);
        
        assertEquals(noArgTour.getTourID(), "");
        assertEquals(noArgTour.getRouteID(), "");
        assertEquals(noArgTour.getDriverID(), "");
        assertEquals(noArgTour.getBusID(), "");
        assertEquals(noArgTour.getDate(), "");
        assertEquals(noArgTour.getTime(), "");     
    }
   
    @Test
    /**
     * Test for correction construction and type exceptions
     */
    void TestStrArgConstructor() {
        assertNotNull(strArgTour);
        
        assertEquals(strArgTour.getTourID(), "0001");
        assertEquals(strArgTour.getRouteID(), "R0003");
        assertEquals(strArgTour.getDriverID(), "D0004");
        assertEquals(strArgTour.getBusID(), "B0042");
        assertEquals(strArgTour.getTime(), "3:30");
        assertEquals(strArgTour.getDate(), "2/17/2018");  
    }
    
    @Test
    /**
     * Test that the toJSONObject method creates and object of type JSONObject
     * and that the JSON Arg Constructor returns a TourImple after being passed a JSONObject object
     */
    void TestToJSONObjectConversion( ) {
        
        JSONObject localJObj;
        localJObj = strArgTour.toJSONObject(); 
        
        assertNotNull(localJObj);
        assertEquals(localJObj.getClass(), jObj.getClass());
       
        JSONObjectArgTour = new TourImpl(localJObj);
        
        assertNotNull(JSONObjectArgTour);
        assertEquals(JSONObjectArgTour.getClass(), noArgTour.getClass());  
    }
}
