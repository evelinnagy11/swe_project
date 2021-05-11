package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private ListView<String> activeActivities;

    @FXML
    private Label activityName;

    @FXML
    private CheckBox doneCheckBox = new CheckBox();

    @FXML
    private Button saveButton;

    public void initdata(String userName) {
        this.username = userName;
        usernameLabel.setText(this.username);
        activeActivities.setItems(handler.AddtoList(usernameLabel.getText()));
        doneCheckBox.setVisible(false);
    }

    public void addActivity(ActionEvent actionEvent) {
        if(newActivity.getText().isEmpty()){
            errorLabel.setText("The activity field is empty!");
            Logger.error("The new activity field is empty!");
        } else {
            errorLabel.setText("");
            handler.addActivity(newActivity.getText(), usernameLabel.getText());
            activeActivities.setItems(handler.AddtoList(usernameLabel.getText()));
        }
    }

    public void chooseActivity(MouseEvent mouseEvent) {
        activityName.setText(activeActivities.getSelectionModel().getSelectedItem());
        activityName.setVisible(true);
        //doneCheckBox = new CheckBox();
        doneCheckBox.setVisible(true);
        saveButton.setVisible(true);
    }
}
