package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.*;
import java.util.List;

/**
 * It connects the moods.db to the moodController.
 */
public class moodHandler {

    Jdbi jdbi = Jdbi.create("jdbc:sqlite:moods.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    moodDao mooddao = handle.attach(moodDao.class);
    profileHandler profilehandler = new profileHandler();


    /**
     * Initialize a new mood.
     */
    public mood mood = new mood();

    /**
     * It creates the table.
     */
    public moodHandler(){
        mooddao.createTable();
    }

    /**
     * The void saves the moods to the table of moods.
     *
     * @param mood_name The method pass the mood_name value to the table.
     * @param username To get the profile_id from the profiles table.
     */
    public void saveMoods(String mood_name, String username){
            int mood_id =  mooddao.listMoods().stream().mapToInt(mood::getMood_id).max().orElseThrow() + 1;
            int profile_id = profilehandler.getUserId(username);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            mooddao.insertMood(mood_id, mood_name, profile_id, date);
    }

    /**
     * Calls the {@code isTodayDate} method and pass the {@code mood_date} variable to it.
     *
     * @param profile_id The method gets the date with profile_id.
     * @return If the date is today, or not.
     */
    public boolean isToday(int profile_id){
        long mood_date = mooddao.getDate(profile_id);
        boolean istoday = isTodayDate(mood_date);
        return istoday;
    }

    /**
     * Checks if the {@code mood_date} and the date of today are equal.
     *
     * @param mood_date That is the date that stores in the moods table.
     * @return If the date equals today.
     */
    public boolean isTodayDate(long mood_date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date(System.currentTimeMillis());
        String today_string = formatter.format(today);
        String mood_date_String = formatter.format(mood_date);
        if( !today_string.equals(mood_date_String) ){
            return true;
        } else {
            return false; }
    }

    /**
     * Convert a list of Strings to a list of ints.
     *
     * @param profile_id To get the list of the moods from moods table.
     * @return A list of ints that represent the users' moods.
     */
    public List<Integer> getMoodClick(int profile_id){
        List<String> moods = mooddao.getMoodNames(profile_id);
        List<Integer> mood_values = new ArrayList<Integer>();
        for(String value : moods){
            switch(value){
                case "angry":
                    mood_values.add(1);
                    break;
                case "sad":
                    mood_values.add(2);
                    break;
                case "tired":
                    mood_values.add(3);
                    break;
                case "happy":
                    mood_values.add(4);
                    break;
                case "excited":
                    mood_values.add(5);
                    break;
            }
        }
        return mood_values;
    }

    /**
     * Calculate the average mood of the logged in user.
     *
     * @param moods The method calculate from that list.
     * @return A double that represent the users' average mood.
     */
    public double averageMood(List<Integer> moods){
        double mood_avg = 0.0;

        if(moods.size() != 0){
            for(Integer mood : moods){
                mood_avg += mood;
            }
            mood_avg /= moods.size();
        }
            return mood_avg;

    }
}
