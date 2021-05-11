package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.NoSuchElementException;

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

    public void createProfile(String username){
        id = profiledao.listprofiles().stream().mapToInt(profile::getId).max().orElseThrow() + 1;
        profile newProfile = new profile(id, username);
        profiledao.insertprofile(newProfile);
    }

    public boolean loginProfile(String username){
        int profile_id;
        try {
            profile_id = Integer.parseInt(profiledao.getid(username).orElseThrow());
        } catch (NoSuchElementException e){
            System.out.println("There is no such profile!");
            return false;
        }
        return true;
    }

    public int getUserId(String username){
        int profile_id = Integer.parseInt(profiledao.getid(username).orElseThrow());
        return profile_id;
    }

}
