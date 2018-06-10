package test.java;
//jsabbath corrected very pesky bug which was creating bad data in the tours.json file 4/18/18
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.Test;

import main.java.memoranda.TourCollection;
import main.java.memoranda.TourImpl;

class TourCollectionTest {
    
    String tourCollectionFilePath = "src/main/resources/data/tours/tours.json";
    TourCollection testCollection = new TourCollection();
    TourCollection testCollection2 = new TourCollection(tourCollectionFilePath);
    TourImpl newTour1; 
    TourImpl newTour2;
    TourImpl newTour3;

    @Test
    public void EmptyTourTest() {
        assertTrue(testCollection.getTourList() == null);
    }
    
    @Test
    public void FullTourCollection() {
        assertTrue(testCollection2.getTourList() != null);
    }
    
    @Test
    public void DoesContainTour() {
        String tourID = "0004";
        assertNotNull(testCollection2.getTour(tourID) != null);
    }
    
    @Test
    public void CanAddTour() {
        newTour1 = new TourImpl("0002","R006","D002","B4343","01/01/2018","8:30AM");
        testCollection2.addTour(newTour1);
        assertTrue(testCollection2.getTour("0002") != null);
    }
    
    @Test
    public void CanRemoveTour() {
        newTour2 = new TourImpl("0002","R006","D002","B4343","01/01/2018","8:30AM");
        testCollection2.addTour(newTour2);
        testCollection2.removeTour(newTour2);
        assertTrue(!testCollection2.getTourList().contains(newTour2));
    }
    
    @Test
    public void DoesExportTourCollection() {
        TourCollection testCollection3;
        newTour3 = new TourImpl("0002","R006","D002","B4343","01/01/2018","8:30AM");
        testCollection2.addTour(newTour3);
        testCollection2.exportTourCollection();
        testCollection3 = new TourCollection(tourCollectionFilePath);
        assertTrue(testCollection3.getTour("0002").getTourID().equals(newTour3.getTourID()));
    }
    
    @After
    public void CleanUp() {
       testCollection2.removeTour(newTour1);
       testCollection2.removeTour(newTour3);
    }
    

}
