package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;


class CurrentDateTest {

	@Test
	/*
	 * Test Evaluates the set() method
	 */
	public void setTest() {
		CurrentDate.set(CalendarDate.today());
		
		CalendarDate today = CalendarDate.today();
		
		assertEquals(CurrentDate.get().getFullDateString(), today.getFullDateString());	
	}
	
	@Test
	/*
	 * Test Evaluates the set() method
	 */
	public void resetTest() {
		CurrentDate.reset();
		
		CalendarDate newDate = new CalendarDate();
		
		assertEquals(CurrentDate.get().getFullDateString(), newDate.getFullDateString());	
	}

}
