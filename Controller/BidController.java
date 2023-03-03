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
import Dao.BidDaoImplementation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Entities.Bid;
import java.sql.Date;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class BidController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn;
    @FXML
    private TextField txt_live_bid;
    @FXML
    private TextField txt_max_bid;
    @FXML
    private Button btn_confirm;
    @FXML
    private CheckBox btn_terms;
    @FXML
    private RadioButton radio_btn_live;
    @FXML
    private RadioButton radio_btn_max;
    //@FXML 
    //private TextField txt_highest_bid;
    @FXML
    private TableView<Bid> tableViewBids;
    @FXML
    private TableColumn<Bid, Integer> bidColumn;

    @FXML
    private TableColumn<Bid, Integer> userColumn;

    @FXML
    private TableColumn<Bid, Integer> auctionColumn;

    @FXML
    private TableColumn<Bid, Date> dateColumn;

    @FXML
    private TableColumn<Bid, String> typeColumn;
    @FXML
    private TableColumn<Bid, Float> liveColumn;
    @FXML
    private TableColumn<Bid, Float> maxColumn;
    @FXML
    private TableColumn<ImageView, String> editColumn;
    @FXML
    private Button btn_delete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            BidDaoImplementation bidDao = new BidDaoImplementation();
            List<Bid> data = bidDao.getAllBids();
            ObservableList<Bid> observableBidList = FXCollections.observableList(data);

            bidColumn.setCellValueFactory(new PropertyValueFactory<>("idBid"));
            userColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
            auctionColumn.setCellValueFactory(new PropertyValueFactory<>("idAuction"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            liveColumn.setCellValueFactory(new PropertyValueFactory<>("liveBidAmount"));
            maxColumn.setCellValueFactory(new PropertyValueFactory<>("maxBidAmount"));

            tableViewBids.setItems(observableBidList);
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void deleteBidAction(ActionEvent event) {
        try {
            BidDaoImplementation bidDao = new BidDaoImplementation();
            Bid selectedBid = tableViewBids.getSelectionModel().getSelectedItem();

            if (selectedBid != null) {

                tableViewBids.getItems().remove(selectedBid);
                bidDao.deleteBid(selectedBid.getIdBid());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void updateBidAction(ActionEvent event) {
        try {
            
            
            Bid selectedBid = tableViewBids.getSelectionModel().getSelectedItem();
   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updateBid.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            UpdateBidController controller = loader.getController();
            stage.setScene(scene);
            stage.show();
            controller.setValue(selectedBid.getIdBid());
            BidDaoImplementation bidDao;
            
          

// Refresh the table view with the updated data from the database
/**
            ObservableList<Bid> data = (ObservableList<Bid>) bidDao.getAllBids();
            tableViewBids.setItems(data);
            
            * */
            
        } 
        catch (IOException ex) {
                Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @FXML
    void addBid(ActionEvent event) throws SQLException {
        if (radio_btn_live.isSelected()) {
            try {
                //Bid bid = new Bid();
                //bid.setLiveBidAmount(Float.parseFloat(txt_live_bid.getText()));

                if ((txt_live_bid.getText().isEmpty()) && (!(txt_max_bid.getText().isEmpty())) || (txt_live_bid.getText().isEmpty()) || (txt_live_bid.getText().isEmpty()) && (txt_max_bid.getText().isEmpty())) {

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

                } /*
         if(Float.parseFloat(txt_live_bid.getText())>=Float.parseFloat( txt_highest_bid.getText()))  {

            Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Invalid Input");
             alert.setHeaderText(null);
             alert.setContentText("Please enter a higher max bid amount");
             alert.showAndWait();
        
    }   **/ else {
                    BidDaoImplementation bid_dao = new BidDaoImplementation();
                    bid_dao.addLiveBid(new Bid(2, 2, Float.parseFloat(txt_live_bid.getText())));
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {

                if ((txt_live_bid.getText().isEmpty()) && (!(txt_max_bid.getText().isEmpty())) || (txt_max_bid.getText().isEmpty()) && (!(txt_live_bid.getText().isEmpty()))) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter both a live bid amount and a max bid amout");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }

            if (!(txt_max_bid.getText().matches("[0-9]*")) && (!(txt_max_bid.getText().isEmpty()))) {

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

            } else if (!(btn_terms.isSelected())) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please agree to the terms and conditions");
                alert.showAndWait();

            } else {
                BidDaoImplementation bid_dao = new BidDaoImplementation();
                bid_dao.addMaxBid(new Bid(2, 2, Float.parseFloat(txt_live_bid.getText()), Float.parseFloat(txt_max_bid.getText())));
            }
        }

    }
}
