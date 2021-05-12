package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;

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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date(System.currentTimeMillis());
        long mood_date = mooddao.getDate(profile_id);
        String today_string = formatter.format(today);
        String mood_date_String = formatter.format(mood_date);
        if( !today_string.equals(mood_date_String) ){
            return true;
        } else {
            return false; }
    }
}
