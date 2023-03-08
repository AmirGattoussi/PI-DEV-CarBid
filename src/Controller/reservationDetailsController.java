package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Api.MapsApi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

/**
 *
 * @author neil
 */
public class reservationDetailsController implements Initializable {

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
    private WebView mapContainer;
    @FXML
    private Text loadingText;

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

        /* Generate interactive map */
        MapsApi.generateMap(mapContainer);
    }
}
