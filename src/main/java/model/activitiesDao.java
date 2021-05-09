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

    @SqlUpdate("CREATE TABLE activities (activity_id INTEGER PRIMARY KEY, activity_name VARCHAR, profile_id INTEGER)")

    void createTable();

    @SqlUpdate("INSERT INTO activities VALUES (:activity_id, :activity_name, :profile_id)")
    void insertActivity(@Bind("activity_id") int activity_id, @Bind("activity_name") String activity_name, @Bind("profile_id") int profile_id);

    @SqlUpdate("INSERT INTO activities VALUES (:activity_id, :activity_name, :profile_id)")
    void insertActivity(@BindBean activities activities);

    @SqlQuery("SELECT * FROM activities WHERE activity_id = :activity_id")
    Optional<profile> getActivities(@Bind("activity_id") int activity_id);

    @SqlQuery("SELECT * FROM activities ORDER BY activity_id")
    List<profile> listActivities();

}
