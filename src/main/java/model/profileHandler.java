package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class profileHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:profiles.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    profileDao profiledao = handle.attach(profileDao.class);

    public int id;
    public profile profile = new profile();

    public profileHandler() {
        profiledao.createTable();
    }

    public void CreateProfile(String username){
        id = profiledao.listprofiles().size() + 1;
        profile newProfile = new profile(id, username);
        profiledao.insertprofile(newProfile);

    }

}
