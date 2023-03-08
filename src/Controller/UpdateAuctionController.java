/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author asus
 */
public class UpdateAuctionController implements Initializable {

    private int aucId;
    @FXML
    private TextField highest_txt;
    @FXML
    private TextField status_txt;
    @FXML
    private DatePicker e_date_pick;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setValue(int aucId) {

        this.aucId = aucId;
    }

    @FXML
    private void updateAuctionAct(ActionEvent event) {

        if (highest_txt.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the highest bid");
            alert.showAndWait();
        } else if ((status_txt.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a status");
            alert.showAndWait();
        } else if (!(highest_txt.getText().matches("[0-9]*"))) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only a number");
            alert.showAndWait();

        } else if ((e_date_pick.getValue() == null)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the date");
            alert.showAndWait();
        } else if (!(status_txt.getText().matches("[A-Za-z][a-z]*"))) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a string");
            alert.showAndWait();

        } else {
            AuctionDaoImplementation aucDao = new AuctionDaoImplementation();
            LocalDate localDate = e_date_pick.getValue();
            java.util.Date utilDate = java.sql.Date.valueOf(localDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            aucDao.updateAuction(aucId, Float.parseFloat(highest_txt.getText()), status_txt.getText(), sqlDate);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Auction");
            alert.setHeaderText(null);
            alert.setContentText("The auction is updated successfully.");
            alert.showAndWait();

        }

    }

}
