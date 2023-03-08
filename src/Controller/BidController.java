/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author asus
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Api.MailApi;
import Dao.AuctionDaoImplementation;
import Dao.BidDaoImplementation;
import Dao.CarDao;
import Dao.NotificationDaoImplementation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Entities.Bid;
import Entities.Car;
import Entities.Notification;
import Entities.User;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.time.LocalDate;
import java.time.ZoneOffset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import javafx.util.Duration;

public class BidController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text txt_car;
    @FXML
    private Button btn_confirm;
    @FXML
    private CheckBox btn_terms;
    @FXML
    private Button btn;
    @FXML
    private TextField txt_live_bid;
    @FXML
    private TextField txt_max_bid;
    @FXML
    private RadioButton radio_btn_live;
    @FXML
    private RadioButton radio_btn_max;
    @FXML
    private Text txt_highest_bid;
    @FXML
    private ListView list_v;
    @FXML
    private TableView table_view_history;
    @FXML
    private TableColumn<User, String> user_column;
    @FXML
    private TableColumn<Bid, Date> date_column;
    @FXML
    private TableColumn<Bid, Float> live_column;
    @FXML
    private Text txt_time;
    @FXML
    private Text bids_number;
    private BidController controller;
    private int userId;
    private int carId;
    private int auctionId;

    public BidController(int userId, int carId, int auctionId) {
        this.userId = userId;
        this.carId = carId;
        this.auctionId = auctionId;
    }

    private void startTimer() throws SQLException {

        AuctionDaoImplementation aucDao = new AuctionDaoImplementation();
        Date deadline = aucDao.getDeadline(auctionId);

        Instant instant = deadline.toLocalDate().atStartOfDay().toInstant(ZoneOffset.UTC);
        ;
        ZoneId zone = ZoneId.systemDefault();
        LocalDate deadli = instant.atZone(zone).toLocalDate();
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            if (deadline != null) {
                Instant now = Instant.now();
                LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                Instant deadlineInstant = deadli.atStartOfDay(ZoneId.systemDefault()).toInstant();
                Duration duration = java.time.Duration.between(now, deadlineInstant);
                if (duration.isNegative()) {
                    txt_time.setText("Deadline has passed");
                } else {
                    long hours = duration.toHours();
                    long minutes = duration.toMinutes() % 60;
                    long seconds = duration.getSeconds() % 60;
                    String remainingTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    txt_time.setText(remainingTime);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            CarDao carDao=new CarDao();
//            Car car = carDao.displayById(carId);
//            txt_car.setText(car.);
//        } catch (SQLException ex) {
//            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {

            AuctionDaoImplementation aucDao = new AuctionDaoImplementation();
            Float highest = aucDao.getHighestBidById(auctionId);
            txt_highest_bid.setText(Float.toString(highest));
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        try {

            BidDaoImplementation bidDao = new BidDaoImplementation();
            int count = bidDao.getNumberBids(carId);
            bids_number.setText(String.valueOf(count));
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        try {

            BidDaoImplementation bidDao = new BidDaoImplementation();
            List<Bid> data = bidDao.getBidsById(auctionId);

            ObservableList<Bid> observableBidList = FXCollections.observableList(data);
            user_column.setCellValueFactory(new PropertyValueFactory<>("name"));
            date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
            live_column.setCellValueFactory(new PropertyValueFactory<>("liveBidAmount"));
            table_view_history.setItems(observableBidList);
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            startTimer();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }

    }

    /*
     * private void refreshScene(ActionEvent event) {
     * 
     * try {
     * FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Bid.fxml"));
     * Parent root = (Parent) loader.load();
     * this.controller = loader.getController();
     * Scene scene = new Scene(root);
     * 
     * Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     * stage.setScene(scene);
     * stage.show();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     **/
    @FXML
    void addBid(ActionEvent event) throws SQLException, IOException {
        AuctionDaoImplementation aucDao=new AuctionDaoImplementation();
        System.out.println(aucDao.getAuction(auctionId).getStatus());
        if ("open".equals(aucDao.getAuction(auctionId).getStatus())){
        if (radio_btn_live.isSelected()) {
            try {
                // Bid bid = new Bid();
                // bid.setLiveBidAmount(Float.parseFloat(txt_live_bid.getText()));

                if ((txt_live_bid.getText().isEmpty()) && (!(txt_max_bid.getText().isEmpty()))
                        || (txt_live_bid.getText().isEmpty())
                        || (txt_live_bid.getText().isEmpty()) && (txt_max_bid.getText().isEmpty())) {

                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter only a live bid amount");
                    alert.showAndWait();
                } else if (!(txt_live_bid.getText().matches("[0-9]*")) && (!(txt_live_bid.getText().isEmpty()))) {

                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter only a number");
                    alert.showAndWait();

                } else if (!(btn_terms.isSelected()) == true) {

                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please agree to the terms and conditions");
                    alert.showAndWait();

                } else if (Float.parseFloat(txt_live_bid.getText()) <= Float.parseFloat(txt_highest_bid.getText())) {

                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a live bid amount bigger than the current bid");
                    alert.showAndWait();

                } else {
                    BidDaoImplementation bidDao = new BidDaoImplementation();
                    Bid max_bid = bidDao.getMaxBidById(auctionId);
                    if (max_bid.getMaxBidAmount() - Float.parseFloat(txt_live_bid.getText()) > 0 && max_bid!=null) {
                        aucDao.IncrementBid(auctionId, max_bid.getUserId(),
                                Float.parseFloat(txt_live_bid.getText()) + 500);

                    } else {
                        NotificationDaoImplementation notificationDao = new NotificationDaoImplementation();
                        int id = notificationDao.sendNotification(new Notification(max_bid.getUserId(), "Sorry, you have been outbid on the auction " + auctionId + " Another bidder has a maximum limit higher than yours"));
                    }
                    BidDaoImplementation bid_dao = new BidDaoImplementation();
                    bid_dao.addLiveBid(new Bid(2, 2, Float.parseFloat(txt_live_bid.getText())));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Bid");
                    alert.setHeaderText(null);
                    alert.setContentText("The bid is added successfully.");
                    alert.showAndWait();
                    // refreshScene(event);
                    refreshTextFields();
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            BidDaoImplementation bidDao = new BidDaoImplementation();
            Bid max_bid = bidDao.getMaxBidById(auctionId);

            if ((txt_live_bid.getText().isEmpty()) && (!(txt_max_bid.getText().isEmpty()))
                    || (txt_max_bid.getText().isEmpty()) && (!(txt_live_bid.getText().isEmpty()))) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter both a live bid amount and a max bid amout");
                alert.showAndWait();
            } else if (Float.parseFloat(txt_max_bid.getText()) < max_bid.getMaxBidAmount()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a bigger max bid amount");
                alert.showAndWait();
            } else if (!(txt_max_bid.getText().matches("[0-9]*")) && (!(txt_max_bid.getText().isEmpty()))) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter only a number");
                alert.showAndWait();

            } else if (Float.parseFloat(txt_live_bid.getText()) >= Float.parseFloat(txt_max_bid.getText())) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a higher max bid amount");
                alert.showAndWait();

            } else if (Float.parseFloat(txt_live_bid.getText()) <= Float.parseFloat(txt_highest_bid.getText())) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a live bid amount bigger than the current bid");
                alert.showAndWait();

            } else if (!(btn_terms.isSelected())) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please agree to the terms and conditions");
                alert.showAndWait();

            } else {
                if (max_bid.getUserId() == userId) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("You already have a max bid");
                    alert.showAndWait();
                } else if (Float.parseFloat(txt_max_bid.getText()) > max_bid.getMaxBidAmount()) {
                    NotificationDaoImplementation notificationDao = new NotificationDaoImplementation();
                    int id = notificationDao.sendNotification(new Notification(max_bid.getUserId(), "Sorry, you have been outbid on the auction " + auctionId + " Another bidder has a maximum limit higher than yours"));
                    aucDao.IncrementBidMax(auctionId, max_bid.getUserId(),
                            Float.parseFloat(txt_live_bid.getText()), Float.parseFloat(txt_max_bid.getText()));
                    BidDaoImplementation bid_dao = new BidDaoImplementation();
                    bid_dao.addMaxBid(new Bid(2, 2, Float.parseFloat(txt_live_bid.getText()),
                            Float.parseFloat(txt_max_bid.getText())));
                    // refreshScene(event);
                    refreshTextFields();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Bid");
                    alert.setHeaderText(null);
                    alert.setContentText("The bid is added successfully.");
                    alert.showAndWait();

                }

            }
        }
    }
        else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Auction status");
                alert.setHeaderText(null);
                alert.setContentText("Sorry the deadline ended,you can not bid anymore");
                alert.showAndWait();
                }

    }

    private void refreshTextFields() {
        try {

            AuctionDaoImplementation aucDao = new AuctionDaoImplementation();
            Float highest = aucDao.getHighestBidById(auctionId);
            txt_highest_bid.setText(Float.toString(highest));

            BidDaoImplementation bidDao = new BidDaoImplementation();
            List<Bid> data = bidDao.getBidsById(auctionId);
            ObservableList<Bid> observableBidList = FXCollections.observableList(data);
            user_column.setCellValueFactory(new PropertyValueFactory<>("name"));
            date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
            live_column.setCellValueFactory(new PropertyValueFactory<>("liveBidAmount"));
            table_view_history.setItems(observableBidList);

            table_view_history.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        try {

            BidDaoImplementation bidDao = new BidDaoImplementation();
            int count = bidDao.getNumberBids(carId);
            bids_number.setText(String.valueOf(count));
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    public int getUserId() {
        return userId;
    }

}
