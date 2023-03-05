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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rima
 */
public class DisplayCarController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btncarspecification;
    @FXML
    private Button btnphoto;
    @FXML
    private Button btnBid;
    @FXML
    private TextField tfDesc;
    @FXML
    private Pane Photos;
    @FXML
    private Pane Bidinformation;
    @FXML
    private Pane Carspecification;
    @FXML
    private Text textcolor;
    private Car selectedCar;
    @FXML
    private Text texttype;
    @FXML
    private Text entryPrice;
    @FXML
    private Text Textmodel;
    @FXML
    private Text Textyear;
    @FXML
    private Text textsd;
    @FXML
    private Text textfp;
    @FXML
    private Text textmileage;
    @FXML
    private Text textmake;
    @FXML
    private Text textpd;
    @FXML
    private Text textloss;
    @FXML
    private Text textft;
    @FXML
    private Text textDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Back(ActionEvent event) {
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

    @FXML
    private void fncarspecification(KeyEvent event) {
    }

    @FXML
    private void carspecification(ActionEvent event) {
    }

    @FXML
    private void fnphoto(KeyEvent event) {
        Carspecification.toFront();
    }

    @FXML
    private void photo(ActionEvent event) {
        Photos.toFront();
    }

    @FXML
    private void setbid(ActionEvent event) {
    }

    public void setValue(Car car) {

        this.selectedCar = car;
        initcar();
    }

    private void initcar() {
        System.out.println(selectedCar);
        textcolor.setText(selectedCar.getColor());
        texttype.setText(selectedCar.getType());
        Textmodel.setText(selectedCar.getModel());
        Textyear.setText(selectedCar.getYear() + " ");
        entryPrice.setText(selectedCar.getbasevalue() + " ");
        textfp.setText(selectedCar.getFiscalpower() + " ");
        textmileage.setText(selectedCar.getMileage() + " ");
        texttype.setText(selectedCar.getType());
        textmake.setText(selectedCar.getMake());
        textloss.setText(selectedCar.getLoss());
        textft.setText(selectedCar.getFueltype());
        textDesc.setText(selectedCar.getDescription());
        textsd.setText(selectedCar.getSecondarydamage());
        textpd.setText(selectedCar.getPrimarydamage());

        // textcolor.setText(selectedCar.getColor());

    }

}