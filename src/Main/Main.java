package Main;

import java.io.IOException;
import java.sql.*;
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
public class Main extends Application{
    private Stage stage;
    private Parent parent;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/manageReservations.fxml"));
        primaryStage.setTitle("Carbid");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1025);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        launch(args);

    }

}
