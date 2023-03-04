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
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
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
    private Pane mapContainer;

    private double latitude;
    private double longitude;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String apiKey = "UrH7rF0Ljp3pAfsxkpI-uq_gWQ8kDb_Ddo2TxpCNTYo";
        // center= (lon, lat)
        String center = "10.1874636,36.899229";
        String baseUrl = "https://atlas.microsoft.com/map/static/png";
        int zoom = 16;
        String marker = "pin-s-l+000FF(" + center + ")";

        // Build the URL for the Azure Maps static map API
        String apiUrl = baseUrl + "?api-version=1.0&subscription-key=" + apiKey +
        "&center=" + center + "&zoom=" + zoom + "&iconset=pin&iconcolor=000FF&style=main" + "&pushpin=" + marker;

        try {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        Image image = new Image(conn.getInputStream());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(435);
        imageView.setFitHeight(435);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setWidth(5);
        dropShadow.setHeight(5);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        mapContainer.setEffect(dropShadow);

        mapContainer.getChildren().add(imageView);
        } catch (Exception e) {
        e.printStackTrace();
        }

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
