/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import Dao.BidDaoImplementation;
import Entities.Auction;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AuctionManagementController implements Initializable {

    @FXML
    private TableColumn<Auction, Integer> auction_column;
    @FXML
    private TableView<Auction> tableViewAuctions;
    @FXML
    private TableColumn<Auction, Integer> car_column;
    @FXML
    private TableColumn<Auction, Date> s_date_column;
    @FXML
    private TableColumn<Auction, Date> e_date_column;
    @FXML
    private TableColumn<Auction, String> status_column;
    @FXML
    private TableColumn<Auction, Float> s_price_column;
    @FXML
    private TableColumn<Auction, Float> highest_column;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AuctionDaoImplementation aucDao = new AuctionDaoImplementation();
        List<Auction> data = aucDao.getAllAuctions();
        ObservableList<Auction> observableAuctionList = FXCollections.observableList(data);
        auction_column.setCellValueFactory(new PropertyValueFactory<>("idAuction"));
        car_column.setCellValueFactory(new PropertyValueFactory<>("idCar"));
        s_date_column.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        e_date_column.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        s_price_column.setCellValueFactory(new PropertyValueFactory<>("startingPrice"));
        highest_column.setCellValueFactory(new PropertyValueFactory<>("highestBid"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableViewAuctions.setItems(observableAuctionList);

    }

    @FXML
    private void deleteAuctionAction(ActionEvent event) {
        AuctionDaoImplementation bidDao = new AuctionDaoImplementation();
        Auction selectedAuction = tableViewAuctions.getSelectionModel().getSelectedItem();
        if (selectedAuction != null) {

            tableViewAuctions.getItems().remove(selectedAuction);
            bidDao.deleteAuction(selectedAuction.getIdAuction());
        }

    }

    @FXML
    private void updateAuctionAction(ActionEvent event) {
        Auction selectedAuction = tableViewAuctions.getSelectionModel().getSelectedItem();
        if (selectedAuction == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item");
            alert.showAndWait();

        } else {
            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updateAuction.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                UpdateAuctionController controller = loader.getController();
                stage.setScene(scene);
                stage.show();
                controller.setValue(selectedAuction.getIdAuction());

            } catch (IOException ex) {
                Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void redirBid(ActionEvent event) {

        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Bid_management.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            BidManagementController controller = loader.getController();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
