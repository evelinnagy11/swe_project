package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Calendar;

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
}
