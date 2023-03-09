/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import Dao.CarDao;
import Entities.Auction;
import Entities.Car;
import View.ListCarsController;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class AddAuctionDetailsController implements Initializable {
    
    private int carId;
    
   public void setCarId(int carId) {
        this.carId = carId;
        System.out.println(carId);
        float x =carDao.displayById(carId).getbasevalue();
        System.out.println(x);
        priceField.setText(carDao.displayById(carId).getbasevalue()+""); 
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
    CarDao carDao = CarDao.getInstance();
    @FXML
    void onCancel(ActionEvent event) {
        // TODO: Implement onCancel method
    }
    AuctionDaoImplementation auc=new AuctionDaoImplementation();
    @FXML
    void onConfirm(ActionEvent event) {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        if (startDatePicker.getValue()== null ||(endDatePicker.getValue()== null)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid transmission");
            alert.showAndWait();
         }

       else {
            try {
        Float startingPrice = Float.parseFloat(priceField.getText());
        java.sql.Date startDatesql = java.sql.Date.valueOf(startDate);
        java.sql.Date endDatesql = java.sql.Date.valueOf(endDate);
        
        Auction auction = new Auction(startDatesql, endDatesql, startingPrice, 0, "Open", carId);
        auc.addAuction(auction);
        
         
                AuctionDaoImplementation auctionDaoImplementation = new AuctionDaoImplementation();

                auctionDaoImplementation.addAuction(
                        new Auction(
                         startDatesql,
                         endDatesql,
                         Float.parseFloat(priceField.getText()),0,"Open", carId)
                         );
                        
                        
                        
                      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AuctionConfirm");
                alert.setHeaderText(null);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddDetailsController.fxml"));
                alert.setContentText("The Auction is confirmed successfully.");
                alert.showAndWait();

            } catch (NumberFormatException ex) {
                Logger.getLogger(AddAuctionDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }}
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Implement initialize method
        System.out.println(carId);
        
    }
    @FXML
    void Cancel(ActionEvent event) {
          try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ListCars.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ListCarsController controller = loader.getController();

            stage.setScene(scene);
            stage.show();
            CarDao carDao;

        } catch (IOException ex) {
            Logger.getLogger(ListCarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

