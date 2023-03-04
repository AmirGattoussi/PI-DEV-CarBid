package Controller;

import Entities.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public final class AdminHomeController implements Initializable {

    // int currentUser = CurrentUser.getUser().getId();
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
        adminNameLabel.setText("Welcome, " + CurrentUser.getUser().getName());
    }

    public void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnHome) {
            mainView.getChildren().clear();
        } else if (event.getSource() == btnUsers) {
            mainView.getChildren().clear();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("../View/manageUsers.fxml"));
                Parent included = loader.load();
                pnlManageReservations = (Pane) included.lookup("#pnlManageUsers");
                mainView.getChildren().add(pnlManageReservations);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == btnDashboard) {
            mainView.getChildren().clear();
        } else if (event.getSource() == btnSignout) {
            mainView.getChildren().clear();
        }
    }
}
