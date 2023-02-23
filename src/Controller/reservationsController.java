package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.ReservationDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class reservationsController implements Initializable {

    ReservationDao r = new ReservationDao();

    @FXML private Label userColumn;
    @FXML private Label carColumn;
    @FXML private Label dateColumn;
    @FXML private Label locationColumn;
    @FXML private VBox reservationsPanel;
    @FXML private HBox reservation;
    @FXML private Button detailsBtn;
    @FXML private Button cancelBtn;
    @FXML private Button filterBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    public void handleClicks(ActionEvent event) {
        if (event.getSource() == detailsBtn) {
            System.out.println("Details!");
        }
        if (event.getSource() == cancelBtn) {
            // System.out.println("Cancel!");
            int id_user = Integer.parseInt(userColumn.getText());
            int id_car = Integer.parseInt(carColumn.getText());
            r.deleteReservation(id_user, id_car);

            refreshView();
        }
        if (event.getSource() == filterBtn) {
            System.out.println("Filter!");
        }
    }

    private void refreshView() {
        Parent parent = reservation.getParent();
        if (parent instanceof Pane) {
            ((Pane) parent).getChildren().remove(reservation);
        }
    }

}
