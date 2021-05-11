package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Calendar;

public class dailyactivityHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:activities.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    dailyactivityDao dailyactivitydao = handle.attach(dailyactivityDao.class);
    profileHandler profilehandler = new profileHandler();

    public dailyactivityHandler(){
        dailyactivitydao.createTable();
    }

    public void doneActivity(String activityname, String username){
        int daily_id = dailyactivitydao.listDailyActivities().size() + 1;
        int activity_id = dailyactivitydao.getActivityId(activityname);
        int profile_id = profilehandler.getUserId(username);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        dailyactivitydao.insertDailyActivity(daily_id, activity_id,activityname, profile_id, date);
    }
}
