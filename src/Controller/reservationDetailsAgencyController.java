package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author neil
 */
public class reservationDetailsAgencyController implements Initializable {

    // *********************************************
    // Attributes
    // *********************************************

    @FXML
    private Button closeBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Pane pnlReservationDetails;
    @FXML
    private Label userLabel;
    @FXML
    private Label userPhoneLabel;
    @FXML
    private Label carLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Pane mapContainer;

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

    }
}
