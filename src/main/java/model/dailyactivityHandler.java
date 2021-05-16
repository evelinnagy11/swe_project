package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The class handle the dailyactivities table.
 */
public class dailyactivityHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:activities.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    dailyactivityDao dailyactivitydao = handle.attach(dailyactivityDao.class);
    profileHandler profilehandler = new profileHandler();

    /**
     * It creates the table.
     */
    public dailyactivityHandler(){
        dailyactivitydao.createTable();
    }

    /**
     * The method insert the activity to the table if the user is done with it.
     *
     * @param activityname To insert the activity name to the table.
     * @param username To get profile_id to identify the user.
     */
    public void doneActivity(String activityname, String username){
        int daily_id = dailyactivitydao.listDailyActivities().stream().mapToInt(dailyactivity::getDaily_id).max().orElseThrow() + 1;
        int activity_id = dailyactivitydao.getActivityId(activityname);
        int profile_id = profilehandler.getUserId(username);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        dailyactivitydao.insertDailyActivity(daily_id, activity_id,activityname, profile_id, date);
    }

    /**
     * Calls isTodayActivity to check the dates.
     *
     * @param activity_name Pass the activity_name to the isTodayActivity.
     * @param profile_id Pass the activity_name to the isTodayActivity.
     * @return If the date is today or not.
     */
    public boolean isActivityDone(String activity_name, int profile_id){
        dailyactivitydao.getListofDates(profile_id);
        if(isTodayActivity(profile_id, activity_name)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calls the isTodayActivityDate and checks if the date is today or not.
     *
     * @param profile_id To identify the user and get the date from the right user.
     * @param activity_name To identify the activity and get the right date.
     * @return If the date is today or not.
     */
    public boolean isTodayActivity(int profile_id, String activity_name){
        long activity_date = dailyactivitydao.getDateByActivity(profile_id, activity_name);
        boolean istoday = isTodayActivityDate(activity_date);
        return istoday;
    }

    /**
     * Checks if the activity_date is today.
     *
     * @param activity_date To compare with the today date.
     * @return If it is today, or not.
     */
    public boolean isTodayActivityDate(long activity_date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date(System.currentTimeMillis());
        String today_string = formatter.format(today);
        String activity_date_String = formatter.format(activity_date);
        if( !today_string.equals(activity_date_String) ){
            return true;
        } else {
            return false; }
    }
}
