/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao.CarDao;
import Entities.Car;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rima
 */
public class ListCars1Controller implements Initializable {

    @FXML
    private Button btnupdate;
    @FXML
    private TableView<Car> tvID;
    @FXML
    private TableColumn<Car, String> tcmake;
    @FXML
    private TableColumn<Car, String> tcmodel;
    @FXML
    private TableColumn<Car, String> tccolor;
    @FXML
    private TableColumn<Car, String> tctransmission;
    @FXML
    private TableColumn<Car, String> tcfueltype;
    @FXML
    private TableColumn<Car, String> tcloss;
    @FXML
    private TableColumn<Car, String> tctype;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnAfficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Car> cars = FXCollections.observableArrayList();
        try {

            CarDao carDao = new CarDao();
            List<Car> data = carDao.displayAll();
            ObservableList<Car> observableList = FXCollections.observableList(data);

            tcmake.setCellValueFactory(new PropertyValueFactory<>("make"));
            tcmodel.setCellValueFactory(new PropertyValueFactory<>("model"));
            tccolor.setCellValueFactory(new PropertyValueFactory<>("color"));
            tctransmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));
            tcfueltype.setCellValueFactory(new PropertyValueFactory<>("fueltype"));
            tcloss.setCellValueFactory(new PropertyValueFactory<>("loss"));
            tctype.setCellValueFactory(new PropertyValueFactory<>("type"));

            tvID.setItems(observableList);
        } catch (SQLException ex) {
            Logger.getLogger(ListCars1Controller.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        Car selectecar = tvID.getSelectionModel().getSelectedItem();
        if (selectecar == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item");
            alert.showAndWait();

        } else {
            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updateCar.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                UpdateCarController controller = loader.getController();

                stage.setScene(scene);
                stage.show();
                controller.setValue(selectecar);
                CarDao carDao;

            } catch (IOException ex) {
                Logger.getLogger(ListCars1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void viewcar(ActionEvent event) {
        Car selectecar = tvID.getSelectionModel().getSelectedItem();
        if (selectecar == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item");
            alert.showAndWait();

        } else {
            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DisplayCar.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                DisplayCarController controller = loader.getController();

                stage.setScene(scene);
                stage.show();
                controller.setValue(selectecar);
                CarDao carDao;

            } catch (IOException ex) {
                Logger.getLogger(ListCars1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            CarDao carDao = new CarDao();
            Car selectedCar = tvID.getSelectionModel().getSelectedItem();

            if (selectedCar != null) {

                tvID.getItems().remove(selectedCar);
                carDao.delete(selectedCar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCars1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void caradd(ActionEvent event) {

        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddCars.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AddCarsController controller = loader.getController();

            stage.setScene(scene);
            stage.show();
            CarDao carDao;

        } catch (IOException ex) {
            Logger.getLogger(ListCars1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
