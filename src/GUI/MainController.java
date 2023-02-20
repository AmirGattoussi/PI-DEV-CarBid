/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author gtsia
 */
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Dao.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MainController {

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField registerNameField;

    @FXML
    private TextField registerEmailField;

    @FXML
    private PasswordField registerPasswordField;

    @FXML
    private void handleLogin() {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();
        try {
            UserDao userdao=new UserDao();
            if (userdao.login(email, password)) {
                System.out.println("Login success");
            // redirect the user to the next page or do something else
        } else {
            System.out.println("error");
            // show an error message to the user
        }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // call the login method from your CRUD implementation to check if the user exists
        
    }

    @FXML
    private void handleRegister() {
        String name = registerNameField.getText();
        String email = registerEmailField.getText();
        String password = registerPasswordField.getText();

        // call the register method from your CRUD implementation to create a new user
        /*if (crudImplementation.register(name, email, password)) {
            // show a success message to the user
        } else {
            // show an error message to the user
        }*/
    }
}

