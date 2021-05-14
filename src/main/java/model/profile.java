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

    @Id
    @Column(nullable = false)
    private int id;

    /**
     * The username of the users.
     */
    private String username;

    public int getId(profile profile) {
        return profile.id;
    }
}
