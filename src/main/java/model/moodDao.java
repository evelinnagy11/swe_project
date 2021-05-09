package model;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(mood.class)
public interface moodDao {

    @SqlUpdate("CREATE TABLE moods (mood_id INTEGER PRIMARY KEY, mood_name VARCHAR, profile_id INTEGER)")

    void createTable();

    @SqlUpdate("INSERT INTO moods VALUES (:mood_id, :mood_name, :profile_id)")
    void insertMood(@Bind("mood_id") int activity_id, @Bind("mood_name") String activity_name, @Bind("profile_id") int profile_id);

    @SqlUpdate("INSERT INTO moods VALUES (:mood_id, :mood_name, :profile_id)")
    void insertMood(@BindBean activities activities);

    @SqlQuery("SELECT * FROM moods WHERE mood_id = :mood_id")
    Optional<profile> getMoods(@Bind("mood_id") int mood_id);

    @SqlQuery("SELECT * FROM moods ORDER BY activity_id")
    List<profile> listMoods();

}
