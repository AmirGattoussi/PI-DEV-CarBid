package GUI;

import Dao.UserDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PasswordResetDialogController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    private UserDao userDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDao = new UserDao();
    }

    @FXML
    private void handleConfirmButton(ActionEvent event) {
        String email = emailField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Check if new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("New password and confirm password do not match.");
            alert.showAndWait();
            return;
        }

        // Call password reset method in UserDao
        if (userDao.resetPassword(email, newPassword)) {
            // Password reset successful, close the dialog
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        } else {
            // Password reset failed, show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to reset password. Please check your email and try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
