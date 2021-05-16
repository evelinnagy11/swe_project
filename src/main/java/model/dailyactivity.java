package model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The class represents the activities that the user done and saved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class dailyactivity {

    /**
     * The id of the daily activity row.
     */
    @Id
    @Column(nullable = false)
    private int daily_id;

    /**
     * The ids of activities, it generated in the activitiesHandler class.
     */
    private int activity_id;

    /**
     * The name of the activity.
     */
    private String activity_name;

    /**
     * The id of the user, it generated in the profileHandler class.
     */
    @Column(nullable = false)
    private int profile_id;

    /**
     * The date of today in long format.
     */
    @Column(nullable = false)
    private long today;
}
