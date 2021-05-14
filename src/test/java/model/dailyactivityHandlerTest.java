package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class dailyactivityHandlerTest {

    private dailyactivityHandler dailyactivityhandler;

    @BeforeEach
    public void setUp() throws Exception{
        dailyactivityhandler = new dailyactivityHandler();
    }

    @Test
    void isTodayActivityDate() {
        long now = System.currentTimeMillis();

        Date date = Date.valueOf("2021-04-30");
        long random_date = date.getTime();

        Date date1 = Date.valueOf("2021-05-10");
        long random_date1 = date1.getTime();

        assertTrue(dailyactivityhandler.isTodayActivityDate(random_date));
        assertTrue(dailyactivityhandler.isTodayActivityDate(random_date1));
        assertFalse(dailyactivityhandler.isTodayActivityDate(now));
    }
}