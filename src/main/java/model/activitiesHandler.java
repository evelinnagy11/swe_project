package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class activitiesHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:activities.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    activitiesDao activitiesdao = handle.attach(activitiesDao.class);
    profileHandler profilehandler = new profileHandler();

    public activitiesHandler(){
        activitiesdao.createTable();
    }

    public void addActivity(String activity_name, String username){
        int profile_id = profilehandler.getUserId(username);
        int activity_id = activitiesdao.listActivities().stream().mapToInt(activities::getActivity_id).max().orElseThrow() + 1;
        activitiesdao.insertActivity(activity_id, activity_name, profile_id);
    }

    public ObservableList<String> AddtoList(String username){
        int profile_id = profilehandler.getUserId(username);
        ObservableList<String> activeActivitiesList = FXCollections.observableArrayList();
        for(int i = 0; i < activitiesdao.listActivitiesbyprofile(profile_id).size(); i++){
            activeActivitiesList.add(activitiesdao.listActivitiesbyprofile(profile_id).get(i));
        }
        return activeActivitiesList;
    }

    public void deleteActivity(String activityname, String username){
        int profile_id = profilehandler.getUserId(username);
        int activity_id = activitiesdao.getActivityIdbyName(activityname);
        activitiesdao.deleteActivities( activity_id, profile_id);
        // TODO delete activity from the observable list too
    }

}
