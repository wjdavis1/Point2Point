package main.java.memoranda;
/**
+ * File Name: Tour.java
+ * Description: Provides a simple API for instantiation of of Tour objects and conversion to and from JSONObjects
+ * Author: Jesse Sabbath
+ * Date: February 22, 2018
+ * 
+ */
import main.java.memoranda.date.CalendarDate;
import org.json.*;

import java.time.LocalTime;
import java.io.Serializable;

public interface Tour extends Serializable {

	public String getTourID();
	public void setTourID(String tourID);
	/*public CalendarDate getDate();
	public void setDate(CalendarDate date);
	public LocalTime getTime();
	public void setTime(LocalTime time);*/
	public String getDate();
    public void setDate(String date);
    public String getTime();
    public void setTime(String time);
	public String getRouteID();
	public void setRouteID(String routeID);
	public String getDriverID();
	public void setDriverID(String driverID);
	public String getBusID();
	public void setBusID(String busID);
	public JSONObject toJSONObject();
	
}
