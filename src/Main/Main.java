/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.*;
import Utils.DBconnexion;
import Dao.*;
import Entities.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * @author neil
 */
public class Main{
// public static Stage stg;
    
//     @Override
//     public void start(Stage primaryStage) throws IOException
//     {
//         this.stg = primaryStage;
//         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Main.fxml"));
//         Parent root= loader.load();
//         Scene scene= new Scene(root);
        
//         primaryStage.setTitle("CarBid");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }
    /**
     * @param args the command line arguments
     */

    
    public static void main(String[] args) throws SQLException, IOException {
        
   /* Reservation reserve = new Reservation(3, 1, "28/02/2023", "Ariana", 1);

    DBconnexion database = DBconnexion.getInstance();
    Connection connection = database.getConnection();

      ReservationDao e = new ReservationDao();
*/
      ServicesSpareParts s=new ServicesSpareParts();
     System.out.println(s.display());
      SpareParts p2 = new SpareParts(1,"moteur",3,"occasion",1200,"volswagen");
    s.add(p2);
// e.createReservation(reserve);
    // e.deleteReservation(3, 1);
    //System.out.println(e.getReservation(3, 1));
    }

}