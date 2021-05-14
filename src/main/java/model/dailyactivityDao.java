package model;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RegisterBeanMapper(dailyactivity.class)
public interface dailyactivityDao {
    @SqlUpdate("CREATE TABLE if not exists dailyactivity (daily_id INTEGER PRIMARY KEY, activity_id INTEGER, activity_name VARCHAR, profile_id INTEGER, today DATE)")

    void createTable();

    @SqlUpdate("INSERT INTO dailyactivity VALUES (:daily_id, :activity_id, :activity_name, :profile_id, :today)")
    void insertDailyActivity(@Bind("daily_id") int daily_id, @Bind("activity_id") int activity_id, @Bind("activity_name") String activity_name, @Bind("profile_id") int profile_id, @Bind("today") Date today);

    @SqlUpdate("INSERT INTO dailyactivity VALUES (:daily_id, :activity_id, :activity_name, :profile_id, :today)")
    void insertDailyActivity(@BindBean activities activities);

    @SqlQuery("SELECT * FROM dailyactivity WHERE daily_id = :daily_id")
    Optional<dailyactivity> getDailyActivities(@Bind("daily_id") int daily_id);

    @SqlQuery("SELECT * FROM dailyactivity ORDER BY daily_id")
    List<dailyactivity> listDailyActivities();

    @SqlQuery("SELECT activity_name FROM dailyactivity WHERE profile_id = :profile_id ORDER BY daily_id")
    List<String> listDailyActivitiesbyprofile(@Bind("profile_id") int profile_id);

    @SqlQuery("SELECT activity_id FROM activities WHERE activity_name = :activity_name")
    int getActivityId(@Bind("activity_name") String activity_name);

    @SqlQuery("SELECT activity_name, COUNT(*) FROM dailyactivity WHERE profile_id = :profile_id GROUP BY activity_name")
    Map<String,Double> getNumberofDoneActivities(@Bind("profile_id") int profile_id);

    @SqlQuery("SELECT MAX(today) FROM dailyactivity WHERE profile_id = :profile_id")
    long getDate(@Bind("profile_id") int profile_id);

    @SqlQuery("SELECT MAX(today) FROM dailyactivity WHERE profile_id = :profile_id AND activity_name = :activity_name")
    long getDateByActivity(@Bind("profile_id") int profile_id, @Bind("activity_name") String activity_name);

    @SqlQuery("SELECT today FROM dailyactivity WHERE profile_id = :profile_id")
    List<Long> getListofDates(@Bind("profile_id") int profile_id);

    @SqlQuery("SELECT activity_name, COUNT(*) FROM dailyactivity WHERE activity_id = :activity_id GROUP BY activity_name")
    List<String> getActivityNames(@Bind("activity_id") int activity_id);
}
