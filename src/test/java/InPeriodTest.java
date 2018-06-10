package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import main.java.memoranda.date.CalendarDate;


class InPeriodTest {

    static CalendarDate test;
    
    @BeforeAll
    static void initAll() {
        test = new CalendarDate();
    }

    @Test
    public void isInPeriodTest() {
        
        CalendarDate today = test.today();

        CalendarDate testYesterday = CalendarDate.yesterday();
        CalendarDate testTomorrow = CalendarDate.tomorrow();

        assertTrue(today.inPeriod(testYesterday, testTomorrow));
        
        today = testYesterday;
        assertTrue(today.inPeriod(testYesterday, testTomorrow));
        
    }

}
