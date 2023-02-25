/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.AdminDao;
import Dao.AgentDao;
import Entities.User;
import Dao.UserDao;
import Entities.Admin;
import Entities.Agent;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class RegisterController implements Initializable {

    @FXML
    private Button registerBtn;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField LocationTextField;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private PasswordField RepeatTextField;
    @FXML
    private ChoiceBox choiceBox;
    ObservableList<String> accountType = FXCollections.observableArrayList("Standard User", "Admin", "Agent");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.setValue("Standard User");
        choiceBox.setItems(accountType);
        // TODO
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        String accountChoice;
        accountChoice = (String) choiceBox.getValue();
        System.out.println(accountChoice);

        String name = NameTextField.getText();
        String email = EmailTextField.getText();
        String phone = PhoneTextField.getText();
        String location = LocationTextField.getText();
        String password = PasswordTextField.getText();
        String confirm = RepeatTextField.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || location.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        } else if (!password.equals(confirm)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Passwords do not match");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure passwords match");
            alert.showAndWait();
        } // TODO: Save the user information to database or file
        else {
            if (accountChoice.equals("Standard User")) {
                int phoneInt;
                phoneInt = Integer.parseInt(phone);
                User user = new User(name, email, password, phoneInt, location);
                UserDao u = new UserDao();
                u.createUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("Thank you for registering!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
            } else if (accountChoice.equals("Admin")) {
                int phoneInt;
                phoneInt = Integer.parseInt(phone);
                Admin admin = new Admin(name, email, password, phoneInt, location);
                AdminDao a = new AdminDao();
                a.createAdmin(admin);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("Thank you for registering!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
            } else if (accountChoice.equals("Agent")) {
                int phoneInt;
                phoneInt = Integer.parseInt(phone);
                Agent agent = new Agent(name, email, password, phoneInt, location);
                AgentDao ag = new AgentDao();
                ag.createAgent(agent);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("Thank you for registering!");
                alert.showAndWait();
                //GO back to login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
    }
}
