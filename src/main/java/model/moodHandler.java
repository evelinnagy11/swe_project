package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Double.NaN;

public class moodHandler {

    Jdbi jdbi = Jdbi.create("jdbc:sqlite:moods.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    moodDao mooddao = handle.attach(moodDao.class);
    profileHandler profilehandler = new profileHandler();

    public mood mood = new mood();

    public moodHandler(){
        mooddao.createTable();
    }

    public void saveMoods(String mood_name, String username){
            int mood_id =  mooddao.listMoods().stream().mapToInt(mood::getMood_id).max().orElseThrow() + 1;
            int profile_id = profilehandler.getUserId(username);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            mooddao.insertMood(mood_id, mood_name, profile_id, date);
    }

    public boolean isToday(int profile_id){
        long mood_date = mooddao.getDate(profile_id);
        boolean istoday = isTodayDate(mood_date);
        return istoday;
    }

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
