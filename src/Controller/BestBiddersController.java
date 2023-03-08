/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.BidDaoImplementation;
import Dao.ReservationDao;
import Entities.Winner;
import Utils.DBconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 *
 * @author asus
 */
public class BestBiddersController implements Initializable {
@FXML
private PieChart bestWinnerspieChart;
private ObservableList data;    
     Connection cnx;

    public BestBiddersController() throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }
   public void getBestThreeBidders() {
     //List<Winner> data = new ArrayList<Winner>();
     data = FXCollections.observableArrayList();
        PreparedStatement statement;
      try {
            statement = cnx.prepareStatement(
                    "SELECT b.userId,u.name,COUNT(liveBidAmount),u.phone_number FROM bid b join user u JOIN auction a ON u.id_user=b.userId and a.idAuction = b.idAuction WHERE status = 'closed' AND b.liveBidAmount=a.highestBid GROUP BY 1 ORDER BY 3 DESC LIMIT 3;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new PieChart.Data(
                        resultSet.getString("name"),
                        resultSet.getInt("COUNT(liveBidAmount)")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   @Override
    public void initialize(URL location, ResourceBundle resources) {
      PieChart pieChart = new PieChart();
        getBestThreeBidders();
        bestWinnerspieChart.setData(data);
        bestWinnerspieChart.setVisible(true);
        bestWinnerspieChart.setTitle("The Top Three Best Winning Bidders");
         
    }
}
