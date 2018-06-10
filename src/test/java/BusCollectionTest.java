package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import main.java.memoranda.BusCollection;
import main.java.memoranda.BusImpl;

/*
 * Author: Tresor Cyubahiro
 * Date: 04.14.2018
 * Description: Test class for BusCollection methods
 */
public class BusCollectionTest {
	
	public String busCollectionFilePath = "src/main/resources/data/buses/buses.json";
    BusCollection busCollection = new BusCollection(busCollectionFilePath);
    
    /*
     * Test getBusList
     */
    @Test
    public void testGetBusList () {
    	BusImpl newBus = new BusImpl(000000000, "TestGetBusList",45);
    	busCollection.addBus(newBus);
    	Vector<BusImpl> busList = busCollection.getBusList();
    	assertTrue(busList.size() > 0);
    }
    
    /*
     * Test method deosBusExist
     */
    @Test
    public void testDoesBusExist() {
    	BusImpl newBus = new BusImpl(909090, "TestExistBus",45);
    	busCollection.addBus(newBus);
    	assertTrue(busCollection.doesBusExist("909090"));
    }
    
    /*
     * Test method addBus
     */
    @Test
    public void testAddBus() {
    	BusImpl newBus = new BusImpl(9999999, "TestBus",45);
    	busCollection.addBus(newBus);
    	assertTrue(busCollection.doesBusExist("9999999"));
    }
    
    /*
     * Test method removeBus
     */
    @Test
    public void removeBus() {
    	BusImpl newBus = new BusImpl(111111, "TestRemoveBus",45);
    	busCollection.addBus(newBus);
    	assertTrue(busCollection.doesBusExist("111111"));
    	busCollection.removeBus(newBus);
    	assertFalse(busCollection.doesBusExist("111111"));
    }
    
    /*
     * Test method getBus
     */
    @Test
    public void getBus() {
    	BusImpl newBus = new BusImpl(3333, "TestGetBus",45);
    	busCollection.addBus(newBus);
    	assertTrue(busCollection.doesBusExist("3333"));
    	BusImpl testBus = busCollection.getBus(3333);
    	assertEquals(newBus.getName(), testBus.getName());
    	assertEquals(newBus.getId(), testBus.getId());
    	assertEquals(newBus.getNumberOfSeats(), testBus.getNumberOfSeats());
    }
    
    /*
     * Test method editBus
     */
    @Test
    public void editBus() {
    	BusImpl newBus = new BusImpl(222222, "TestEditBus",45);
    	busCollection.addBus(newBus);
    	assertTrue(busCollection.doesBusExist("222222"));
    	busCollection.editBus("222222", "NewName", 0);
    	assertEquals(busCollection.getBus(222222).getName(), "NewName");
    	busCollection.editBus("222222", "30", 1);
    	assertEquals(busCollection.getBus(222222).getNumberOfSeats(), 30);
    }
}
