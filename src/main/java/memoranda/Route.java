package main.java.memoranda;

import java.util.List;

import org.json.JSONObject;

/**
 * FileName: Route.java Description: Interface for creating route objects, any
 * classes that are calling this interface need to implement interface.
 * 
 * @author James Ortner Date: Feb 21st, 2018
 *
 */

public interface Route {
    // String getDestination();
    String getRouteId();

    String getStartPoint();

    String getName();

    double getLength();

    double getDuration();
    
    List<NodeImpl> getRouteNodes();

    // void setDestination(String Destination);
    void setRouteId(String routeId);

    void setStartPoint(String startPoint);

    void setName(String name);

    void setLength(double length);

    void setDuration(double duration);

    JSONObject toJsonObject();

    String toJSONString();
}