package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.memoranda.BusImpl;

class BusImplTest {

    
    @Test
    public void TestBusInitializer() {
        int id = 1234;
        int numberOfSeats = 4;
        BusImpl testBus2 = new BusImpl(id, "Magic School Bus",numberOfSeats);
        
        assertTrue(testBus2.getId() == id);
        assertTrue(testBus2.getNumberOfSeats() == numberOfSeats);
        
    }
    
    @Test
    public void TestingSchedule() {
        BusImpl testBus = new BusImpl(2033,"The Excalibur", 22);
        
        assertNotNull(testBus.getSchedule());
    }
    
    @Test void TestSettingBusID() {
        BusImpl testBus2 = new BusImpl(2033,"That Bus",22);
        testBus2.setID(3000);
        
        assertTrue(testBus2.getId() == 3000);
    }
}
