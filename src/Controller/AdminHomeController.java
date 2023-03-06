package Controller;

import Entities.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class AdminHomeController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnSpareParts;
    @FXML
    private Button btnReservations;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private StackPane mainView;
    @FXML
    private Pane pnlManageReservations;
    @FXML
    private Label adminNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminNameLabel.setText("Welcome, " /*+ CurrentUser.getUser().getName()*/);
    }

    public void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnHome) {
            mainView.getChildren().clear();
        } else if (event.getSource() == btnUsers) {
            mainView.getChildren().clear();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/manageUsers.fxml"));
                Parent included = loader.load();
                mainView = (StackPane) mainView.lookup("#mainView");
                mainView.getChildren().add(included);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == btnDashboard) {
            mainView.getChildren().clear();
        } else if (event.getSource() == btnSignout) {
            mainView.getChildren().clear();
            CurrentUser.setUser(null);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
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
    }
}
