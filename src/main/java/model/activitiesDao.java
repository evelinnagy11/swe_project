package model;

import java.util.List;
import java.util.Optional;

import com.google.inject.persist.Transactional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * DAO class for the {@link activities} entity.
 */
@RegisterBeanMapper(activities.class)
public interface activitiesDao {

    /**
     * Creates activities table if it not exists.
     */
    @SqlUpdate("CREATE TABLE if not exists activities (activity_id INTEGER PRIMARY KEY, activity_name VARCHAR, profile_id INTEGER)")
    void createTable();

    /**
     * Insert a row to activities table with the following data.
     *
     * @param activity_id The id of the activity. That is a primary key.
     * @param activity_name The name of the activity.
     * @param profile_id The logged in users' id.
     */
    @SqlUpdate("INSERT INTO activities VALUES (:activity_id, :activity_name, :profile_id)")
    void insertActivity(@Bind("activity_id") int activity_id, @Bind("activity_name") String activity_name, @Bind("profile_id") int profile_id);

    /**
     * Lists the activities by the activity id.
     *
     * @return A list of activities.
     */
    @Transactional
    @SqlQuery("SELECT * FROM activities ORDER BY activity_id")
    List<activities> listActivities();

    /**
     * Lists the activities by the profile id.
     *
     * @param profile_id To list the logged in users' activities.
     * @return List of the users' activities.
     */
    @Transactional
    @SqlQuery("SELECT activity_name FROM activities WHERE profile_id = :profile_id ORDER BY activity_id")
    List<String> listActivitiesbyprofile(@Bind("profile_id") int profile_id);

    /**
     * Delete the chosen activity from the activities table.
     *
     * @param activity_id To get the row to delete it.
     * @param profile_id To identify the user and find the right row.
     */
    @Transactional
    @SqlUpdate("DELETE FROM activities WHERE activity_id = :activity_id AND profile_id = :profile_id")
    void deleteActivities(@Bind("activity_id") int activity_id, @Bind("profile_id") int profile_id);

    /**
     * Get activity_id from activities table with activity name.
     *
     * @param activity_name To get the activity_id.
     * @return The id of the chosen activity.
     */
    @Transactional
    @SqlQuery("SELECT activity_id FROM activities WHERE activity_name = :activity_name")
    int getActivityIdbyName(@Bind("activity_name") String activity_name);
}
