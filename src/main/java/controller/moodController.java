package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.mood;

import java.util.Arrays;
import java.util.List;

public class moodController {

    private String username;

    private mood moods;
    private List<Image> moodImages;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button saveButton;

    private void drawMoods() {
        for (int i = 0; i < 5; i++) {
                //ImageView view = (ImageView) gameGrid.getChildren().get(i);
                //view.setImage(moodImages.get(gameState.getTray()[i].getValue()));
        }
    }
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

    public void buttonClick(ActionEvent actionEvent) {
        //TODO a gomb ertekenek atadasa
        saveButton.setVisible(true);
    }

    public void saveMood(ActionEvent actionEvent) {

    }
}
