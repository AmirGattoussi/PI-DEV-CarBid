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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gtsia
 */
public class Main extends Application{
public static Stage stg;
    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.stg = primaryStage;
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Main.fxml"));
        Parent root= loader.load();
        Scene scene= new Scene(root);
        
        primaryStage.setTitle("CarBid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
                launch(args);

       /* Connection cnx=DBconnexion.getInstance().getConnection();
    Admin admin=new Admin(100,10,"admin","admin@gmail.com","adminpass");
    AdminDao adminDao=new AdminDao();
adminDao.view_users();    /*UserDao userDao;
        User amir = new User (3,"amir","Gattoussi","pass3");
         User yosr = new User (5,"yosr","moalla","pass2");
        userDao = new UserDao();
        userDao.createUser(amir);
        userDao.createUser(yosr);
        userDao.updateUser(amir);*/
    }

   

}
