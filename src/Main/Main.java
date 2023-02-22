/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.sql.*;
import Utils.DBconnexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Dao.*;
import Entities.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sun.javafx.binding.BidirectionalContentBinding;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author neil
 */
public class Main extends Application {

   @Override
   public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("../View/DisplayCar.fxml"));
       primaryStage.setTitle("DisplayCar");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
 
   
  
   
   
   }
       
   
   
   
   
   
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
      launch(args);
 


    }

    

}
