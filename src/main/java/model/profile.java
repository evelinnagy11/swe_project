package model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The class represents the profiles of the users.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class profile {

    /**
     * The id of the profile.
     */
    @Id
    @Column(nullable = false)
    private int id;

    /**
     * The username of the users.
     */
    private String username;

    /**
     * Get the id of the profile.
     *
     * @param profile A profile in the profiles table.
     * @return The id of the profile.
     */
    public int getId(profile profile) {
        return profile.id;
    }
}
