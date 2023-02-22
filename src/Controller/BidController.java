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

  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }

  @FXML
  void addBidLive(ActionEvent event) throws SQLException {
    try {
      Bid bid = new Bid();
      // bid.setLiveBidAmount(Float.parseFloat(txt_live_bid.getText()));
      if ((txt_live_bid.getText().isEmpty()) && (!(txt_max_bid.getText().isEmpty()))) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please enter only a live bid amount.");
        alert.showAndWait();
      }
    } catch (NumberFormatException ex) {
      System.out.println(ex.getMessage());
    }

    if (!(txt_live_bid.getText().matches("[0-9]*")) && (!(txt_live_bid.getText().isEmpty()))) {

      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setHeaderText(null);
      alert.setContentText("Please enter only a number.");
      alert.showAndWait();

    }
  }
}
