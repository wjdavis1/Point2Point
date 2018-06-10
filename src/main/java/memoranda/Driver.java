package main.java.memoranda;

import org.json.JSONObject;

/**
 * FileName: Driver.java
 * Description: Interface for creating driver objects, any classes that are calling this interface need 
 * to implement interface.
 * @author Wesley Davis
 * Date: Feb 18th, 2018
 *
 */

public interface Driver {
	String getFullName();
	String getDriverId();
	int getAge();
	String getPhoneNumber();
    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setPhoneNumber(String phoneNumber);
    void setAge(int age);
	String getFirstName();
	String getLastName();
	JSONObject toJsonObject();
	String toJSONString();
}
