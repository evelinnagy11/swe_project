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

    /**
     * It creates the profiles table if it not exists.
     */
    @SqlUpdate("CREATE TABLE if not exists profiles (id INTEGER PRIMARY KEY, username VARCHAR)")
    void createTable();

    /**
     * It creates the passwords table if it not exists.
     */
    @SqlUpdate("CREATE TABLE if not exists passwords (id INTEGER PRIMARY KEY, password VARCHAR NOT NULL)")
    void createpasswordTable();

    /**
     * Inserts password to the passwords table.
     *
     * @param id The profile id.
     * @param password The encrypted password.
     */
    @SqlUpdate("INSERT INTO passwords VALUES (:id, :password)")
    void insertpassword(@Bind("id") int id, @Bind("password") String password);

    /**
     * Inserts profile to the profiles table.
     *
     * @param profile The profiles data.
     */
    @SqlUpdate("INSERT INTO profiles VALUES (:id, :username)")
    void insertprofile(@BindBean profile profile);

    /**
     * Get password from the passwords table.
     *
     * @param id The profile id of the user.
     * @return The encrypted password.
     */
    @Transactional
    @SqlQuery("SELECT password FROM passwords WHERE id = :id")
    String getPassword(@Bind("id") int id);

    /**
     * Get id from the profiles by username.
     *
     * @param username To get the user id.
     * @return The id of the profile.
     */
    @Transactional
    @SqlQuery("SELECT id FROM profiles WHERE username = :username")
    Optional<String> getid(@Bind("username") String username);

    /**
     * Lists profiles from the table.
     *
     * @return The list of the profiles.
     */
    @Transactional
    @SqlQuery("SELECT * FROM profiles ORDER BY id")
    List<profile> listprofiles();

}
