package model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The class represents the activities that the user can add.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class activities {

    @Id
    @Column(nullable = false)
    private int activity_id;

    /**
     * The name of the activity.
     */
    private String activity_name;

    /**
     * The id of the user, it generated in the profileHandler class.
     */
    private int profile_id;
}
