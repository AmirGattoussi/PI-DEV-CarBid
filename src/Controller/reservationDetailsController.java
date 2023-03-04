package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JComponent;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.net.HttpURLConnection;

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
    private VBox mapContainer;

    private double latitude;
    private double longitude;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // VBox mapContainer = new VBox();

        // JXMapViewer mapViewer = new JXMapViewer();
        // mapViewer.setZoom(10);
        // mapViewer.setAddressLocation(new GeoPosition(latitude, longitude));
        // DefaultTileFactory tileFactory = new DefaultTileFactory(new OSMTileFactoryInfo());
        // mapViewer.setTileFactory(tileFactory);

        // JComponent mapViewComponent = mapViewer.getView(); // Get the JComponent representing the map view
        // Node mapViewNode = new SwingNode(); // Create a SwingNode to wrap the JComponent
        // ((SwingNode) mapViewNode).setContent(mapViewComponent);

        // mapContainer.getChildren().add(mapViewNode);

        // ********************************

        // String apiKey = "UrH7rF0Ljp3pAfsxkpI-uq_gWQ8kDb_Ddo2TxpCNTYo";
        // String center = "-122.3321,47.6062";
        // String baseUrl = "https://atlas.microsoft.com/map/static/png";
        // int zoom = 12;
        // String size = "368,407";

        // // Build the URL for the Azure Maps static map API
        // String apiUrl = baseUrl + "?api-version=1.0&subscription-key=" + apiKey +
        // "&center=" + center + "&zoom=" + zoom + "&size=" + size;

        // try {
        // URL url = new URL(apiUrl);
        // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // conn.setRequestMethod("GET");
        // Image image = new Image(conn.getInputStream());
        // ImageView imageView = new ImageView(image);

        // mapContainer.getChildren().add(imageView);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // ReservationDao r = new ReservationDao();
        // UserDao u = new UserDao();

        // int user_id = r.getReservation(0, 0).getUser(); // specify user and car IDs

        // Label userLabel = (Label) detailsPane.lookup("#userLabel");
        // userLabel.setText("" + u.getUserById(user_id).getName());
        // Label userPhoneLabel = (Label) detailsPane.lookup("#userPhoneLabel");
        // userPhoneLabel.setText("" + u.getUserById(user_id).getPhone_number());

    }

    public void handleClicks(ActionEvent event) {
        if (event.getSource() == closeBtn) {
            System.out.println("Close!");
        }
        if (event.getSource() == cancelBtn) {
            System.out.println("Cancel!");
        }
    }
}
