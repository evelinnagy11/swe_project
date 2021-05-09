package controller;

import model.profileDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
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
import org.jdbi.v3.core.Handle;
import org.sqlite.SQLiteDataSource;
import org.tinylog.Logger;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    public void loginUser(ActionEvent actionEvent) throws IOException {
        Jdbi jdbi = Jdbi.create("jdbc:sqlite:profiles.db")
                .installPlugin(new SqlObjectPlugin());
        Handle handle = jdbi.open();
        profileDao profiledao = handle.attach(profileDao.class);

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            errorLabel.setText("* Username or password is empty!");
            Logger.error("Username or password is empty!");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/moodpage.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<moodController>getController().initdata(usernameField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("{} user is logged in.", usernameField.getText());
        }
    }
}
