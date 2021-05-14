package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        int daily_id = dailyactivitydao.listDailyActivities().stream().mapToInt(dailyactivity::getDaily_id).max().orElseThrow() + 1;
        int activity_id = dailyactivitydao.getActivityId(activityname);
        int profile_id = profilehandler.getUserId(username);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        dailyactivitydao.insertDailyActivity(daily_id, activity_id,activityname, profile_id, date);
    }

    public ObservableList<PieChart.Data> fillPieChart(String username){
        int profile_id = profilehandler.getUserId(username);
        Map<String,Double> doneActivities = new HashMap<>();
        Map<String, Double> listofDoneActivities = dailyactivitydao.getNumberofDoneActivities(profile_id);
        /*listofDoneActivities.forEach(action -> {
            String key = (String) action.get("activity_name");
            Double value = (Double) action.get("count");
            doneActivities.put(key, value);
        });

        for(Map.Entry<String,Double> entry : listofDoneActivities.entrySet()){
            String name = entry.getKey();
            Double value = entry.getValue();
            doneActivities.merge(name, value, Double::sum);
        }*/
        ObservableList<PieChart.Data> pieChartData =
                doneActivities.entrySet().stream()
                        .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toCollection(() -> FXCollections.observableArrayList()));
        return pieChartData;
    }

    public boolean isActivityDone(String activity_name, int profile_id){
        dailyactivitydao.getListofDates(profile_id);
        if(isTodayActivity(profile_id, activity_name)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isTodayActivity(int profile_id, String activity_name){
        long activity_date = dailyactivitydao.getDateByActivity(profile_id, activity_name);
        boolean istoday = isTodayActivityDate(activity_date);
        return istoday;
    }

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
