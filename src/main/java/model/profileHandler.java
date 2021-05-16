package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.NoSuchElementException;

/**
 * Connects the profiles table to the profileController.
 */
public class profileHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:profiles.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    profileDao profiledao = handle.attach(profileDao.class);

    /**
     * The id of the profile.
     */
    public int id;

    /**
     * Initialize a new profile.
     */
    public profile profile = new profile();

    /**
     * Creates table for login.
     */
    public profileHandler() {
        profiledao.createTable();
        profiledao.createpasswordTable();
    }

    /**
     * Inserts a new profile to the profiles table.
     *
     * @param username The username that saved in the profiles table.
     * @param password The password that saved in encrypted form.
     */
    public void createProfile(String username, String password){
        id = profiledao.listprofiles().stream().mapToInt(profile::getId).max().orElseThrow() + 1;
        profile newProfile = new profile(id, username);
        profiledao.insertprofile(newProfile);
        String encryptedPassword = Encryption(password, 11);
        profiledao.insertpassword(id, encryptedPassword);
    }

    /**
     * Validates the users data.
     *
     * @param username The method checks if the username is correct.
     * @param password The method checks if the password is correct.
     * @return If the data are correct or not.
     */
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

    /**
     * Get user id from profiles table.
     *
     * @param username To identify the user and get profile_id.
     * @return The profile_id that belongs to the user.
     */
    public int getUserId(String username){
        int profile_id = Integer.parseInt(profiledao.getid(username).orElseThrow());
        return profile_id;
    }

    /**
     * The method encrypts the users' password.
     *
     * @param password The password that gets encrypted.
     * @param encrytionNumber The random number that encrypts.
     * @return The encrypted password.
     */
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
