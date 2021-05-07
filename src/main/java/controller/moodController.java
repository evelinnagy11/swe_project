package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class moodController {

    private String username;

    @FXML
    private Label usernameLabel;

    public void initdata(String userName) {
        this.username = userName;
        usernameLabel.setText(this.username);
    }
}
