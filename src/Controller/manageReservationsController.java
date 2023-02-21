package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import Dao.ReservationDao;
import Entities.Reservation;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author neil
 */

public class manageReservationsController implements Initializable {

    @FXML
    private TableView<Reservation> tableView;

    @FXML
    private TableColumn<Reservation, Integer> userColumn;

    @FXML
    private TableColumn<Reservation, Integer> carColumn;

    @FXML
    private TableColumn<Reservation, Date> dateColumn;

    @FXML
    private TableColumn<Reservation, String> locationColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'manageReservations.fxml'.";

        ReservationDao r = new ReservationDao();
        List<Reservation> data = r.getReservations();
        ObservableList<Reservation> observableReservationList = FXCollections.observableList(data);

        userColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("user"));
        carColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("car"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("location"));

        tableView.setItems(observableReservationList);
    }

}
