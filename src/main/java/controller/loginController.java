package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import model.moodHandler;
import model.profileHandler;
import org.tinylog.Logger;

import java.io.IOException;

@Slf4j
public class loginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label registrationLabel;

    profileHandler profile = new profileHandler();
    moodHandler mood = new moodHandler();

    public void loginUser(ActionEvent actionEvent) throws IOException {

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            errorLabel.setText("* Username or password is empty!");
            Logger.error("Username or password is empty!");
        } else if (profile.loginProfile(usernameField.getText())){
            if(mood.isToday(profile.getUserId(usernameField.getText()))){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/moodpage.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<moodController>getController().initdata(usernameField.getText());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                Logger.info("{} user is logged in.", usernameField.getText());
            }
            else{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/activitiespage.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<activitiesController>getController().initdata(usernameField.getText());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                Logger.info("{} user is logged in.", usernameField.getText()); }
        } else {
            errorLabel.setText("* Wrong username or password!");
            Logger.error("There is no such profile!");
        }
    }

    public void registrationProfile(ActionEvent actionEvent) {
        profile.createProfile(usernameField.getText());
        registrationLabel.setText("Successful registration!");
        Logger.info(usernameField.getText() + " is registered.");
    }
}
