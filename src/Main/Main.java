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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Dao.*;
import Entities.*;

// import java.util.logging.Level;
// import java.util.logging.Logger;
/**
 *
 * @author neil
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
        primaryStage.setTitle("Carbid");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    /**
     * @param args the command line arguments
     * @throws Exception
     */

    

}
