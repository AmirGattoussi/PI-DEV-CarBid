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
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws SQLException, IOException {
        
      DBconnexion database = DBconnexion.getInstance();
Connection connection = database.getConnection();
EmployeeDaoImplementation e= new EmployeeDaoImplementation();
e.addEmployee();

 Cars car1 = new Cars();
        Cars car2 = new Cars(1,"A-classs","blue","SUV","mercedes-benz","wrecked",2.3f,2020,2.4f,"auto","loss","right side","left side","Gasoline");
        Cars car3 = new Cars(1,"G-classs","yellow","SUV","BMW","w",2.5f,2020,2.6f,"manuelle","los","left side","right side","Gasoline");
        Cars car4 = new Cars();
        
        car2.setColor("red");
        car2.setYear(2011);

        car4.setMake("Cadillac");
        
        
            System.out.println("This car is " + car1.getId()+ ", model " + car1.getModel()+ ", make " + car1.getMake());
            System.out.println("This car is " + car2.getId()+ ", model " + car2.getModel()+ ", make " + car2.getMake());
            System.out.println("This car is " + car3.getId()+ ", model " + car3.getModel()+ ", make " + car3.getMake());
            System.out.println("This car is " + car4.getId()+ ", model " + car4.getModel()+ ", make " + car4.getMake());

       
        System.out.println("The total car type is: " + car1.getType());
        System.out.println("The total car type is: " + car2.getType());
        System.out.println("The total car type is: " + car3.getType());
        System.out.println("The total car type is: " + car4.getType());

/*String  name="name";
    
        try {
            Statement stm = connection.createStatement();
            String query ="INSERT INTO employee (id, name) VALUES ( '1', 'name')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("failed");
        }*/
//first commit
        // TODO code application logic here
        
    }

}