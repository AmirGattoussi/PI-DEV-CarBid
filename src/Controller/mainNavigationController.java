package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public final class mainNavigationController implements Initializable {

    // int currentUser = CurrentUser.getUser().getId();

    @FXML
    private Button btnHome;
    @FXML
    private Button btnAuction;
    @FXML
    private Button btnOrders;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnHome) {
            mainView.getChildren().clear();
            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Register.fxml"));
                                Parent registerView = loader.load();
                                mainView = (StackPane) mainView.lookup("#mainView");
                                mainView.getChildren().add(registerView);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
        } else {
            if (event.getSource() == btnAuction) {
                mainView.getChildren().clear();
            } else {
                if (event.getSource() == btnOrders) {
                    mainView.getChildren().clear();
                } else {
                    if (event.getSource() == btnSpareParts) {
                        mainView.getChildren().clear();
                    } else {
                        if (event.getSource() == btnReservations) {
                            mainView.getChildren().clear();
                            try {
                                FXMLLoader loader = new FXMLLoader(
                                        getClass().getResource("../View/manageReservations.fxml"));
                                Parent included = loader.load();
                                pnlManageReservations = (Pane) included.lookup("#pnlManageReservations");
                                mainView.getChildren().add(pnlManageReservations);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (event.getSource() == btnSettings) {
                                mainView.getChildren().clear();
                            } else {
                                if (event.getSource() == btnSignout) {
                                    mainView.getChildren().clear();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
