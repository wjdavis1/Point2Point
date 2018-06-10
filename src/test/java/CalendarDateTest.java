package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import main.java.memoranda.date.CalendarDate;

/* Tresor Cyubahiro 02.07.2018
 * Add Unit Tests for CalendarDate class 
 */
class CalendarDateTest {
	CalendarDate date = new CalendarDate();
	/*
	 * Test whether the date of instantiation is the same as today's date.
	 */
	@Test
	public void todayDate() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
				
		assertTrue(date.getDay() == day);
		assertTrue(date.getMonth() == month);
		assertTrue(date.getYear() == year);
	}
	
	@Test
	public void YesterdayTest() {
		CalendarDate testYesterday = CalendarDate.yesterday();
		
		Calendar yesterday = Calendar.getInstance();
		
		yesterday.roll(Calendar.DATE, false);
		
		assertEquals(testYesterday.getDate().getTime(), yesterday.getTimeInMillis());
	}
	
	@Test
	/*
	 * Test Tomorrow Method
	 */
	public void TomorrowTest() {
		CalendarDate testTomorrow = CalendarDate.tomorrow();
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.roll(Calendar.DATE, true);
		
		assertEquals(testTomorrow.getDate().getTime(), tomorrow.getTimeInMillis());
	}

}
