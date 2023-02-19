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
import Entities.*;

// import java.util.Date;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import java.util.logging.Level;
// import java.util.logging.Logger;
/**
 *
 * @author code-connect
 */
public class Main {
    // public static Stage stg;

    // @Override
    // public void start(Stage primaryStage) throws IOException
    // {
    // this.stg = primaryStage;
    // FXMLLoader loader= new
    // FXMLLoader(getClass().getResource("../GUI/Main.fxml"));
    // Parent root= loader.load();
    // Scene scene= new Scene(root);

    // primaryStage.setTitle("CarBid");
    // primaryStage.setScene(scene);
    // primaryStage.show();
    // }
    /**
     * @param args the command line arguments
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Reservation reserve = new Reservation(3, 1, "15-11-2023", "Ariana", 1);

        // DBconnexion database = DBconnexion.getInstance();
        // Connection connection = database.getConnection();

        ReservationDao e = new ReservationDao();
        // e.createReservation(reserve);
        // e.deleteReservation(3, 1);
        // e.updateReservationDate(3, 1, "15-11-2023");
        // e.updateReservationLocation(3, 1, "Sousse Sahloul");
        System.out.println(e.getReservation(3, 1));
    }

}