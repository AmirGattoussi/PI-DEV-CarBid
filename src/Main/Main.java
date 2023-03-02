/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.sql.*;
import Utils.DBconnexion;
import Dao.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dao.AuctionDaoImplementation;
import Entities.*;
import com.sun.javafx.binding.BidirectionalContentBinding;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author code-connect
 */
public class Main extends Application {

   @Override
   public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("../View/DisplayCar.fxml"));
       //primaryStage.setTitle("AddCar");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
 
   
  
       
       }
   
   
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws SQLException, IOException {
       launch(args);
        /*  User amir2=new User(10,"amir2","mail@","passamir",20,"tunis");

        UserDao user=new UserDao();
        user.createUser(amir2);
         user.login("mail@", "passamir");


/*       
         Carwishlist wishlist = new Carwishlist();
         wishlist.addCar("Tesla Model S");
         wishlist.addCar("Porsche 911");
         wishlist.addCar("Audi R8");
         wishlist.removeCar("Porsche 911");
         ArrayList<String> cars = wishlist.getCars();
         
         System.out.println("My car wishlist has "  + " cars:");
         for (String car : cars) {
             System.out.println(car); 

    } */

   
}}
