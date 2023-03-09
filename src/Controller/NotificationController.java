/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import Dao.NotificationDaoImplementation;
import Entities.Notification;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author asus
 */
public class NotificationController implements Initializable {

    @FXML
    private VBox notificationPanel;
    @FXML
    private Pane pnlNotifications;
    @FXML
    private Button readBtn;
    @FXML
    private Button refreshBtn;
    private int userId = 2;
    NotificationDaoImplementation notifDao = new NotificationDaoImplementation();
    int currentCount = notifDao.getAllNotifications(2).size();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshView();
    }

    @FXML
    private void updateNotificationStatus(ActionEvent event) {
        if (event.getSource() == refreshBtn) {
            notificationPanel.getChildren().clear();
            refreshView();

            // Animate the refresh button when pressed.
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(.5), refreshBtn);
            rotateTransition.setByAngle(-360);
            rotateTransition.play();
        } else {
            if (event.getSource() == readBtn) {
                notifDao.updateNotification(userId);
            }
        }
    }

    @FXML
    private void refreshView() {
        ObservableList<Notification> observableNotificationList = returnLatestTable();

        if (observableNotificationList.isEmpty()) {
            notificationPanel.getChildren().clear();
            pnlNotifications.lookup("#noNotificationYet").setVisible(true);
        } else {
            pnlNotifications.lookup("#noNotificationYet").setVisible(false);
            for (Notification notif : observableNotificationList) {
                HBox notification = generateNotificationRow(notif.getContent());
                notificationPanel.getChildren().add(notification);
            }
        }
    }

    public boolean tableChanged(int oldCount) {
        int newCount = notifDao.getNumberOfNotifications();

        if (newCount != oldCount) {
            oldCount = newCount;
            return true;
        } else {
            return false;
        }
    }

    public ObservableList<Notification> returnLatestTable() {
        return FXCollections.observableList(notifDao.getAllNotifications(userId));
    }

    @FXML
    public HBox generateNotificationRow(String notif) {
        HBox notificationRow = new HBox();
        notificationRow.setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

        notificationRow.setPrefSize(580, 60);
        notificationRow.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        notificationRow.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        notificationRow.setAlignment(Pos.CENTER);

        Label notification = new Label();
        notification.setPrefSize(580, 20);
        notification.setId("notification");
        notification.setText(notif);
        notification.setWrapText(true);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(2);
        dropShadow.setWidth(5);
        dropShadow.setHeight(5);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        notificationRow.setEffect(dropShadow);

        notificationRow.setPadding(new Insets(20, 20, 20, 20));
        notificationRow.getChildren().addAll(notification);
        return notificationRow;
    }
}
