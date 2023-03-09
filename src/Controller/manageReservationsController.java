package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Api.MapsApi;
import Dao.CarDao;
import Dao.ReservationDao;
import Entities.*;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
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
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.scene.effect.BlurType;

/**
 * This Java controller class controls the manage reservations UI for the user.
 * 
 * @author neil
 */
public class manageReservationsController implements Initializable {

    // *********************************************
    // Attributes
    // *********************************************

    ReservationDao r = new ReservationDao();
    ReservationDetail rd = new ReservationDetail();
    int currentUserID = CurrentUser.getUser().getId();

    @FXML
    private VBox reservationsPanel;
    @FXML
    private Label carColumn;
    @FXML
    private Label carModelColumn;
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
    @FXML
    private WebView mapContainer;
    @FXML
    private Pane pnlManageReservations;
    @FXML
    private Pane pnlReservationDetails;

    int currentCount = r.filterReservationsByUser(currentUserID).size();

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
        refreshView();
    }

    /**
     * This Method handles button clicks.
     * 
     * @param event event handler.
     */
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
                    carColumn = (Label) hbox.getChildren().get(0);
                    detailsPopup(hbox, currentUserID, Integer.parseInt(carColumn.getText()));
                } else {
                    if (((Node) event.getSource()).getId() == cancelBtn.getId()) {
                        HBox hbox = (HBox) ((Node) event.getSource()).getParent();
                        carColumn = (Label) hbox.getChildren().get(0);
                        deleteReservationAlert(hbox, currentUserID, Integer.parseInt(carColumn.getText()));
                    }
                }
            }
        }
    }

    /**
     * This method refreshes the manage reservations view.
     * 
     * @param void
     */
    @FXML
    private void refreshView() {
        ObservableList<Reservation> observableReservationList = returnLatestTable();

        try {
            CarDao c = new CarDao();

            if (isTableEmpty(observableReservationList)) {
                reservationsPanel.getChildren().clear();
                pnlManageReservations.lookup("#noReservationYet").setVisible(true);
            } else {
                pnlManageReservations.lookup("#noReservationYet").setVisible(false);
                for (Reservation reserv : observableReservationList) {
                    HBox reservation = generateReservationRow(reserv.getCar(),
                            c.displayById(reserv.getCar()).getModel(), reserv.getDate(),
                            reserv.getLocation());
                    reservationsPanel.getChildren().add(reservation);
                }
            }
            updateReservationCounter();

        } catch (SQLException e) {
            System.out.println("****************");
            e.printStackTrace();
            System.out.println("****************");
        }
    }

    /**
     * This method checks if there was a change in the db table or not
     * 
     * @param oldCount previous List size.
     */
    public boolean tableChanged(int oldCount) {
        int currentCount = r.getNumberOfReservations();

        if (currentCount != oldCount) {
            oldCount = currentCount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if the Table is empty or not.
     * 
     * @param ol an ObservableList object that represents the table to be checked.
     */
    public boolean isTableEmpty(ObservableList<Reservation> ol) {
        if (ol.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method creturn the latest reservation table from the DataBase.
     * 
     * @param void
     */
    public ObservableList<Reservation> returnLatestTable() {
        return FXCollections.observableList(r.filterReservationsByUser(currentUserID));
    }

    /**
     * This method generates fxml code for reservation row
     * 
     * @param car       the car ID
     * @param car_model the car model
     * @param date      the date of the reservation
     * @param location  the location where the car is located.
     */
    @FXML
    public HBox generateReservationRow(int car, String car_model, String date, String location) {
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
            System.out.println("****************");
            e.printStackTrace();
            System.out.println("****************");
        }

        reservation.setPrefSize(835, 53);
        reservation.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        reservation.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        reservation.setAlignment(Pos.CENTER);

        Label carColumn = new Label();
        carColumn.setPrefSize(99, 18);
        carColumn.setId("carColumn");

        Label carModelColumn = new Label();
        carModelColumn.setPrefSize(134, 18);
        carModelColumn.setId("carModelColumn");

        Label dateColumn = new Label();
        dateColumn.setPrefSize(125, 18);

        Label locationColumn = new Label();
        locationColumn.setPrefSize(369, 18);

        carColumn.setText("" + car);
        carModelColumn.setText("" + car_model);
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
        reservation.getChildren().addAll(carColumn, carModelColumn, dateColumn, locationColumn, details_btn, cancelBtn);
        return reservation;
    }

    /**
     * This method generates fxml code for reservation details popup and displays
     * it.
     * 
     * @param reservation the HBox parent of the detailsBtn that is clicked.
     * @param Id_user     the user ID
     * @param Id_car      the car ID
     */
    @FXML
    private void detailsPopup(HBox reservation, int Id_user, int Id_car) {
        Rectangle overlay = new Rectangle(0, 0, Color.rgb(0, 0, 0, 0.15));
        overlay.widthProperty().bind(pnlManageReservations.widthProperty());
        overlay.heightProperty().bind(pnlManageReservations.heightProperty()); // Overlay to put the popup in focus

        Popup popup = new Popup();
        Pane popupContent = new Pane();

        Parent included;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../View/reservationDetails.fxml")); // Loading FXML
            included = loader.load();
            pnlReservationDetails = (Pane) included.lookup("#pnlReservationDetails");
            mapContainer = (WebView) included.lookup("#mapContainer");
        } catch (IOException e) {
            System.out.println("****************");
            e.printStackTrace();
            System.out.println("****************");
        }
        popupContent.getChildren().addAll(pnlReservationDetails);

        /**
         * Creating Scroll Pane to house the popup content, it's scrollable.
         */
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(popupContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(600);
        scrollPane.setFocusTraversable(false);
        scrollPane.getStyleClass().add("reservationDetails-ScrollPane");
        scrollPane.getStylesheets().add("./Styles/reservationStyle.css");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide vertical scroll bar

        Platform.runLater(() -> {
            // Set the vertical scroll position to the top
            scrollPane.setVvalue(0.0);
        });

        popup.getContent().add(scrollPane); // Adding content to the popup.
        popup.setAutoHide(true); // when clicking away from popup it closes.
        popup.setOnHidden(event -> pnlManageReservations.getChildren().remove(overlay)); // Removing overlay.

        /**
         * Popup button handling.
         * Note: Reason why it's not in handleClicks() because this is easier to get the
         * selected reservation row.
         */
        Button closeBtn = (Button) popupContent.lookup("#closeBtn");
        closeBtn.setOnAction(event -> {
            popup.hide();
        });
        Button cancelButton = (Button) popupContent.lookup("#cancelBtn");
        cancelButton.setOnAction(event -> {
            deleteReservationRow(reservation, Id_user, Id_car);
            popup.hide();
        });

        /* Getting reservation details for specific car */
        rd = r.reservationDetails(Id_user, Id_car);

        /* Generate interactive map */
        MapsApi.generateMap(mapContainer, rd.getLocation());

        /**
         * Populating the details popup labels.
         */
        Label agencyLabel = (Label) popupContent.lookup("#agencyNameField");
        agencyLabel.setText("" + rd.getName());

        Label agencyPhoneLabel = (Label) popupContent.lookup("#agencyPhoneField");
        agencyPhoneLabel.setText("" + rd.getPhoneNumber());

        Label agencyEmailLabel = (Label) popupContent.lookup("#agencyEmailField");
        agencyEmailLabel.setText("" + rd.getEmail());

        Label carMakeLabel = (Label) popupContent.lookup("#carBrandField");
        carMakeLabel.setText("" + rd.getMake());

        Label carModelLabel = (Label) popupContent.lookup("#carModelField");
        carModelLabel.setText("" + rd.getModel());

        Label dateLabel = (Label) popupContent.lookup("#dateField");
        dateLabel.setText("" + rd.getDate());

        Label locationLabel = (Label) popupContent.lookup("#locationField");
        locationLabel.setText("" + rd.getLocation());

        pnlManageReservations.getChildren().add(overlay); // Adding overlay to View.

        Bounds rootBounds = pnlManageReservations.getBoundsInLocal();
        double popupX = rootBounds.getMinX() + (rootBounds.getWidth() - scrollPane.getWidth()) / 1.4;
        popup.show(detailsBtn.getScene().getWindow(), popupX, 125);
    }

    /**
     * This method promps the user if he's certain he wants to delete reservation.
     * 
     * @param reservation the HBox parent of the cancelBtn that is clicked.
     * @param id_user     the user ID
     * @param id_car      the car ID
     */
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
            System.out.println("****************");
            e.printStackTrace();
            System.out.println("****************");
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

    /**
     * This method deletes a reservation from the table and removes a row from the
     * UI table.
     * 
     * @param reservation the HBox row to be removed from FXML code
     * @param id_user     the user ID
     * @param id_car      the car ID
     */
    public void deleteReservationRow(Node reservation, int id_user, int id_car) {
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

    /**
     * This method updates the UI reservation counter
     * 
     * @param void
     */
    public void updateReservationCounter() {
        totalNumberOfReservations.setText("" + returnLatestTable().size());
    }
}
