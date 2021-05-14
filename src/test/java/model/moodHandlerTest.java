package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class moodHandlerTest {

    private moodHandler moodhandler;

    @BeforeEach
    public void setUp() throws Exception{
        moodhandler = new moodHandler();
    }

    @Test
    void isTodayDate() {
        long now = System.currentTimeMillis();

        Date date = Date.valueOf("2015-03-31");
        long random_date = date.getTime();

        Date date1 = Date.valueOf("2021-05-10");
        long random_date1 = date1.getTime();

        assertTrue(moodhandler.isTodayDate(random_date));
        assertTrue(moodhandler.isTodayDate(random_date1));
        assertFalse(moodhandler.isTodayDate(now));

    }
}