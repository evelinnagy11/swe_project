package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        Map<Integer,Integer> listofDoneActivities = dailyactivitydao.getNumberofDoneActivities(profile_id);
        ObservableList<PieChart.Data> pieChartData;
        for(int i=0; i<listofDoneActivities.size(); i++) {
            //FXCollections.observableArrayList( new PieChart.Data(listofDoneActivities.keySet(),listofDoneActivities.values()));
        }
        return null;
    }
}
