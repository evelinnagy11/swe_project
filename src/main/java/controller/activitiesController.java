package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.activitiesHandler;
import model.dailyactivityHandler;
import org.tinylog.Logger;

import java.util.Optional;

public class activitiesController {

    activitiesHandler handler = new activitiesHandler();
    dailyactivityHandler dailyhandler = new dailyactivityHandler();
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

    public void initdata(String userName) {
        this.username = userName;
        usernameLabel.setText(this.username);
        activeActivities.setItems(handler.AddtoList(usernameLabel.getText()));
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
        activeActivities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    openActivity(activeActivities.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    public void openActivity(String activity_name){
        Dialog<Pair<String, Boolean>> dialog = new Dialog<>();
        dialog.setTitle(activeActivities.getSelectionModel().getSelectedItem());
        dialog.setHeaderText("Are you done with this activity?");

        ButtonType doneButton = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
        ButtonType deleteButton = new ButtonType("Delete category", ButtonBar.ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(doneButton, deleteButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label activity = new Label(activeActivities.getSelectionModel().getSelectedItem());
        CheckBox inOut = new CheckBox();

        grid.add(new Label(), 0, 0);
        grid.add(activity, 1, 0);
        grid.add(new Label(), 0, 1);
        grid.add(inOut, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == doneButton) {
                dailyhandler.doneActivity(activeActivities.getSelectionModel().getSelectedItem(), username);
            }
            else if(dialogButton == deleteButton){
                handler.deleteActivity(activeActivities.getSelectionModel().getSelectedItem(), username);
            }
            return null;
        });

        Optional<Pair<String, Boolean>> result = dialog.showAndWait();
        //setOwnCategory(profile_id);
    }

}
