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
import Dao.ServicesSpareParts;
import com.sun.javafx.binding.BidirectionalContentBinding;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private Parent parent;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage=primaryStage;
        parent=FXMLLoader.load(getClass().getResource("/GUI/AdminPage.fxml"));
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
      launch(args);
 
    }

}
