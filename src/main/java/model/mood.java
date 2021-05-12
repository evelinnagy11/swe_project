package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class mood {

    public int getMood_id(mood mood) {
        return mood.mood_id;
    }

    public enum Mood {

        ANGRY,
        SAD,
        TIRED,
        HAPPY,
        EXCITED
    }

    private int mood_id;
    private String mood_name;
    private int profile_id;
    private long today_date;

}
