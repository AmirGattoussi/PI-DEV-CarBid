package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.ldap.ManageReferralControl;

import com.mysql.cj.admin.ServerController;

import Dao.ReservationDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class reservationsController implements Initializable {

    ReservationDao r = new ReservationDao();

    @FXML
    private Label userColumn;
    @FXML
    private Label carColumn;
    @FXML
    private Label dateColumn;
    @FXML
    private Label locationColumn;
    @FXML
    private VBox reservationsPanel;
    @FXML
    private HBox reservation;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button filterBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    public void handleClicks(ActionEvent event) {
        if (event.getSource() == detailsBtn) {
            System.out.println("Details!");
        }
        if (event.getSource() == cancelBtn) {
            // System.out.println("Cancel!");
            showAlert();
        }
        if (event.getSource() == filterBtn) {
            System.out.println("Filter!");
        }
    }

    @FXML
    private void refreshView() {
        Parent parent = reservation.getParent();
        if (parent instanceof Pane) {
            ((Pane) parent).getChildren().remove(reservation);
        }

        // try {
        //     FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/manageReservations.fxml"));
        //     Parent root = loader.load();
        //     manageReservationsController controller = loader.getController();
        //     controller.updateReservationCounter();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

    }

    @FXML
    private void showAlert() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel reservation?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Getting absolute path of the css file
        String cssPath = "src/Styles/reservationStyle.css";
        File cssFile = new File(cssPath);
        String cssUrl;
        try {
            cssUrl = cssFile.toURI().toURL().toExternalForm();
            // Add the stylesheet to the alert dialog
            alert.getDialogPane().getScene().getStylesheets().add(cssUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Set the style class for the alert dialog
        alert.getDialogPane().getStyleClass().add("alert");

        // Add the custom buttons to the alert dialog
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        ButtonType buttonTypeDelete = new ButtonType("Yes", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeDelete);

        // Get the button nodes and set their style classes
        Button cancelButton = (Button) alert.getDialogPane().lookupButton(buttonTypeCancel);
        cancelButton.getStyleClass().add("alert-no-button");

        Button deleteButton = (Button) alert.getDialogPane().lookupButton(buttonTypeDelete);
        deleteButton.getStyleClass().add("alert-yes-button");

        // Show the alert dialog and wait for the user to respond
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeDelete) {
                int id_user = Integer.parseInt(userColumn.getText());
                int id_car = Integer.parseInt(carColumn.getText());
                r.deleteReservation(id_user, id_car);

                refreshView();
            }
        });
    }
}
