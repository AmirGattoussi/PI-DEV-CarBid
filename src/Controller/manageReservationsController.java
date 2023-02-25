package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Dao.ReservationDao;
import Entities.Reservation;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author neil
 */

public class manageReservationsController implements Initializable {

    ReservationDao r = new ReservationDao();

    @FXML
    private VBox reservationsPanel;
    @FXML
    private Label userColumn;
    @FXML
    private Label carColumn;
    @FXML
    private Label dateColumn;
    @FXML
    private Label locationColumn;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button filterBtn;
    @FXML
    private Label totalNumberOfReservations;
    @FXML
    private Button refreshBtn;
    @FXML
    private Pane pnlManageReservations;
    @FXML
    private Pane pnlReservationDetails;

    int currentCount = r.getNumberOfReservations();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshView();
    }
    @FXML
    public void handleClicks(ActionEvent event) {
        if (event.getSource() == filterBtn) {
            System.out.println("Filter!");
        }
        if (event.getSource() == refreshBtn) {

            if (tableChanged(currentCount)) {
                reservationsPanel.getChildren().clear();
                refreshView();
            }

            // Animate the refresh button when pressed.
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(.5), refreshBtn);
            rotateTransition.setByAngle(-360);
            rotateTransition.play();
            // System.out.println("Refresh!");
        }
    }

    @FXML
    private void refreshView() {
        ObservableList<Reservation> observableReservationList = FXCollections.observableList(r.getReservations());

        if (observableReservationList.isEmpty()) {
            reservationsPanel.getChildren().clear();
        } else {
            try {
                for (Reservation reservation : observableReservationList) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/reservations.fxml"));
                    HBox hbox = fxmlLoader.load();

                    Label userColumn = (Label) hbox.lookup("#userColumn");
                    userColumn.setText("" + reservation.getUser());
                    Label carColumn = (Label) hbox.lookup("#carColumn");
                    carColumn.setText("" + reservation.getCar());
                    Label dateColumn = (Label) hbox.lookup("#dateColumn");
                    dateColumn.setText("" + reservation.getDate());
                    Label locationColumn = (Label) hbox.lookup("#locationColumn");
                    locationColumn.setText("" + reservation.getLocation());

                    reservationsPanel.getChildren().add(hbox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // updateReservationCounter();
    }

    public boolean tableChanged(int oldCount) {
        int currentCount = r.getNumberOfReservations();

        if (currentCount != oldCount) {
            oldCount = currentCount;
            return true;
        } else {
            return false;
        }
    }

    // public void updateReservationCounter() {
    //     // totalNumberOfReservations.requestLayout();
    //     totalNumberOfReservations.setText("" + r.getNumberOfReservations());
    // }

}
