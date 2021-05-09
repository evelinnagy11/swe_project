package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.mood;
import model.moodHandler;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class moodController {

    private String username;
    private mood moods;
    private List<Image> moodImages;
    moodHandler mood = new moodHandler();
    private String clickedmood = "";

    @FXML
    private Label usernameLabel;

    @FXML
    private Button saveButton;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private ImageView angry;
    @FXML
    private ImageView sad;
    @FXML
    private ImageView tired;
    @FXML
    private ImageView happy;
    @FXML
    private ImageView excited;


    private void drawMoods() {
        for (int i = 0; i < 5; i++) {
                ImageView view = (ImageView) buttonGrid.getChildren().get(i);
                view.setImage(moodImages.get(i));
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

        drawMoods();

    }

    public void angryClick(MouseEvent mouseEvent) {
        clickedmood = "angry";
        Logger.info("The user mood is angry.");
        saveButton.setVisible(true);
    }

    public void sadClick(MouseEvent mouseEvent) {
        clickedmood = "sad";
        Logger.info("The user mood is sad.");
        saveButton.setVisible(true);
    }

    public void tiredClick(MouseEvent mouseEvent) {
        clickedmood = "tired";
        Logger.info("The user mood is tired.");
        saveButton.setVisible(true);
    }

    public void happyClick(MouseEvent mouseEvent) {
        clickedmood = "happy";
        Logger.info("The user mood is happy.");
        saveButton.setVisible(true);
    }

    public void excitedClick(MouseEvent mouseEvent) {
        clickedmood = "excited";
        Logger.info("The user mood is excited.");
        saveButton.setVisible(true);
    }

    public void saveMood(ActionEvent actionEvent) throws IOException {
        mood.saveMood(clickedmood, username);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/activitiespage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("The mood is saved.");
    }

}
