package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Dao.AgentDao;
import Dao.ReservationDao;
import Entities.CurrentUser;
import Entities.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Controller for the create reservation pop up
 * 
 * @author neil
 */
public class createReservationController implements Initializable {

    // *********************************************
    // Attributes
    // *********************************************

    ReservationDao r = new ReservationDao();
    int currentUserID = CurrentUser.getUser().getId();
    AgentDao a = new AgentDao();
    private int id_car;

    @FXML
    private Pane createReservationPopUp;
    @FXML
    private Pane datePickerContainer;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Text invalidDateTXT;

    // *********************************************
    // Methods
    // *********************************************

    /**
     * JavaFx initialize method
     * 
     * @param location  used to resolve relative paths for the root object, or null
     *                  if the location is not known.
     * @param resources used to localize the root object, or null if the root object
     *                  was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invalidDateTXT.setVisible(false);
    }

    /**
     * This Method handles button clicks.
     * 
     * @param event event handler.
     */
    public void handleClicks(ActionEvent event) {
        if (event.getSource() == confirmBtn) {
            LocalDate reservationDate = datePicker.getValue();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = reservationDate.format(formatter);

            int id_agent = a.getAgentByCar(id_car);

            if (isDateValid(reservationDate)) {
                Reservation newReservation = new Reservation(currentUserID, id_car, date, a.getAgentLocation(id_agent),
                        id_agent);
                r.createReservation(newReservation);
            } else {
                invalidDateTXT.setVisible(true);
            }
        }
    }

    /**
     * This method validates if date is after current date or not.
     * 
     * @param date date to verify
     */
    public boolean isDateValid(LocalDate date) {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.compareTo(date) < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setter for the private variable id_car.
     * 
     * @param car id of car
     */
    public void setCarID(int car) {
        this.id_car = car;
    }

}
