package test.java;

/**
 * File Name: RouteTest.java
 * Description: Test Class for the RouteImpl class
 * Author: Wesley Davis
 * Created: 03/16/2018
 */

import static org.junit.jupiter.api.Assertions.*;

import main.java.memoranda.RouteImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RouteTest {
    
    static RouteImpl testRoute;
    static JSONObject testNodeObj;

    /**
     * Method: initAll()
     * Input: None
     * Return: None
     * 
     * Description: initAll is the equivalent to setup method in JUnit 4,
     * method goes through and sets up any objects that may be initialized
     * before tests are executed.
     */
    @BeforeAll
    static void initAll() {
        
        testRoute = new RouteImpl();

    }
    
    @Test
    public void TestEmptyRouteConstructor() {
        RouteImpl testRoute2 = new RouteImpl();
        
        assertNull(testRoute2.getRouteId());
        assertNull(testRoute2.getStartPoint());
        assertNull(testRoute2.getName());
        assertEquals(testRoute2.getLength(), 0.0);
        assertEquals(testRoute2.getDuration(), 0);
    }
    
    @Test
    public void TestingSettersAndGetters() {
        testRoute.setStartPoint("Mesa");
        testRoute.setRouteId("R0003");
        testRoute.setName("Mesa Route");
        testRoute.setLength(2.7);
        testRoute.setDuration(18);
        
        assertEquals(testRoute.getStartPoint(), "Mesa");
        assertEquals(testRoute.getRouteId(), "R0003");
        assertEquals(testRoute.getName(), "Mesa Route");
        assertEquals(testRoute.getLength(), 2.7);
        assertEquals(testRoute.getDuration(), 18);
    }
    


}
