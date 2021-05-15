package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Test
    void averageMoodTest(){
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(5);

        list2.add(3);
        list2.add(2);
        list2.add(4);
        list2.add(4);
        list2.add(5);

        assertEquals(0.0, moodhandler.averageMood(list));
        assertEquals(3.0, moodhandler.averageMood(list1));
        assertEquals(3.6, moodhandler.averageMood(list2));

    }
}