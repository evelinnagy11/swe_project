package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The class represents the moods that the user can choose and save.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class mood {


    /**
     * Get the mood id from the mood.
     *
     * @param mood The copy of a mood.
     * @return The mood id.
     */
    public int getMood_id(mood mood) {
        return mood.mood_id;
    }

    /**
     * The mood id is generated in the moodHandler class.
     */
    @Id
    @Column(nullable = false)
    private int mood_id;

    /**
     * The name of the moods.
     */
    private String mood_name;

    /**
     * The id of the user, it generated in the profileHandler class.
     */
    private int profile_id;

    /**
     * The date of today in long format.
     */
    private long today_date;

}
