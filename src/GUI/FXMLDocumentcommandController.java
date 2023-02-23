/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import Entities.*;
/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class FXMLDocumentcommandController implements Initializable {

    
    @FXML
    private TextField text_cmd_id_command;
    @FXML
    private TextField text_cmd_id_user;
    @FXML
    private TextField text_cmd_id_sparepart;
    
    @FXML
    private TableView<?> tablecommand;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
    }

    @FXML
    private void modify(ActionEvent event) {
    }

    @FXML
    private void display(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void clickMousee(MouseEvent event) {
    }

    @FXML
    private void Refresh(ActionEvent event) {
    }

    @FXML
    private void chercher(KeyEvent event) {
    }
    
}
