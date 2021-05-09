package model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface moodDao {

    @SqlUpdate("CREATE TABLE if not exists moods (mood_id INTEGER PRIMARY KEY, mood_name VARCHAR, profile_id INTEGER, today_date DATE)")

    void createTable();

    @SqlUpdate("INSERT INTO moods VALUES (:mood_id, :mood_name, :profile_id, :today_date)")
    void insertMood(@Bind("mood_id") int mood_id, @Bind("mood_name") String mood_name, @Bind("profile_id") int profile_id, @Bind("today_date") Date today_date);

    @SqlQuery("SELECT * FROM moods WHERE mood_id = :mood_id")
    Optional<mood> getMoods(@Bind("mood_id") int mood_id);

    @SqlQuery("SELECT * FROM moods ORDER BY mood_id")
    List<mood> listMoods();

}
