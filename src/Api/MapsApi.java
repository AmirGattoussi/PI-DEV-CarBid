package Api;

import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MapsApi {

    // private double latitude;
    // private double longitude;

    public static void generateMap(Pane mapContainer) {

        String apiKey = "UrH7rF0Ljp3pAfsxkpI-uq_gWQ8kDb_Ddo2TxpCNTYo";
        // center = (lon, lat)
        String center = "10.1865735,36.8163404";
        String baseUrl = "https://atlas.microsoft.com/map/static/png";
        int zoom = 16;
        String marker = "pin-s-l+000FF(" + center + ")";

        // Build the URL for the Azure Maps static map API
        String apiUrl = baseUrl + "?api-version=1.0&subscription-key=" + apiKey +
                "&center=" + center + "&zoom=" + zoom + "&iconset=pin&iconcolor=000FF&style=main" + "&pushpin="
                + marker;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            Image image = new Image(conn.getInputStream());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(435);
            imageView.setFitHeight(435);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10);
            dropShadow.setWidth(10);
            dropShadow.setHeight(10);
            dropShadow.setBlurType(BlurType.GAUSSIAN);
            mapContainer.setEffect(dropShadow);

            mapContainer.getChildren().add(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
