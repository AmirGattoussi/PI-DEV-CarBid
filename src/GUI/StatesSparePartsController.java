/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import Dao.ServicesSpareParts;
import Entities.SpareParts;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;



/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class StatesSparePartsController implements Initializable {

    @FXML
    private PieChart statesSP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServicesSpareParts sps = new ServicesSpareParts();
            
            int nbrmot=sps.CountSPPerType("car_engine");
            System.out.println(nbrmot);
            int nbrdecv=   sps.CountSPPerType("volant_design");
            System.out.println(nbrdecv);
            
            int nbrpara=sps.CountSPPerType("bumper");
            System.out.println(nbrpara);
            
            
            
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("car_engine", nbrmot),
                    new PieChart.Data("volant_design", nbrdecv),
            new PieChart.Data("bumper", nbrpara));
            statesSP.setTitle("Graph of spare parts per type");
             statesSP.setData(pieData); 
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StatesSparePartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  
}
