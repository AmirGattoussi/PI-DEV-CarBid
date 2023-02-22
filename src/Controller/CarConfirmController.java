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

public class CarConfirmController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        btn.setOnAction(event->{
//            txt.setText("The first workshop :)))");
//        });
    
    }
    
@FXML
 void redir(ActionEvent event)  {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Bid.fxml"));
                Parent root = loader.load();
                 BidController controller = loader.getController();

    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle("New View");
    stage.show();
    
            } catch (IOException ex) {
                Logger.getLogger(BidController.class.getName()).log(Level.SEVERE, null, ex);
            }
   
}
    
    
    
    
    
}


