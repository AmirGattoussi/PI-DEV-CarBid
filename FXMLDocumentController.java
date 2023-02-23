/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Dao.ServicesSpareParts;
import Entities.SpareParts;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import services.InformationsSupplementairesService;
//import entities.InformationsSupplementaires;

//import javafxgestionuser.AfficherUser1Controller;
/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField text_main_id;
    @FXML
    private TextField text_main_type;
    @FXML
    private TextField text_main_pou;
    @FXML
    private TextField text_main_description;
    @FXML
    private TextField text_main_price;
    @FXML
    private Button btn_main_add;
    @FXML
    private Button btn_main_modify;
    @FXML
    private Button btn_main_display;
    @FXML
    private Button btn_main_delete;
    @FXML
    private TableView<SpareParts> tablespareparts;
    @FXML
    private TableColumn<SpareParts, Integer> id_tab;
    @FXML
    private TableColumn<SpareParts, String> type_tab;
    @FXML
    private TableColumn<SpareParts, Integer> pou_tab;
    @FXML
    private TableColumn<SpareParts, String> description_tab;
    @FXML
    private TableColumn<SpareParts, Double> price_tab;
    @FXML
    private TableColumn<SpareParts, String> typec_tab;
    @FXML
    private Button btn_main_refresh;
    @FXML
    private TextField text_main_typec;
    @FXML
    private TextField look_for_spt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            ServicesSpareParts us = new ServicesSpareParts();
            List<SpareParts> splist = us.display();
            ObservableList<SpareParts> list = FXCollections.observableArrayList(splist);
            id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
            type_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
            pou_tab.setCellValueFactory(new PropertyValueFactory<>("pou"));
            description_tab.setCellValueFactory(new PropertyValueFactory<>("description"));
            price_tab.setCellValueFactory(new PropertyValueFactory<>("price"));
            typec_tab.setCellValueFactory(new PropertyValueFactory<>("typec"));
            tablespareparts.setItems(list);
            // list.addAll(us.display());
            //System.out.println(list);
            //tablespareparts.setItems(list);
            // tablespareparts.setVisible(true);//baad ma 3ammart el lista bich t7otha fil tableau fil graphique
            // tablespareparts.setEditable(true);//t9olo les element fil tableau etidable wella la!,


            /*ObservableList selectedCells = tablespareparts.getSelectionModel().getSelectedCells();
            selectedCells.addListener(new ListChangeListener() {
                @Override
                public void onChanged(ListChangeListener.Change c) {
                    SpareParts uselected = (SpareParts) tablespareparts.getSelectionModel().getSelectedItem();//wa9teli t7ot el sourie 3ala ligne fil tableau (wdli hiya el user )selectionne
                    System.out.println("selected value " + uselected);

                }

            });
             */
        } catch (SQLException e) {
        }

    }

    @FXML
    private void add(ActionEvent event) {
        try {
            System.out.println("Bontton Ajouter Lu");
            if ((text_main_type.getText().isEmpty()) || (text_main_typec.getText().isEmpty()) || (text_main_id.getText().isEmpty()) || (text_main_pou.getText().isEmpty()) || (text_main_description.getText().isEmpty()) || (text_main_price.getText().isEmpty())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter data.");
                alert.showAndWait();
            } else {
                ServicesSpareParts us = new ServicesSpareParts();
                Integer id = Integer.parseInt(text_main_id.getText());
                String type = text_main_type.getText();
                Integer pou = Integer.parseInt(text_main_pou.getText());
                String description = text_main_description.getText();
                Double price = Double.parseDouble(text_main_price.getText());
                String typec = text_main_typec.getText();

                SpareParts u = new SpareParts(id, type, pou, description, price, typec);
                us.add(u);
            }
            //refresh(event);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clickMouse(MouseEvent event) {

    }

    @FXML
    private void modify(ActionEvent event) throws SQLException {
        ServicesSpareParts us = new ServicesSpareParts();

        Integer id = Integer.parseInt(text_main_id.getText());
        String type = text_main_type.getText();
        Integer pou = Integer.parseInt(text_main_pou.getText());
        String description = text_main_description.getText();
        Double price = Double.parseDouble(text_main_price.getText());
        String typec = text_main_typec.getText();
        SpareParts u = new SpareParts(id, type, pou, description, price, typec);
        us.modify(u);
    }

    @FXML
    private void display(ActionEvent event) {

    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        //bich t3ayat lel service bich ifas5 el user selectionne 3abra el boutton supprimer (ActionEvent )
        System.out.println("Bontton supprimer Lu");
        ServicesSpareParts us = new ServicesSpareParts();
        try {
            us = new ServicesSpareParts();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer idu = Integer.parseInt(text_main_id.getText());
        //inti hne keteb int idu fi wistha Ineteger hedheka alech howa ma9rach el button
        //ena tw raditha Integer idu bich mchetli ou maadesh t7ot el int idu
        us.delete(idu);
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Deleted");
        alert.setHeaderText(null);
        alert.setContentText("Item deleted.");
        alert.showAndWait();

        //refresh(event);
        // refrech();
    }

    @FXML
    private void Refresh(ActionEvent event) {
    }

    @FXML
    private void chercher(KeyEvent event) {
    }

    @FXML
    private void clickMousee(MouseEvent event) {
        int id = tablespareparts.getSelectionModel().getSelectedItems().get(0).getId();
        String Type = tablespareparts.getSelectionModel().getSelectedItems().get(0).getType();
        int Pou = tablespareparts.getSelectionModel().getSelectedItems().get(0).getPou();
        String Description = tablespareparts.getSelectionModel().getSelectedItems().get(0).getDescription();
        Double Price = tablespareparts.getSelectionModel().getSelectedItems().get(0).getPrice();
        String TypeC = tablespareparts.getSelectionModel().getSelectedItems().get(0).getTypec();
        this.text_main_id.setText(String.valueOf(id));
        this.text_main_type.setText(Type);
        this.text_main_pou.setText(String.valueOf(Pou));
        this.text_main_description.setText(Description);
        this.text_main_price.setText(String.valueOf(Price));
        this.text_main_typec.setText(TypeC);
    }

    @FXML
    private void GetSparePartsById(ActionEvent event) {

    }

}
