package Api;

import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

/**
 *
 * @author neil
 */
public class MapsApi {

    // *********************************************
    // Methods
    // *********************************************

    /**
     * This method calls the Google Maps Api and generates a map.
     * 
     * @param mapContainer container that will contain the map returned by the API.
     */
    @FXML
    public static void generateMap(WebView mapContainer) {

        String apiKey = "AIzaSyCtAGE5bb0_x_LJAfK_yq-8quauxzIGGHM";
        String address = "lot 13, V5XR+M37 إقامة السلام 2، Av. Fethi Zouhir, Cebalat Ben Ammar 2083";

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
