/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Dao.*;
import Entities.CurrentUser;
import Entities.User;
import Utils.PasswordHasher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.FileWriter;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField password;
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
    AdminDao admin = new AdminDao();
    AgentDao agent = new AgentDao();
    boolean valid = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void showLoginNotification() {
        String title = "CARBID";
        String message = "LOGGED IN";
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("../images/auction.png");
            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Java AWT Tray Demo");
            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                System.out.println("TrayIcon could not be added.");
            }
            trayIcon.displayMessage(title, message, MessageType.INFO);
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        // perform login validation
        String emailIn = email.getText();
        String passwordIn = password.getText();
        String hashedPassword = PasswordHasher.hash(passwordIn);
        valid = user.login(emailIn, hashedPassword);
        if (valid) {
            // login successful, display success message on screen
            showLoginNotification();
            int passThroughUserID = user.getUserIdAtLogin(emailIn);
            // Get the user object from the database
            User loggedUser = user.getUserByMail(emailIn);
            // Set the currently logged-in user in the CurrentUser class
            CurrentUser.setUser(loggedUser);
            // Log the login information
            try {
                try (FileWriter writer = new FileWriter("login_log.txt", true)) {
                    writer.write(emailIn + "," + new Date().toString() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Failed to write to login log file");
            }
            // Login to Admin Interface
            if (admin.isAdmin(passThroughUserID)) {
                Parent root = FXMLLoader.load(getClass().getResource("../View/AdminHome.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } // Login to Agent Interface
            else if (agent.isAgent(passThroughUserID)) {
                Parent root = FXMLLoader.load(getClass().getResource("../View/mainNavigationAgency.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("../View/mainNavigation.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Register.fxml"));
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
    private void handleResetLink(ActionEvent event) {
        // Create the dialog box
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Reset Password");
        dialog.setHeaderText("Enter your email and new password");

        // Set up the buttons
        ButtonType resetButtonType = new ButtonType("Reset", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(resetButtonType, ButtonType.CANCEL);

        // Create the email and password fields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("New Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        gridPane.add(new Label("Email:"), 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(new Label("New Password:"), 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(new Label("Confirm Password:"), 0, 2);
        gridPane.add(confirmPasswordField, 1, 2);

        // Disable the reset button until all fields are filled in
        Node resetButton = dialog.getDialogPane().lookupButton(resetButtonType);
        resetButton.setDisable(true);

        // Validate the input when the fields are changed
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            resetButton.setDisable(newValue.trim().isEmpty() || passwordField.getText().isEmpty()
                    || confirmPasswordField.getText().isEmpty());
        });
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            resetButton.setDisable(newValue.trim().isEmpty() || emailField.getText().isEmpty()
                    || confirmPasswordField.getText().isEmpty());
        });
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            resetButton.setDisable(
                    newValue.trim().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty());
        });

        dialog.getDialogPane().setContent(gridPane);

        // Handle the reset button click
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == resetButtonType) {
                String emailReset = emailField.getText();
                String newPassword = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                if (!newPassword.equals(confirmPassword)) {
                    // Show an error message if the passwords don't match
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Passwords do not match");
                    alert.setContentText("Please make sure the new password and confirmation match.");
                    alert.showAndWait();
                } else if (!user.doesUserExist(emailReset)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No User");
                    alert.setContentText("User with this email does not exist");
                    alert.showAndWait();
                } else {
                    // Perform the password reset action
                    user.resetPassword(emailReset, newPassword);
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

}
