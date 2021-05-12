package model;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(activities.class)
public interface activitiesDao {

    @SqlUpdate("CREATE TABLE if not exists activities (activity_id INTEGER PRIMARY KEY, activity_name VARCHAR, profile_id INTEGER)")

    void createTable();

    @SqlUpdate("INSERT INTO activities VALUES (:activity_id, :activity_name, :profile_id)")
    void insertActivity(@Bind("activity_id") int activity_id, @Bind("activity_name") String activity_name, @Bind("profile_id") int profile_id);

    @SqlUpdate("INSERT INTO activities VALUES (:activity_id, :activity_name, :profile_id)")
    void insertActivity(@BindBean activities activities);

    @SqlQuery("SELECT * FROM activities WHERE activity_id = :activity_id")
    Optional<activities> getActivities(@Bind("activity_id") int activity_id);

    @SqlQuery("SELECT * FROM activities ORDER BY activity_id")
    List<activities> listActivities();

    @SqlQuery("SELECT activity_name FROM activities WHERE profile_id = :profile_id ORDER BY activity_id")
    List<String> listActivitiesbyprofile(@Bind("profile_id") int profile_id);

    @SqlUpdate("DELETE FROM activities WHERE activity_id = :activity_id AND profile_id = :profile_id")
    void deleteActivities(@Bind("activity_id") int activity_id, @Bind("profile_id") int profile_id);

    @SqlQuery("SELECT activity_id FROM activities WHERE activity_name = :activity_name")
    int getActivityIdbyName(@Bind("activity_name") String activity_name);
}
