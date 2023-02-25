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

    public static void main(String[] args) throws Exception {
        launch(args);
    //     Reservation reserve = new Reservation(3, 2, "17-10-2023", "Ariana", 1);

    //     // DBconnexion database = DBconnexion.getInstance();
    //     // Connection connection = database.getConnection();

    //     ReservationDao e = new ReservationDao();
    //     e.createReservation(reserve);
    //     // e.deleteReservation(3, 1);
    //     // e.updateReservationDate(3, 1, "15-11-2023");
    //     // e.updateReservationLocation(3, 1, "Sousse Sahloul");
    //     System.out.println(e.getReservation(3, 2));
    //     // System.out.println(e.getReservations());
    // }

    /**
     * @param args the command line arguments
     */
    // public static void main(String[] args) throws SQLException, IOException {
    // launch(args);

    // BidDaoImplementation bid= new BidDaoImplementation();
    // AuctionDaoImplementation auc=new AuctionDaoImplementation();
    // Auction auction = new Auction(new Date (123,5,19),new Date(124,6,5), 4444,
    // 1111, "closed", 1);
    // auc.addAuction(auction);
    // System.out.println(auc.getAuction(3).toString());
    // auc.updateAuction(3, 1212, "open");
    // auc.deleteAuction(3);
    // System.out.println(auc.getHighestBidById(4));

    // System.out.println(auc.getAuction(6));

    // }

    /**
     * @param args the command line arguments
     */
    /*
     * public static void main(String[] args) throws SQLException, IOException {
     * AuctionDaoImplementation auc=new AuctionDaoImplementation();
     * Auction auction = new Auction(new Date (123,5,19),new Date(124,6,10), 4567,
     * 1234, "open", 5);
     * auc.addAuction(auction);
     * System.out.println(auc.getAuction(😎.toString());
     * auc.deleteAuction(8);
     * auc.updateAuction(8, 333333, "open");
     * BidDaoImplementation bid= new BidDaoImplementation();
     * Bid b= new Bid(4, 4, new Date(124,3,14), "Live", 4444,4444);
     * bid.addLiveBid(b);
     * bid.addMaxBid(b);
     * System.out.println(bid.getBid(3));
     * bid.updateBid(2, new Date(125,8,8), "Max", 5555, 6666);
     * bid.deleteBid(2);
     */
    /**
     * @param args the command line arguments
     * @throws Exception
     */

}
/*
 * ServicesSpareParts s=new ServicesSpareParts();
 * //System.out.println(s.display());
 * SpareParts p2 = new SpareParts(1,"moteur",3,"occasion",1200,"volswagen");
 * SpareParts p3 = new SpareParts(1,"volan",3,"occasion",1200,"volswagen");
 * //s.add(p2);
 * //s.modify(p3);
 */
}