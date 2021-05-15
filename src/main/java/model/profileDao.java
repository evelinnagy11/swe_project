package model;

import java.util.List;
import java.util.Optional;

import com.google.inject.persist.Transactional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * DAO class for the {@link profile} entity.
 */
@RegisterBeanMapper(profile.class)
public interface profileDao {

    @SqlUpdate("CREATE TABLE if not exists profiles (id INTEGER PRIMARY KEY, username VARCHAR)")
    void createTable();

    @SqlUpdate("CREATE TABLE if not exists passwords (id INTEGER PRIMARY KEY, password VARCHAR NOT NULL)")
    void createpasswordTable();

    @SqlUpdate("INSERT INTO profiles VALUES (:id, :username)")
    void insertprofile(@Bind("id") int id, @Bind("username") String username);

    @SqlUpdate("INSERT INTO passwords VALUES (:id, :password)")
    void insertpassword(@Bind("id") int id, @Bind("password") String password);

    @SqlUpdate("INSERT INTO profiles VALUES (:id, :username)")
    void insertprofile(@BindBean profile profile);

    @Transactional
    @SqlQuery("SELECT * FROM profiles WHERE id = :id")
    Optional<profile> getprofiles(@Bind("id") int id);

    @Transactional
    @SqlQuery("SELECT password FROM passwords WHERE id = :id")
    String getPassword(@Bind("id") int id);

    @Transactional
    @SqlQuery("SELECT id FROM profiles WHERE username = :username")
    Optional<String> getid(@Bind("username") String username);

    @Transactional
    @SqlQuery("SELECT * FROM profiles ORDER BY id")
    List<profile> listprofiles();

}
