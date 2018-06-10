package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import main.java.memoranda.RouteImpl;
import main.java.memoranda.NodeImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

class RouteImplTest {

    JSONObject jObj = new JSONObject();

    RouteImpl noArgRoute = new RouteImpl();
    RouteImpl strArgRoute = new RouteImpl("R0001","PolyTechnic","PolyTechnic Route", 5.0, 30.0);
    static RouteImpl includeNodes;
    RouteImpl JSONObjectArgRoute;
    RouteImpl testRouteImpl;

    
    static Set<NodeImpl> nodes;

    @BeforeAll
    static void initAll() {
        nodes = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            int count = i + 1;
            NodeImpl point = new NodeImpl("Point" + i, count * 12.0, count * 15.0);
            nodes.add(point);
        }

        includeNodes = new RouteImpl("R0002","PolyTechnic","PolyTechnic Route", 5.0, 30.0, nodes);
    }

    @Test
    /**
     * Tests to make sure values of Tour match default values after construction.
     * All values should be blank strings
     */
    void TestNoArgConstructor() {

        assertNotNull(noArgRoute);

        assertEquals(noArgRoute.getRouteId(), null);
        assertEquals(noArgRoute.getStartPoint(), null);
        assertEquals(noArgRoute.getName(), null);
        assertEquals(noArgRoute.getLength(), 0.0);
        assertEquals(noArgRoute.getDuration(), 0);
    }

    @Test
    /**
     * Test for correction construction and type exceptions
     */
    void TestStrArgConstructor() {
        assertNotNull(strArgRoute);

        assertEquals(strArgRoute.getRouteId(), "R0001");
        assertEquals(strArgRoute.getStartPoint(), "PolyTechnic");
        assertEquals(strArgRoute.getName(), "PolyTechnic Route");
        assertEquals(strArgRoute.getLength(), 5.0);
        assertEquals(strArgRoute.getDuration(), 30.0);
    }

//    @Test
//    /**
//     * Test that the toJSONObject method creates and object of type JSONObject and
//     * that the JSON Arg Constructor returns a RouteImpl after being passed a
//     * JSONObject object
//     */
//    void TestToJSONObjectConversion() {
//
//        JSONObject localJObj;
//        localJObj = includeNodes.toJsonObject();
//
//        assertNotNull(localJObj);
//        assertEquals(localJObj.getClass(), jObj.getClass());
//
//        RouteImpl argRoute = new RouteImpl(localJObj);
//
//        assertNotNull(argRoute);
//        assertEquals(argRoute.getClass(), noArgRoute.getClass());
//    }
}
