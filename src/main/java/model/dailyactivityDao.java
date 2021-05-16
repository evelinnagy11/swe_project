package model;

import com.google.inject.persist.Transactional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * DAO class for the {@link dailyactivity} entity.
 */
@RegisterBeanMapper(dailyactivity.class)
public interface dailyactivityDao {

    /**
     * It creates table if it not exists.
     */
    @SqlUpdate("CREATE TABLE if not exists dailyactivity (daily_id INTEGER PRIMARY KEY, activity_id INTEGER, activity_name VARCHAR, profile_id INTEGER, today DATE)")
    void createTable();

    /**
     * Inserts the following data to the dailyactivity table.
     *
     * @param daily_id The primary key for the row.
     * @param activity_id The activity id that identify the activity.
     * @param activity_name The name of the activity.
     * @param profile_id The profile_id identify the user.
     * @param today The date when the user save the activity.
     */
    @SqlUpdate("INSERT INTO dailyactivity VALUES (:daily_id, :activity_id, :activity_name, :profile_id, :today)")
    void insertDailyActivity(@Bind("daily_id") int daily_id, @Bind("activity_id") int activity_id, @Bind("activity_name") String activity_name, @Bind("profile_id") int profile_id, @Bind("today") Date today);

    /**
     * Lists the daily activities.
     *
     * @return A list of the saved activities.
     */
    @Transactional
    @SqlQuery("SELECT * FROM dailyactivity ORDER BY daily_id")
    List<dailyactivity> listDailyActivities();

    /**
     * Get the activity id with activity name.
     *
     * @param activity_name To identify and find the chosen activity.
     * @return An id of the chosen activity.
     */
    @Transactional
    @SqlQuery("SELECT activity_id FROM activities WHERE activity_name = :activity_name")
    int getActivityId(@Bind("activity_name") String activity_name);

    /**
     * Get the latest date by the profile id and the activity name.
     *
     * @param profile_id The id that identify the user.
     * @param activity_name The activity name that identify the row
     * @return The last day when the activity was saved.
     */
    @Transactional
    @SqlQuery("SELECT MAX(today) FROM dailyactivity WHERE profile_id = :profile_id AND activity_name = :activity_name")
    long getDateByActivity(@Bind("profile_id") int profile_id, @Bind("activity_name") String activity_name);

    /**
     * Get the list of the dates by the profile id.
     *
     * @param profile_id Identify the user.
     * @return A list of dates when the user saved activities.
     */
    @Transactional
    @SqlQuery("SELECT today FROM dailyactivity WHERE profile_id = :profile_id")
    List<Long> getListofDates(@Bind("profile_id") int profile_id);

}
