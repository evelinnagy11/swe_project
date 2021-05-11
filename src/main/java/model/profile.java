package model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class profile {

    private int id;
    private String username;

    public int getId(profile profile) {
        return profile.id;
    }
}
