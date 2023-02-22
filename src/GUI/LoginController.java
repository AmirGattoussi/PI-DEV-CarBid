/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class LoginController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private Button loginBtn;
    @FXML
    private Hyperlink registerLink;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    UserDao user = new UserDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        // perform login validation
        String emailIn = email.getText();
        String passwordIn = password.getText();
        boolean valid = user.login(emailIn, passwordIn);
        if (valid) {
            // login successful, display success message on screen
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully logged in!");
            alert.showAndWait();
        } else {
            // login failed, display error message on screen
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleLinkClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent loginParent;
        try {
            loginParent = loader.load();
            Scene loginScene = new Scene(loginParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void handleResetLink (ActionEvent event)  {
    }
        /* try {
            PasswordResetDialogController dialog = new PasswordResetDialogController();
            dialog.showAndWait();

            // Display a success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Password Reset");
            alert.setHeaderText(null);
            alert.setContentText("Your password has been reset. Please check your email for the new password.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
