package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 * The class connects the activities.db to the activitiesController.
 */
public class activitiesHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:activities.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    activitiesDao activitiesdao = handle.attach(activitiesDao.class);
    profileHandler profilehandler = new profileHandler();

    /**
     * It creates the activities table.
     */
    public activitiesHandler(){
        activitiesdao.createTable();
    }

    /**
     * The method insert a new row of activity.
     *
     * @param activity_name The name of the activity that saved to the table.
     * @param username To get profile_id from the profiles table.
     */
    public void addActivity(String activity_name, String username){
        int profile_id = profilehandler.getUserId(username);
        int activity_id = activitiesdao.listActivities().stream().mapToInt(activities::getActivity_id).max().orElseThrow() + 1;
        activitiesdao.insertActivity(activity_id, activity_name, profile_id);
    }

    /**
     * Procedure an observable list that stores the user activities.
     *
     * @param username To get profile_id from the profiles table.
     * @return An observable list that represent the users' activities.
     */
    public ObservableList<String> AddtoList(String username){
        int profile_id = profilehandler.getUserId(username);
        ObservableList<String> activeActivitiesList = FXCollections.observableArrayList();
        for(int i = 0; i < activitiesdao.listActivitiesbyprofile(profile_id).size(); i++){
            activeActivitiesList.add(activitiesdao.listActivitiesbyprofile(profile_id).get(i));
        }
        return activeActivitiesList;
    }

    /**
     * Deletes activity from the activities table.
     *
     * @param activityname To identify the activity.
     * @param username To identify the user and get profile_id.
     */
    public void deleteActivity(String activityname, String username){
        int profile_id = profilehandler.getUserId(username);
        int activity_id = activitiesdao.getActivityIdbyName(activityname);
        activitiesdao.deleteActivities( activity_id, profile_id);
    }

}
