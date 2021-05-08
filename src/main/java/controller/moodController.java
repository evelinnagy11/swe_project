package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import model.mood;

import java.util.Arrays;
import java.util.List;

public class moodController {

    private String username;

    private mood moods;
    private List<Image> moodImages;

    @FXML
    private Label usernameLabel;

    public void initdata(String userName) {
        this.username = userName;
        usernameLabel.setText(this.username);
    }

    @FXML
    public void initialize() {

        moods = new mood();

        moodImages = Arrays.asList(
                new Image(getClass().getResource("/pictures/angry.png").toExternalForm()),
                new Image(getClass().getResource("/pictures/sad.png").toExternalForm()),
                new Image(getClass().getResource("/pictures/tired.png").toExternalForm()),
                new Image(getClass().getResource("/pictures/happy.png").toExternalForm()),
                new Image(getClass().getResource("/pictures/excited.png").toExternalForm())
        );

    }

}
