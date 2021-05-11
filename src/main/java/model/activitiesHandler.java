package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Calendar;

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
        int activity_id = activitiesdao.listActivities().size() + 1;
        activitiesdao.insertActivity(activity_id, activity_name, profile_id);
    }
}
