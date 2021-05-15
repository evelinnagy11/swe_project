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
        profiledao.createpasswordTable();
    }

    public void createProfile(String username, String password){
        id = profiledao.listprofiles().stream().mapToInt(profile::getId).max().orElseThrow() + 1;
        profile newProfile = new profile(id, username);
        profiledao.insertprofile(newProfile);
        String encryptedPassword = Encryption(password, 11);
        profiledao.insertpassword(id, encryptedPassword);
    }

    public boolean loginProfile(String username, String password){
        int profile_id;
        try {
            profile_id = Integer.parseInt(profiledao.getid(username).orElseThrow());
            String passwordfromDB = profiledao.getPassword(profile_id);
            String encryptedPassword = Encryption(password, 11);
            if(passwordfromDB.equals(encryptedPassword)){
                return true;
            }
        } catch (NoSuchElementException e){
            System.out.println("There is no such profile!");
            return false;
        }
        return false;
    }

    public int getUserId(String username){
        int profile_id = Integer.parseInt(profiledao.getid(username).orElseThrow());
        return profile_id;
    }

    public String Encryption(String password, int encrytionNumber){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < password.length(); i++){
            int numberofChar = password.charAt(i);
            int encryptednumberofChar = numberofChar + encrytionNumber;
            char newCharacter = (char) encryptednumberofChar;
            sb.append(newCharacter);
        }
        return sb.toString();
    }

}
