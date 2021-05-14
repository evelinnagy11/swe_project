package model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
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

    @SqlUpdate("CREATE TABLE if not exists moods (mood_id INTEGER PRIMARY KEY, mood_name VARCHAR, profile_id INTEGER, today_date DATE)")
    void createTable();

    @SqlUpdate("INSERT INTO moods VALUES (:mood_id, :mood_name, :profile_id, :today_date)")
    void insertMood(@Bind("mood_id") int mood_id, @Bind("mood_name") String mood_name, @Bind("profile_id") int profile_id, @Bind("today_date") Date today_date);

    @Transactional
    @SqlQuery("SELECT * FROM moods WHERE mood_id = :mood_id")
    Optional<mood> getMoods(@Bind("mood_id") int mood_id);

    @Transactional
    @SqlQuery("SELECT * FROM moods ORDER BY mood_id")
    List<mood> listMoods();

    @Transactional
    @SqlQuery("SELECT MAX(today_date) FROM moods WHERE profile_id = :profile_id")
    long getDate(@Bind("profile_id") int profile_id);

    @Transactional
    @SqlQuery("SELECT mood_name, COUNT(*) FROM moods WHERE profile_id = :profile_id GROUP BY mood_name")
    Map getNumberofMoods(@Bind("profile_id") int profile_id);
}
