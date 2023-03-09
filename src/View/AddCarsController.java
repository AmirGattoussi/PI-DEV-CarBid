/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AddAuctionDetailsController;
import Controller.RegisterController;
import Dao.CarDao;
import Entities.Car;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import static jdk.nashorn.internal.objects.NativeJava.type;
import javafx.stage.FileChooser.ExtensionFilter;;

/**
 * FXML Controller class
 *
 * @author rima
 */
public class AddCarsController implements Initializable {

    @FXML
    private TextField tfMake;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfTrans;
    @FXML
    private ComboBox<?> idCh;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfColor;
    @FXML
    private TextField tfSd;
    @FXML
    private TextField tfPd;
    @FXML
    private TextField tfLoss;
    @FXML
    private TextField tfFP;
    @FXML
    private TextField tfMileage;
    @FXML
    private TextField tfFt;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfDesc;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnimport;
    @FXML
    private ImageView imgid;
    private Object imgView;
    private String imagePath;
    private String[] type;
    private FileChooser fileChooser= new FileChooser();
    String path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void delete(ActionEvent event) {

    }

    @FXML
    private void insert(ActionEvent event) throws SQLException {
        if (tfMake.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid transmission");
            alert.showAndWait();
        } else if ((tfModel.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter loss");
            alert.showAndWait();
        } else if ((tfYear.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter fueltype");
            alert.showAndWait();
        } else if ((tfTrans.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter make");
            alert.showAndWait();
        } else if ((tfType.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter model");
            alert.showAndWait();
        } else if ((tfColor.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter type");
            alert.showAndWait();
        } else if ((tfSd.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter secondarydamage");
            alert.showAndWait();
        } else if ((tfPd.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter primarydamage");
            alert.showAndWait();
        } else if ((tfLoss.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter year");
            alert.showAndWait();
        } else if ((tfMileage.getText().isEmpty())) {

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
        } else if ((tfFP.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a mileage");
            alert.showAndWait();
        } else if ((tfFt.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a mileage");
            alert.showAndWait();
        } else {
            try {
                CarDao cardao = new CarDao();
                Car c;
                c = new Car(

                        tfModel.getText(),
                        tfColor.getText(),
                        tfType.getText(),
                        tfMake.getText(),
                        tfDesc.getText(),
                        Integer.parseInt(tfMileage.getText()),
                        Integer.parseInt(tfYear.getText()),
                        Integer.parseInt(tfFP.getText()),
                        tfTrans.getText(),
                        tfLoss.getText(),
                        tfPd.getText(),
                        tfSd.getText(),
                        tfFt.getText(),
                        this.path

                );

                cardao.insert(c);
                int carId = cardao.getCarId(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insert Car");
                alert.setHeaderText(null);
                alert.setContentText("The car is inserted successfully.");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/addAuctionDetails.fxml"));
                Parent loginParent;
                try {
                    loginParent = loader.load();
                    AddAuctionDetailsController controller = loader.getController();
                    controller.setCarId(carId);
                    Scene loginScene = new Scene(loginParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(loginScene);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AddCarsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        CarDao cardao = new CarDao();
        Car c;
        c = new Car(

                tfModel.getText(),
                tfColor.getText(),
                tfType.getText(),
                tfMake.getText(),
                tfDesc.getText(),
                Integer.parseInt(tfMileage.getText()),
                Integer.parseInt(tfYear.getText()),
                Integer.parseInt(tfFP.getText()),
                tfTrans.getText(),
                tfLoss.getText(),
                tfPd.getText(),
                tfSd.getText(),
                tfFt.getText(),
                this.path

        );

        cardao.update(c);

    }

    @FXML
    private void backhome(ActionEvent event) {
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

    private void Import(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.fileChooser.setTitle("Select an image");
        this.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File file = this.fileChooser.showOpenDialog(primaryStage);
        this.imagePath = file.getPath();
        
        
        path = file.getName();
        System.out.println(path);
        for (char c : file.getPath().toCharArray()) {
            
        }
  
        System.out.println(file.toPath());
     String phpUrl = "http://localhost/piImg/piImg.php";
//        String imageFilePath = "C:\\xamppp\\htdocs\\piImg";

        // Read the image file data
        byte[] imageData = Files.readAllBytes(file.toPath());

        // Create the boundary string for the multipart request
        String boundary = "---------------------------12345";

        // Open the connection to the PHP script
        URL url = new URL(phpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        // Write the image file data to the output stream of the connection
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
                ("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
        outputStream.write(("Content-Type: image/jpeg\r\n\r\n").getBytes());
        outputStream.write(imageData);
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
        outputStream.flush();
        outputStream.close();

        // Read the response from the PHP script
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        String x = "http://localhost/piImg/" + this.path;
    imgid.setImage(new Image(new URL(x).openStream(),245,237,false,true));
    }

}
