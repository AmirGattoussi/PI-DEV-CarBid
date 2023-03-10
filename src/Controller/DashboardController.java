/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import Dao.UserDao;
import Utils.DBconnexion;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Integer> userChart;
    @FXML
    private PieChart conversionRateChart;
    @FXML
    private CategoryAxis userCategoryAxis;
    @FXML
    private Label liveAuctionsLabel;
    @FXML
    private Label visitCounter;
    @FXML
    private Label userNumberLabel;
    @FXML
    private NumberAxis userNumberAxis;
    @FXML
    private Button fathiBtn;
    
    UserDao user = new UserDao();
    private ObservableList data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize user data and add it to the user chart
        userCategoryAxis.setLabel("User Type");
        userNumberAxis.setLabel("Number of Users");
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Admin", user.getNumberOfAdmins()));
        series.getData().add(new XYChart.Data<>("Agent", user.getNumberOfAgents()));
        series.getData().add(new XYChart.Data<>("Subscribers", user.getNumberOfSubs()));
        userChart.getData().add(series);
        //Setting Up Labels
        userNumberLabel.setText("" + user.getNumberOfUsers());
        //LIVE AUCTIONS COUNT
        try {
            Connection cnx = DBconnexion.getInstance().getConnection();
            data = FXCollections.observableArrayList();
            AuctionDaoImplementation auc = new AuctionDaoImplementation();
            PreparedStatement statement;

            statement = cnx.prepareStatement("SELECT count(*) FROM auction WHERE status='open'");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int number = resultSet.getInt("count(*)");
                liveAuctionsLabel.setText(Integer.toString(number));
            }

        } catch (Exception ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Method to count live auctions

    public int getLiveAuctions() {
        int lAuctions = 0;
        AuctionDaoImplementation auc = new AuctionDaoImplementation();
        //TO DO
        return lAuctions;
    }
    @FXML
    private void bestBidders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/BestBidders.fxml"));
        Parent loginParent;
        try {
            loginParent = loader.load();
            Scene loginScene = new Scene(loginParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
