/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import Entities.Auction;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class AddAuctionDetailsController implements Initializable {
    
    private int carId;
    
   public void setCarId(int carId) {
        this.carId = carId;
    }


    /**
     * Initializes the controller class.
     */
    @FXML
    private DatePicker startDatePicker;
    
    @FXML
    private DatePicker endDatePicker;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnConfirm;
    
    @FXML
    void onCancel(ActionEvent event) {
        // TODO: Implement onCancel method
    }
    AuctionDaoImplementation auc=new AuctionDaoImplementation();
    @FXML
    void onConfirm(ActionEvent event) {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        Float startingPrice = Float.parseFloat(priceField.getText());
        java.sql.Date startDatesql = java.sql.Date.valueOf(startDate);
        java.sql.Date endDatesql = java.sql.Date.valueOf(endDate);
        
        Auction auction = new Auction(startDatesql, endDatesql, startingPrice, 0, "open", carId);
        auc.addAuction(auction);
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Implement initialize method
        System.out.println(carId);
    }
    
}
