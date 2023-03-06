package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import Dao.ReservationDao;
import Dao.UserDao;
import Entities.Reservation;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.scene.effect.BlurType;

/**
 *
 * @author neil
 */

public class manageReservationsController implements Initializable {

    ReservationDao r = new ReservationDao();
    UserDao u = new UserDao();

    @FXML
    private VBox reservationsPanel;
    @FXML
    private Label userColumn;
    @FXML
    private Label carColumn;
    @FXML
    private Label dateColumn;
    @FXML
    private Label locationColumn;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button filterBtn;
    @FXML
    private Label totalNumberOfReservations;
    @FXML
    private Button refreshBtn;
    @FXML
    private HBox reservation;

    // public FXML variables
    @FXML
    public Pane pnlManageReservations;
    @FXML
    public Pane pnlReservationDetails;

    int currentCount = r.getNumberOfReservations();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshView();
    }

    // This method handles button clicks
    @FXML
    public void handleClicks(ActionEvent event) {
        if (event.getSource() == refreshBtn) {

            if (tableChanged(currentCount)) {
                reservationsPanel.getChildren().clear();
                refreshView();
            }

            // Animate the refresh button when pressed.
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(.5), refreshBtn);
            rotateTransition.setByAngle(-360);
            rotateTransition.play();
        } else {
            if (event.getSource() == filterBtn) {
                System.out.println("Filter!");
            } else {
                if (((Node) event.getSource()).getId() == detailsBtn.getId()) {
                    HBox hbox = (HBox) ((Node) event.getSource()).getParent();
                    userColumn = (Label) hbox.getChildren().get(0);
                    carColumn = (Label) hbox.getChildren().get(1);
                    detailsPopup(hbox, Integer.parseInt(userColumn.getText()), Integer.parseInt(carColumn.getText()));
                } else {
                    if (((Node) event.getSource()).getId() == cancelBtn.getId()) {
                        HBox hbox = (HBox) ((Node) event.getSource()).getParent();
                        userColumn = (Label) hbox.getChildren().get(0);
                        carColumn = (Label) hbox.getChildren().get(1);
                        deleteReservationAlert(hbox, Integer.parseInt(userColumn.getText()),
                                Integer.parseInt(carColumn.getText()));
                    }
                }
            }
        }
    }

    // This method refreshes the view when there's something that changed
    @FXML
    private void refreshView() {
        ObservableList<Reservation> observableReservationList = returnLatestTable();

        if (isTableEmpty(observableReservationList)) {
            reservationsPanel.getChildren().clear();
            pnlManageReservations.lookup("#noReservationYet").setVisible(true);
        } else {
            pnlManageReservations.lookup("#noReservationYet").setVisible(false);
            for (Reservation reserv : observableReservationList) {
                HBox reservation = generateReservationRow(reserv.getUser(), reserv.getCar(), reserv.getDate(),
                        reserv.getLocation());
                reservationsPanel.getChildren().add(reservation);
            }
        }
        updateReservationCounter();
    }

    // This method checks if there was a change in the db table or not
    public boolean tableChanged(int oldCount) {
        int currentCount = r.getNumberOfReservations();

        if (currentCount != oldCount) {
            oldCount = currentCount;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTableEmpty(ObservableList<Reservation> ol) {
        if (ol.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public ObservableList<Reservation> returnLatestTable() {
        return FXCollections.observableList(r.getReservations());
    }

    // This method generates fxml code for reservation row
    @FXML
    public HBox generateReservationRow(int usr, int car, String date, String location) {
        HBox reservation = new HBox();
        reservation.setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

        // Getting absolute path of the css file
        String cssPath = "src/Styles/reservationStyle.css";
        File cssFile = new File(cssPath);
        String cssUrl;
        try {
            cssUrl = cssFile.toURI().toURL().toExternalForm();
            // Add the stylesheet to the reservation row
            reservation.getStylesheets().add(cssUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        reservation.setPrefSize(835, 53);
        reservation.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        reservation.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        reservation.setAlignment(Pos.CENTER);

        Label userColumn = new Label();
        userColumn.setPrefSize(99, 18);
        userColumn.setId("userColumn");
        Label carColumn = new Label();
        carColumn.setPrefSize(84, 18);
        carColumn.setId("carColumn");
        Label dateColumn = new Label();
        dateColumn.setPrefSize(125, 18);
        Label locationColumn = new Label();
        locationColumn.setPrefSize(308, 18);

        userColumn.setText("" + usr);
        carColumn.setText("" + car);
        dateColumn.setText("" + date);
        locationColumn.setText("" + location);

        Button details_btn = new Button("Details");
        details_btn.setId("detailsBtn");
        details_btn.setPrefSize(70, 22);
        details_btn.getStyleClass().add("details-btn");
        detailsBtn = details_btn;
        details_btn.setOnAction(this::handleClicks);

        Button cancel_btn = new Button("Cancel");
        cancel_btn.setId("cancelBtn");
        cancel_btn.setPrefSize(70, 22);
        cancel_btn.getStyleClass().add("cancel-btn");
        cancel_btn.setOnAction(this::handleClicks);
        cancelBtn = cancel_btn;
        HBox.setMargin(cancel_btn, new Insets(0, 0, 0, 10));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(2);
        dropShadow.setWidth(5);
        dropShadow.setHeight(5);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        reservation.setEffect(dropShadow);

        reservation.setPadding(new Insets(20, 20, 20, 20));
        reservation.getChildren().addAll(userColumn, carColumn, dateColumn, locationColumn, details_btn, cancelBtn);
        return reservation;
    }

    @FXML
    private void detailsPopup(HBox reservation, int Id_user, int id_car) {
        Rectangle overlay = new Rectangle(0, 0, Color.rgb(0, 0, 0, 0.15));
        overlay.widthProperty().bind(pnlManageReservations.widthProperty());
        overlay.heightProperty().bind(pnlManageReservations.heightProperty());

        Popup popup = new Popup();
        Pane popupContent = new Pane();

        Parent included;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../View/reservationDetails.fxml"));
            included = loader.load();
            pnlReservationDetails = (Pane) included.lookup("#pnlReservationDetails");
        } catch (IOException e) {
            e.printStackTrace();
        }
        popupContent.getChildren().addAll(pnlReservationDetails);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(popupContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(600);
        scrollPane.setFocusTraversable(false);
        scrollPane.getStyleClass().add("reservationDetails-ScrollPane");
        scrollPane.getStylesheets().add("./Styles/reservationStyle.css");

        Platform.runLater(() -> {
            // Set the vertical scroll position to the top
            scrollPane.setVvalue(0.0);
        });
    

        popup.getContent().add(scrollPane);
        popup.setAutoHide(true);
        popup.setOnHidden(event -> pnlManageReservations.getChildren().remove(overlay));

        Button closeBtn = (Button) popupContent.lookup("#closeBtn");
        closeBtn.setOnAction(event -> {
            popup.hide();
        });
        Button cancelButton = (Button) popupContent.lookup("#cancelBtn");
        cancelButton.setOnAction(event -> {
            deleteReservationRow(reservation, Id_user, id_car);
            popup.hide();
        });

        // Label agencyLabel = (Label) popupContent.lookup("#AgencyNameField");
        // agencyLabel.setText("" + u.getUserById(Id_user).getName());
        // Label userPhoneLabel = (Label) popupContent.lookup("#AgencyPhoneField");
        // userPhoneLabel.setText("" + u.getUserById(Id_user).getPhone_number());
        Label dateLabel = (Label) popupContent.lookup("#dateField");
        dateLabel.setText("" + r.getReservation(Id_user, id_car).getDate());
        Label locationLabel = (Label) popupContent.lookup("#locationField");
        locationLabel.setText("" + r.getReservation(Id_user, id_car).getLocation());

        pnlManageReservations.getChildren().add(overlay);

        Bounds rootBounds = pnlManageReservations.getBoundsInLocal();
        double popupX = rootBounds.getMinX() + (rootBounds.getWidth() - scrollPane.getWidth()) / 1.4;
        popup.show(detailsBtn.getScene().getWindow(), popupX , 125);
        popup.setAutoFix(true);
    }

    // This method promps the user if he's certain he wants to delete reservation
    @FXML
    private void deleteReservationAlert(HBox reservation, int id_user, int id_car) {
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
                deleteReservationRow(reservation, id_user, id_car);
            }
        });
    }

    public void deleteReservationRow(Node reservation, int id_user, int id_car){
        r.deleteReservation(id_user, id_car);

        Parent parent = reservation.getParent();
        if (parent instanceof Pane) {
            ((Pane) parent).getChildren().remove(reservation);
        }

        updateReservationCounter();
        if (isTableEmpty(returnLatestTable())) {
            pnlManageReservations.lookup("#noReservationYet").setVisible(true);
        }
    }

    // This method updates the UI reservation counter
    public void updateReservationCounter() {
        totalNumberOfReservations.setText("" + r.getNumberOfReservations());
    }

}
