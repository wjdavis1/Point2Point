package main.java.memoranda;

import java.io.Serializable;
import org.json.JSONObject;

/**
 * File Name: DriverImpl.java
 * Description: Implementation of the Driver Interface, used for 
 * storing Driver data such as name, age id if needed.
 * @author Wesley Davis
 * Date: Feb 18th, 2018
 */

/**
 * Class: DriverImpl
 * Description: SEE FILE DESCRIPTION ABOVE
 */
public class DriverImpl implements Driver, Serializable {
	
	private String firstName;
	private String lastName;
	private String driverID;
	private String phoneNumber;
	private int age;
	
	public DriverImpl() {
		firstName = null;
		lastName = null;
		driverID = null;
		age = 0;
	}
	
	public DriverImpl(JSONObject obj) {
		firstName = (String)obj.get("firstName");
		lastName = (String)obj.get("lastName");
		driverID = (String)obj.get("driverID");
		phoneNumber = (String)obj.get("phoneNumber");
		age = obj.getInt("age");
		
	}
	
	public DriverImpl(String firstName, String lastName, String driverID ,int age, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.driverID = driverID;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}


	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
	 * Method: getFullName
	 * Input: None
	 * Return: String
	 * Description: Gets the Drivers full name
	 */
	@Override
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method: getDriverId()
	 * Input: None
	 * Return: String
	 * Description: Gets the Driver ID
	 */
	@Override
	public String getDriverId() {
		return driverID;
	}

	/**
	 * Method: getAge
	 * Input: None
	 * Return: int
	 * Description: Gets the Age of the driver
	 */
	@Override
	public int getAge() {
		return age;
	}
	
	/**
	 * Method: getPhoneNumber
	 * Input: None
	 * Return: String
	 * Description: Gets the phone number of the driver
	 */
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Method: toJsonObject
	 * Input: None
	 * Return: JSONObject
	 * Description: Returns a JSONObject of the Driver class.
	 */
	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("firstName", firstName);
		obj.put("lastName", lastName);
		obj.put("driverID", driverID);
		obj.put("phoneNumber", phoneNumber);
		obj.put("age", age);
		return obj;
	}
	
	/**
	 * Method: toJSONString
	 * Input: None
	 * Return: None
	 * 
	 * Description: Converts the JSONObject of this class into a string
	 */
	public String toJSONString() {
		String jsonString;
		jsonString = toJsonObject().toString();
		return jsonString;
	}
	

}
