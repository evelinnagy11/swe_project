package model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class activities {

    private int activity_id;
    private String activity_name;
    private int profile_id;
}
