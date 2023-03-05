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
import static java.time.Instant.now;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class UpdateBidController implements Initializable {

    private int valueId;
    @FXML
    private TextField txt_live;
    @FXML
    private TextField txt_max;
    @FXML
    private DatePicker date_field;
    @FXML
    private TextField txt_status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void updateBidAct(ActionEvent event) {

        if (txt_live.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a live bid amount");
            alert.showAndWait();
        } else if ((txt_status.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a status");
            alert.showAndWait();
        } else if ((txt_max.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a max bid amount");
            alert.showAndWait();
        } else if (!(txt_max.getText().matches("[0-9]*")) || (!(txt_live.getText().matches("[0-9]*")))) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only a number");
            alert.showAndWait();

        }

        else if ((date_field.getValue() == null)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the date");
            alert.showAndWait();
        } else if (Float.parseFloat(txt_live.getText()) >= Float.parseFloat(txt_max.getText())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a higher max bid amount");
            alert.showAndWait();

        } else {
            try {
                BidDaoImplementation bidDao = new BidDaoImplementation();
                LocalDate localDate = date_field.getValue();
                java.util.Date utilDate = java.sql.Date.valueOf(localDate);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                bidDao.updateBid(valueId, sqlDate, txt_status.getText(), Float.parseFloat(txt_live.getText()),
                        Float.parseFloat(txt_max.getText()));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Bid");
                alert.setHeaderText(null);
                alert.setContentText("The bid is updated successfully.");
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void setValue(int valueId) {

        this.valueId = valueId;
    }

}
