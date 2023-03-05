package Main;

import Api.MailApi;
import Dao.AuctionDaoImplementation;
import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private Parent parent;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/AdminHome.fxml"));
        primaryStage.setTitle("Carbid");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1025);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
    AuctionDaoImplementation aucDao = new AuctionDaoImplementation();
    aucDao.WinnerNotificationMail();
        launch(args);
    }
}
