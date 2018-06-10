/*
 * Test class for class DriverImpl
 * Author: Tresor Cyubahiro
 */
package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import main.java.memoranda.DriverImpl;

public class DriverImplTest {
	
	DriverImpl driver = new DriverImpl("firstname", "lastname", "12345" , 35, "4807748368");
	
	@Test
	/*
	 * Method to test DriverImpl Constructor DriverImpl(String firstName, String lastName, String driverID ,int age, String phoneNumber)
	 */
	public void TestConstructor() {
		JSONObject driverObj = new JSONObject();
		driverObj.put("firstName", "firstname");
		driverObj.put("lastName", "lastname");
		driverObj.put("driverID", "12345");
		driverObj.put("age", 35);
		driverObj.put("phoneNumber", "4807748368");
		
		assertEquals(driverObj.get("firstName"), driver.getFirstName());
		assertEquals(driverObj.get("lastName"), driver.getLastName());
		assertEquals(driverObj.get("driverID"), driver.getDriverId());
		assertEquals(driverObj.get("age"), driver.getAge());
		assertEquals(driverObj.get("phoneNumber"), driver.getPhoneNumber());
	}
	
	@Test
	/*
	 * Method to test DriverImpl's setAge(int)
	 */
	public void TestSetName() {
		DriverImpl newDriver = new DriverImpl();
		newDriver.setAge(42);
		assertEquals(newDriver.getAge(), 42);
	}

}
