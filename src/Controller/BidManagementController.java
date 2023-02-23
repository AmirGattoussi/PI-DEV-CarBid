/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.BidDaoImplementation;
import Entities.Bid;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class BidManagementController implements Initializable {
       @FXML
    private Button btn_confirm;
    @FXML
    private CheckBox btn_terms;
   
    
 
   
    @FXML
    private Button btn_delete;
     @FXML
    private TableColumn<Bid, Integer> bidColumn;
   @FXML
    private TableView<Bid> tableViewBids;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
         
          BidDaoImplementation bidDao = new BidDaoImplementation(); List<Bid>
          data = bidDao.getAllBids(); ObservableList<Bid> observableBidList =
          FXCollections.observableList(data);
         
          bidColumn.setCellValueFactory(new PropertyValueFactory<>("idBid"));
          userColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
          auctionColumn.setCellValueFactory(new
          PropertyValueFactory<>("idAuction"));
          dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
         typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
          liveColumn.setCellValueFactory(new
          PropertyValueFactory<>("liveBidAmount"));
          maxColumn.setCellValueFactory(new
          PropertyValueFactory<>("maxBidAmount"));
         
          tableViewBids.setItems(observableBidList); } catch (SQLException ex)
          { Logger.getLogger(BidController.class.getName()).log(Level.SEVERE,
         null, ex); }
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
        Bid selectedBid = tableViewBids.getSelectionModel().getSelectedItem();
        if (selectedBid == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item");
            alert.showAndWait();

        } else {
            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updateBid.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                UpdateBidController controller = loader.getController();
                stage.setScene(scene);
                stage.show();
                controller.setValue(selectedBid.getIdBid());
                BidDaoImplementation bidDao;


            } catch (IOException ex) {
                Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
  
}
  