package test.java;

/**
 * File Name: RouteCollectionTest.java
 * Author: Wesley Davis
 * Created: 03/20/2018
 * Description: This is a JUnit Test Case for the Route Collection java class found in the 
 * main.java.memoranda package.
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import main.java.memoranda.RouteCollection;
import main.java.memoranda.RouteImpl;
import main.java.memoranda.Route;

import java.util.Map;
import java.io.IOException;
import java.util.HashMap;

class RouteCollectionTest {

    static RouteImpl emptyRoute;
    static RouteCollection emptyTestCollection;

    @BeforeAll
    static void initAll() {
        emptyRoute = new RouteImpl();
        emptyRoute.setStartPoint("Mesa");
        emptyRoute.setRouteId("R0005");
        emptyTestCollection = new RouteCollection("routes.json");
        emptyTestCollection.addRoute(emptyRoute);
    }

    @Test
    void TestCollectionNull() {
        assertNotNull(emptyTestCollection);
    }

    @Test
    void DoesRouteExistTest() {
        assertTrue(emptyTestCollection.doesRouteExist("R0005"));
        assertFalse(emptyTestCollection.doesRouteExist("R9999"));
    }

    @Test
    void DidCollectionAddNewRouteTest() {
        RouteImpl newEmptyRoute = new RouteImpl();
        assertTrue(emptyTestCollection.addRoute(newEmptyRoute));
        assertFalse(emptyTestCollection.addRoute(newEmptyRoute));
    }

    @Test
    void DidCollectionRemoveRouteTest() {

        RouteImpl newRoute = new RouteImpl();
        newRoute.setStartPoint("Mesa");
        newRoute.setRouteId("R0006");
        emptyTestCollection.addRoute(newRoute);
        assertTrue(emptyTestCollection.removeRoute("R0006"));
        assertFalse(emptyTestCollection.removeRoute("R0006"));
    }

    @Test
    void DidCollectionEditSpecificRouteTest() {
        emptyTestCollection.editRoute("R0001", "Phoenix", 0);
        emptyTestCollection.editRoute("R0001", "Mesa", 1);

    }

}
