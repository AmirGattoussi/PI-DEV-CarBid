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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rima
 */
public class UpdateCarController implements Initializable {

    @FXML
    private Button btnconfirm;
    @FXML
    private TextField tftransmission;
    @FXML
    private TextField tfloss;
    @FXML
    private TextField tfft;
    @FXML
    private TextField tfmake;
    @FXML
    private TextField tfmodel;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfsd;
    @FXML
    private TextField tfpd;
    @FXML
    private TextField tfyear;
    @FXML
    private TextField tffp;
    @FXML
    private TextField tfDesc;
    @FXML
    private TextField tfmileage;
    @FXML
    private TextField tfcolor;
    @FXML
    private Button btnview;

    private Car selectedCar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void confirmUpdate(ActionEvent event) {
        if (tftransmission.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid transmission");
            alert.showAndWait();
        } else if ((tfloss.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter loss");
            alert.showAndWait();
        } else if ((tfft.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter fueltype");
            alert.showAndWait();
        } else if ((tfmake.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter make");
            alert.showAndWait();
        } else if ((tfmodel.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter model");
            alert.showAndWait();
        } else if ((tftype.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter type");
            alert.showAndWait();
        } else if ((tfsd.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter secondarydamage");
            alert.showAndWait();
        } else if ((tfpd.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter primarydamage");
            alert.showAndWait();
        } else if ((tfyear.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter year");
            alert.showAndWait();
        } else if ((tffp.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter fiscalpower");
            alert.showAndWait();
        } else if ((tfDesc.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter description");
            alert.showAndWait();
        } else if ((tfmileage.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a mileage");
            alert.showAndWait();
        } else {
            try {
                CarDao carDao = new CarDao();

                carDao.update(new Car(selectedCar.getId(), tfmodel.getText(), tfcolor.getText(),
                        tftype.getText(),
                        tfmake.getText(),
                        tfDesc.getText(),
                        Integer.parseInt(tfmileage.getText()),
                        Integer.parseInt(tfyear.getText()),
                        Integer.parseInt(tffp.getText()),
                        tftransmission.getText(),
                        tfloss.getText(),
                        tfpd.getText(),
                        tfsd.getText(),
                        tfft.getText()));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Car");
                alert.setHeaderText(null);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UpdateCareController.fxml"));
                alert.setContentText("The car is updated successfully.");
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(UpdateCarController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void setValue(Car car) {

        this.selectedCar = car;
        initcar();
    }

    private void initcar() {
        System.out.println(selectedCar);
        tfcolor.setText(selectedCar.getColor());
        tftransmission.setText(selectedCar.getTransmission());
        tfloss.setText(selectedCar.getLoss());
        tfft.setText(selectedCar.getFueltype());
        tfmake.setText(selectedCar.getMake());
        tfmodel.setText(selectedCar.getModel());
        tftype.setText(selectedCar.getType());
        tfsd.setText(selectedCar.getSecondarydamage());
        tfpd.setText(selectedCar.getPrimarydamage());
        tfyear.setText(String.valueOf(selectedCar.getYear()));
        tfmileage.setText(String.valueOf(selectedCar.getMileage()));
        tffp.setText(String.valueOf(selectedCar.getFiscalpower()));
        tfDesc.setText(selectedCar.getDescription());

    }

    @FXML
    private void backlist(ActionEvent event) {
        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ListCars.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ListCarsController controller = loader.getController();

            stage.setScene(scene);
            stage.show();
            CarDao carDao;

        } catch (IOException ex) {
            Logger.getLogger(ListCarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}