/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.AdminDao;
import Entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gtsia
 */
public class AdminPageController implements Initializable {

    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, Integer> phoneColumn;
    @FXML
    private TableColumn<User, String> locationColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableView userTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdminDao admin = new AdminDao();
        List<User> userList = admin.view_users();

        ObservableList<User> observableUserList = FXCollections.observableList(userList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        userTableView.setItems(observableUserList);

    }

}
