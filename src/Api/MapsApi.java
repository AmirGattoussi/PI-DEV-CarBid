package Api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

/**
 *
 * @author neil
 */
public class MapsApi {

    /**
     * This method calls the Google Maps Api and generates a map.
     * 
     * @param mapContainer container that will contain the map returned by the API.
     * @param address the address of the location to be highlighted when map loads.
     */
    @FXML
    public static void generateMap(WebView mapContainer, String address) {

        /* Loading API key from config.properties for security reasons */
        Properties prop = new Properties();
        String apiKey = "";
        try {
            prop.load(new FileInputStream("config.properties"));
            apiKey = prop.getProperty("google.maps.key");

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* The html to be displayed in the webview */
        String googleMapsHTML = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Document</title>" +
                "</head>" +
                "<body>" +
                "<iframe width=\"415\" height=\"415\" style=\"border:0\" loading=\"lazy\" allowfullscreen src=\"https://www.google.com/maps/embed/v1/place?q="
                + address + "&key=" + apiKey + "\"></iframe>" +
                "</body>" +
                "</html>";

        mapContainer.getEngine().loadContent(googleMapsHTML);
        mapContainer.setPrefSize(435, 435);

        mapContainer.getEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                // The WebView is loaded completely
                mapContainer.setVisible(true);
            }
        });
    }
}
