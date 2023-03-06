package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import Dao.UserDao;
import Entities.User;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.effect.BlurType;

/**
 *
 * @author neil
 */
public class manageUsersController implements Initializable {

    UserDao r = new UserDao();

    @FXML
    private VBox usersPanel;
    @FXML
    private Label userColumn;
    @FXML
    private Label carColumn;
    @FXML
    private Label dateColumn;
    @FXML
    private Label locationColumn;
    @FXML
    private Label phoneColumn;
    @FXML
    private Label roleColumn;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button filterBtn;
    @FXML
    private Label totalNumberOfUsers;
    @FXML
    private Button refreshBtn;
    @FXML
    private HBox user;

    // public FXML variables
    @FXML
    public Pane pnlManageUsers;
    @FXML
    public Pane pnlUserDetails;

    int currentCount = r.getNumberOfUsers();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshView();
    }

    // This method handles button clicks
    @FXML
    public void handleClicks(ActionEvent event) {
        if (event.getSource() == refreshBtn) {

            if (tableChanged(currentCount)) {
                usersPanel.getChildren().clear();
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

                } else {
                    if (((Node) event.getSource()).getId() == cancelBtn.getId()) {
                        HBox hbox = (HBox) ((Node) event.getSource()).getParent();
                        userColumn = (Label) hbox.getChildren().get(0);
                        carColumn = (Label) hbox.getChildren().get(1);
                        deleteUserAlert(hbox, Integer.parseInt(userColumn.getText()), Integer.parseInt(carColumn.getText()));
                    }
                }
            }
        }
    }

    // This method refreshes the view when there's something that changed
    @FXML
    private void refreshView() {
        ObservableList<User> observableUserList = returnLatestTable();
        System.out.println(isTableEmpty(observableUserList));
        if (isTableEmpty(observableUserList)) {
            usersPanel.getChildren().clear();
            pnlManageUsers.lookup("#noUsersYet").setVisible(true);
        } else {
            pnlManageUsers.lookup("#noUsersYet").setVisible(false);
            for (User us : observableUserList) {
                HBox user = generateUserRow(us.getId(), us.getName(), us.getEmail(), us.getLocation(), us.getPhone_number(), r.getRole(us.getId()));
                usersPanel.getChildren().add(user);
            }
        }
        updateUserCounter();
    }

    // This method checks if there was a change in the db table or not
    public boolean tableChanged(int oldCount) {
        int currentCount = r.getNumberOfUsers();

        if (currentCount != oldCount) {
            oldCount = currentCount;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTableEmpty(ObservableList<User> ol) {
        if (ol.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public ObservableList<User> returnLatestTable() {
        return FXCollections.observableList(r.view_users());
    }

    // This method generates fxml code for user row
    @FXML
    public HBox generateUserRow(int usr, String car, String date, String location, int phone_number, String role) {
        HBox user = new HBox();
        user.setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

        // Getting absolute path of the css file
        String cssPath = "src/Styles/reservationStyle.css";
        File cssFile = new File(cssPath);
        String cssUrl;
        try {
            cssUrl = cssFile.toURI().toURL().toExternalForm();
            // Add the stylesheet to the user row
            user.getStylesheets().add(cssUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        user.setPrefSize(720, 53);
        user.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        user.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        user.setAlignment(Pos.CENTER_LEFT);

        Label userColumn = new Label();
        userColumn.setPrefSize(70, 18);
        userColumn.setId("userColumn");
        Label carColumn = new Label();
        carColumn.setPrefSize(70, 18);
        carColumn.setId("carColumn");
        Label dateColumn = new Label();
        dateColumn.setPrefSize(140, 18);
        Label locationColumn = new Label();
        locationColumn.setPrefSize(70, 18);
        Label phoneColumn = new Label();
        phoneColumn.setPrefSize(70, 18);
        Label roleColumn = new Label();
        roleColumn.setPrefSize(150, 18);

        userColumn.setText("" + usr);
        carColumn.setText("" + car);
        dateColumn.setText("" + date);
        locationColumn.setText("" + location);
        phoneColumn.setText("" + phone_number);
        roleColumn.setText("" + role);

        Button details_btn = new Button("Details");
        details_btn.setId("detailsBtn");
        details_btn.setPrefSize(70, 22);
        details_btn.getStyleClass().add("details-btn");
        detailsBtn = details_btn;
        details_btn.setOnAction(this::handleClicks);
        HBox.setMargin(details_btn, new Insets(0, 0, 0, 90));

        Button cancel_btn = new Button("Delete");
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
        user.setEffect(dropShadow);

        user.setPadding(new Insets(20, 20, 20, 20));
        user.getChildren().addAll(userColumn, carColumn, dateColumn, locationColumn, phoneColumn, details_btn, cancelBtn);
        return user;
    }

    @FXML
    public void generateDetailsView() {

    }

    // This method promps the user if he's certain he wants to delete user
    @FXML
    private void deleteUserAlert(HBox user, int id_user, int id_car) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this user? This will also delete all their records.");
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
                r.deleteUserRecords(id_user);

                Parent parent = user.getParent();
                if (parent instanceof Pane) {
                    ((Pane) parent).getChildren().remove(user);
                }

                updateUserCounter();
                if (isTableEmpty(returnLatestTable())) {
                    pnlManageUsers.lookup("#noUserYet").setVisible(true);
                }
            }
        });
    }

    // This method updates the UI user counter
    public void updateUserCounter() {
        totalNumberOfUsers.setText("" + r.getNumberOfUsers());
    }

}
