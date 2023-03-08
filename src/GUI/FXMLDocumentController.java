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
import Utils.PdfAPI;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button btn_main_rech;
    @FXML
    private TextField tfNom;
    @FXML
    private Button btn_pdf;
    @FXML
    private Button btn_states;

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
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Filter Par type et Par typec
            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<SpareParts> filteredData = new FilteredList<>(list, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(spareparts -> {
                    // If filter text is empty, display all spareparts.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (spareparts.getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches type .
                    } else if (spareparts.getTypec().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches typec.
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<SpareParts> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tablespareparts.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tablespareparts.setItems(sortedData);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } catch (SQLException e) {
        }

    }

    @FXML
    private void add(ActionEvent event) {

        try {
            System.out.println("Bontton Ajouter Lu");
            // if ((text_main_type.getText().isEmpty()) || (text_main_typec.getText().isEmpty()) || (text_main_id.getText().isEmpty()) || (text_main_pou.getText().isEmpty()) || (text_main_description.getText().isEmpty()) || (text_main_price.getText().isEmpty())) {
            if ((text_main_type.getText().isEmpty()) || (text_main_typec.getText().isEmpty()) || (text_main_pou.getText().isEmpty()) || (text_main_description.getText().isEmpty()) || (text_main_price.getText().isEmpty())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter data.");
                alert.showAndWait();
            } else {
                ServicesSpareParts us = new ServicesSpareParts();
                // Integer id = Integer.parseInt(text_main_id.getText());
                String type = text_main_type.getText();
                Integer pou = Integer.parseInt(text_main_pou.getText());
                String description = text_main_description.getText();
                Double price = Double.parseDouble(text_main_price.getText());
                String typec = text_main_typec.getText();

                // SpareParts u = new SpareParts(id, type, pou, description, price, typec);
                SpareParts u = new SpareParts(type, pou, description, price, typec);
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
    private void display(ActionEvent event) throws SQLException {

        SpareParts uselected = (SpareParts) tablespareparts.getSelectionModel().getSelectedItem();
        ServicesSpareParts us = new ServicesSpareParts();
        //  InformationsSupplementairesService InfosService = new InformationsSupplementairesService();
        int id = uselected.getId();
        String type = uselected.getType();
        int pou = uselected.getPou();
        String description = uselected.getDescription();
        Double price = uselected.getPrice();
        String typec = uselected.getTypec();
        //  User u1 = us.ChercherParId(id);
        // InformationsSupplementaires inf1 = InfosService.chercherparid(id);
        //    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        //  Parent root =loader.load();
        // AfficherUser1Controller ctrl = loader.getController();
        System.out.println("Controller yeess");
        //   ctrl.MyFunction(uselected.getNom(),uselected.getPrenom(),inf1.getTell(),uselected.getMail(),uselected.getPassword(),uselected.getRole());

        //Scene scene =new Scene(root);
        //Stage stage =new Stage();
        //stage.setScene(scene);
        //stage.show();
        //} catch (IOException ex) {
        // Logger.getLogger(display().class.getName()).log(Level.SEVERE, null, ex);
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
        //refresh(event);

        // refrech();
    }

    @FXML
    private void Refresh(ActionEvent event) throws SQLException {
        //

        System.out.println("Bontton refresh Lu");
        ServicesSpareParts us = new ServicesSpareParts();
        ObservableList<SpareParts> list = FXCollections.observableArrayList();
        id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
        type_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
        pou_tab.setCellValueFactory(new PropertyValueFactory<>("pou"));
        description_tab.setCellValueFactory(new PropertyValueFactory<>("description"));
        price_tab.setCellValueFactory(new PropertyValueFactory<>("price"));
        typec_tab.setCellValueFactory(new PropertyValueFactory<>("typec"));

        list.addAll(us.display());

        tablespareparts.setItems(list);

        // 
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
    private void GetSparePartsById(ActionEvent event) throws SQLException {

        System.out.println("chercher ");
        ServicesSpareParts us = new ServicesSpareParts();
        ObservableList<SpareParts> liste = FXCollections.observableArrayList();

        liste.addAll(us.GetSparePartsById(Integer.parseInt(text_main_id.getText())));
//liste.addAll(us.GetSparePartsById(Integer.parseInt(text_main_pou.getText())));
        tablespareparts.setItems(liste);

        System.out.print("list value \n " + liste);

    }

    @FXML
    private void GeneratePdf(ActionEvent event) throws SQLException,FileNotFoundException, DocumentException, IOException {
        PdfAPI pdf = new PdfAPI();

        try {
            pdf.listAllSpareParts();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void calculerNombreParType() throws SQLException {

        int NbrMoteur = 0;
        int NbrDécorvolan = 0;
        /*
         private int id_sparepart;
    private String type;
    private int pou;
    private String description;
    private double price;
    private String typec;
        */

        ServicesSpareParts us = new ServicesSpareParts();
       ObservableList<SpareParts> list = FXCollections.observableArrayList();
            id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
            type_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
            pou_tab.setCellValueFactory(new PropertyValueFactory<>("pou"));
            description_tab.setCellValueFactory(new PropertyValueFactory<>("description"));
            price_tab.setCellValueFactory(new PropertyValueFactory<>("price"));
            typec_tab.setCellValueFactory(new PropertyValueFactory<>("typec"));
              list.addAll(us.display());
            tablespareparts.setItems(list);
      
        for (SpareParts sp : list) {
            if ("moteur".equals(sp.getType())) {
                NbrMoteur += 1;

            }
            if ("décor volan".equals(sp.getType())) {
                NbrDécorvolan += 1;
            }
        }
        System.out.println(NbrMoteur);
        System.out.println(NbrDécorvolan);

    }

    @FXML
    private void showstates(ActionEvent event) {
        try {
            //taawed thezzek lel inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statesSpareParts.fxml"));
            Parent root = loader.load();
            btn_states.getScene().setRoot(root);
           
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}
