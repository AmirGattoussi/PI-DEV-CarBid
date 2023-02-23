package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Dao.ReservationDao;
import Entities.Reservation;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author neil
 */

public class manageReservationsController implements Initializable {

    @FXML private VBox reservationsPanel;
    @FXML private Label  userColumn;
    @FXML private Label  carColumn;
    @FXML private Label dateColumn;
    @FXML private Label  locationColumn;
    @FXML private Button detailsBtn;
    @FXML private Button cancelBtn;
    @FXML private Button filterBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ReservationDao r = new ReservationDao();
        List<Reservation> data = r.getReservations();
        ObservableList<Reservation> observableReservationList = FXCollections.observableList(data);

        try {
        for (Reservation reservation : observableReservationList) {
        FXMLLoader fxmlLoader = new
        FXMLLoader(getClass().getResource("../View/reservations.fxml"));
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

    public void handleClicks(ActionEvent event) {
        if (event.getSource() == filterBtn) {
            System.out.println("Filter!");
        }
    }

}
