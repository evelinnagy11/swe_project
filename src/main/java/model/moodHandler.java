package model;

import controller.moodController;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;

import java.util.Calendar;
import java.util.Optional;

public class moodHandler {

    Jdbi jdbi = Jdbi.create("jdbc:sqlite:moods.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    moodDao mooddao = handle.attach(moodDao.class);
    profileDao profiledao = handle.attach(profileDao.class);

    public mood mood = new mood();

    public moodHandler(){
        mooddao.createTable();
    }

    public void saveMoods(String mood_name, String username){
            int profile_id;
            int mood_id =  mooddao.listMoods().stream().mapToInt(mood::getMood_id).max().orElseThrow() + 1;
            //profile_id = Integer.parseInt(profiledao.getid(username).orElseThrow());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            mooddao.insertMood(mood_id, mood_name, 1, date);
    }
}
