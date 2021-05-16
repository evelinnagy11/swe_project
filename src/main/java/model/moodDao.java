package model;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.google.inject.persist.Transactional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * DAO class for the {@link mood} entity.
 */
@RegisterBeanMapper(mood.class)
public interface moodDao {

    /**
     * It creates the moods table if not exists.
     */
    @SqlUpdate("CREATE TABLE if not exists moods (mood_id INTEGER PRIMARY KEY, mood_name VARCHAR, profile_id INTEGER, today_date DATE)")
    void createTable();

    /**
     * Inserts a row to the moods table with the following data.
     *
     * @param mood_id The primary key to the row.
     * @param mood_name The name of the mood.
     * @param profile_id The id that identify the user.
     * @param today_date The date when the user saved the mood.
     */
    @SqlUpdate("INSERT INTO moods VALUES (:mood_id, :mood_name, :profile_id, :today_date)")
    void insertMood(@Bind("mood_id") int mood_id, @Bind("mood_name") String mood_name, @Bind("profile_id") int profile_id, @Bind("today_date") Date today_date);

    /**
     * Lists the moods.
     *
     * @return List of the moods.
     */
    @Transactional
    @SqlQuery("SELECT * FROM moods ORDER BY mood_id")
    List<mood> listMoods();

    /**
     * Get the latest date by the profile id.
     *
     * @param profile_id It identifies the user.
     * @return The latest date when the user saved a mood.
     */
    @Transactional
    @SqlQuery("SELECT MAX(today_date) FROM moods WHERE profile_id = :profile_id")
    long getDate(@Bind("profile_id") int profile_id);

    /**
     * Get the name of the moods by profile id.
     *
     * @param profile_id Identifies the user.
     * @return The list of the moods name.
     */
    @Transactional
    @SqlQuery("SELECT mood_name FROM moods WHERE profile_id = :profile_id")
    List<String> getMoodNames(@Bind("profile_id") int profile_id);
}
