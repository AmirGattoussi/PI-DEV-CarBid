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


/**
 *
 * @author code-connect
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Controller/manageReservations.fxml"));
        primaryStage.setTitle("Carbid");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    /**
     * @param args the command line arguments
     * @throws Exception
     */

    // public static void main(String[] args) throws Exception {
    //     Reservation reserve = new Reservation(3, 2, "17-10-2023", "Ariana", 1);

    //     // DBconnexion database = DBconnexion.getInstance();
    //     // Connection connection = database.getConnection();

    //     ReservationDao e = new ReservationDao();
    //     // e.createReservation(reserve);
    //     // e.deleteReservation(3, 1);
    //     // e.updateReservationDate(3, 1, "15-11-2023");
    //     // e.updateReservationLocation(3, 1, "Sousse Sahloul");
    //     // System.out.println(e.getReservation(3, 2));
    //     System.out.println(e.filterReservationsByDate("15-11-2023"));
    // }

}
