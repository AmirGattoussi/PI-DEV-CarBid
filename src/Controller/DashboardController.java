/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

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
    private NumberAxis userNumberAxis;
    UserDao user = new UserDao();
    
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

    }
}
