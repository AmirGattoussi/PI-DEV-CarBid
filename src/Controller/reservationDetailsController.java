package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import Api.MapsApi;

/**
 *
 * @author neil
 */

public class reservationDetailsController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapsApi.generateMap(mapContainer);
    }
}
