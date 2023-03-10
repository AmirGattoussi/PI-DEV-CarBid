/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AuctionDaoImplementation;
import Dao.ReservationDao;
import Dao.UserDao;
import Utils.DBconnexion;
import java.io.BufferedReader;
import java.io.FileReader;
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
    private PieChart conversionChart;
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
    Connection cnx;

    public DashboardController() throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    public void getActiveUsers() {
        //List<Winner> data = new ArrayList<Winner>();
        data = FXCollections.observableArrayList();
        PreparedStatement statement;
        PreparedStatement statement2;
        try {
            statement = cnx.prepareStatement(
                    "Select count(DISTINCT(u.id_user))  from user u join cars c on u.id_user=c.id_user;");
            statement2 = cnx.prepareStatement("select count(DISTINCT(u.id_user)) from user u LEFT JOIN cars c ON u.id_user=c.id_user WHERE c.id_user IS NULL;");
            ResultSet resultSet = statement.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();
            while ((resultSet.next()) && (resultSet2.next())) {
                int have = resultSet.getInt("count(DISTINCT(u.id_user))");
                int nothave = resultSet2.getInt("count(DISTINCT(u.id_user))");
                System.out.println("IN"+have);
                System.out.println("NOT HAVE "+nothave);
                data.add(new PieChart.Data(
                        "Users with listed cars",
                        have
                )
                );
                data.add(new PieChart.Data(
                        "Users with no listed cars",
                        nothave
                )
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        getActiveUsers();
        PieChart chart = new PieChart(data);

        // Set the title of the conversionRateChart
        chart.setTitle("Conversion Rate");

        // Set the legend of the conversionRateChart
        chart.setLegendVisible(false);

        // Add the conversionRateChart to your scene
        //conversionChart = chart;
        System.out.println("data "+data);
        conversionChart.setData(data);
        conversionChart.setVisible(true);
        conversionChart.setTitle("Conversion Rate");
        //VISITOR COUNT
        String filename = "login_log.txt";
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        visitCounter.setText(Integer.toString(lineCount));
    }
    //Method to count live auctions

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
