package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.activitiesHandler;
import org.tinylog.Logger;

public class activitiesController {

    activitiesHandler handler = new activitiesHandler();
    private String username;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField newActivity;

    @FXML
    private Label errorLabel;

    public void initdata(String userName) {
        this.username = userName;
        usernameLabel.setText(this.username);
    }

    public void addActivity(ActionEvent actionEvent) {
        if(newActivity.getText().isEmpty()){
            errorLabel.setText("The activity field is empty!");
            Logger.error("The new activity field is empty!");
        } else {
            errorLabel.setText("");
            //TODO add the activity to the listview and save to a database
            handler.addActivity(newActivity.getText(), usernameLabel.getText());
        }
    }
}
