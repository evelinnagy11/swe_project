package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.tinylog.Logger;

public class activitiesController {

    @FXML
    private TextField newActivity;

    @FXML
    private Label errorLabel;

    public void addActivity(ActionEvent actionEvent) {
        if(newActivity.getText().isEmpty()){
            errorLabel.setText("The activity field is empty!");
            Logger.error("The new activity field is empty!");
        } else {
            errorLabel.setText("");
            //TODO add the activity to the listview and save to a database
        }
    }
}
