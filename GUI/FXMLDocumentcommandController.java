/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Dao.ServicesCommand;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import Entities.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class FXMLDocumentcommandController implements Initializable {

    
    @FXML
    private TextField text_cmd_id_command;
    @FXML
    private TextField text_cmd_id_user;
    @FXML
    private TextField text_cmd_id_sparepart;
    
    @FXML
    private TableView<Command> tablecommand;
    /*
    @FXML
    private Button btn_main_add;
    @FXML
    private Button btn_main_modify;
    @FXML
    private Button btn_main_display;
    @FXML
    private Button btn_main_delete;
    */
    /*@FXML
    private TableColumn<?, ?> id_tab_cmd;
   
    @FXML
    private Button btn_main_refresh;
    @FXML
    private TextField look_for_sp;
    */
    @FXML
    private TableColumn<Command, Integer> id_user_tab_cmd;
    @FXML
    private TableColumn<Command, Integer> id_tab_cmd;
    @FXML
    private TableColumn<Command, Integer> id_sparepart_tab_cmd;
    @FXML
    private Button btn_main_add_cmd;
    @FXML
    private Button btn_main_modify_cmd;
    @FXML
    private Button btn_main_display_cmd;
    @FXML
    private Button btn_main_delete_cmd;
    /*@FXML
    private Button btn_main_display_cmd1;
    */
    @FXML
    private TableColumn<Command,String> date_cr_tab_cmd;
    @FXML
    private TextField text_cmd_date_cr;
    
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {

            ServicesCommand us = new ServicesCommand();
            List<Command> splist = us.displaycommand();
            ObservableList<Command> list = FXCollections.observableArrayList(splist);
            id_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("id_command"));
           id_user_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            id_sparepart_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("id_sparepart"));
           date_cr_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
           tablecommand.setItems(list);
           // list.addAll(us.displaycommand());
            System.out.println(list);
            //tablecommand.setItems(list);
           // tablecommand.setVisible(true);//baad ma 3ammart el lista bich t7otha fil tableau fil graphique
           // tablecommand.setEditable(true);//t9olo les element fil tableau etidable wella la!,
            // password.setCellFactory(TextFieldTableCell.forTableColumn());
            //email.setCellFactory(TextFieldTableCell.forTableColumn());

            /*ObservableList selectedCells = tablecommand.getSelectionModel().getSelectedCells();
            selectedCells.addListener(new ListChangeListener() {
                @Override
                public void onChanged(ListChangeListener.Change c) {
                    Command uselected = (Command) tablecommand.getSelectionModel().getSelectedItem();//wa9teli t7ot el sourie 3ala ligne fil tableau (wdli hiya el user )selectionne
                    System.out.println("selected value " + uselected);

                }

            });
*/
       } catch (SQLException e) {
        }

        // TODO
    }    





    @FXML
    private void clickMousee(MouseEvent event) {
    }
/*
    @FXML
    private void Refresh(ActionEvent event) throws SQLException {
        
        System.out.println("Bontton refresh Lu");
        ServicesCommand us = new ServicesCommand();
        ObservableList<User> list = FSlections.observableArrayList();
        id_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("id_command"));
           id_user_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            id_sparepart_tab_cmd.setCellValueFactory(new PropertyValueFactory<>("id_sparepart"));
                                              
        list.addAll(us.displaycommand());

        tablecommand.setItems(list);
        
        
    }

   */
    @FXML
    private void addcommand(ActionEvent event) {
        
        try {
            System.out.println("Bontton Ajouter Lu");
            ServicesCommand us = new ServicesCommand();
          //  Integer id_cmd = Integer.parseInt(text_cmd_id_command.getText());
         Integer id_user = Integer.parseInt(text_cmd_id_user.getText());
         Integer id_spt = Integer.parseInt(text_cmd_id_sparepart.getText());
       //  String date_cr=String.parseString(text_cmd_date_cr.getText());
           String date_cr=text_cmd_date_cr.getText();
              /*if ((type.isEmpty()) && !(typec.isEmpty()) ) {
          Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter  data.");
                alert.showAndWait();}
*/
           // Command u = new Command(id_cmd,id_user,id_spt);
          Command u = new Command(id_user,id_spt,date_cr);
            System.out.println(u);
            us.addcommand(u);
            //refresh(event);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentcommandController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }

    @FXML
    private void modifycommand(ActionEvent event)throws SQLException {
        
        ServicesCommand sc = new ServicesCommand();

          Integer id_cmd = Integer.parseInt(text_cmd_id_command.getText());
         Integer id_user = Integer.parseInt(text_cmd_id_user.getText());
         Integer id_spt = Integer.parseInt(text_cmd_id_sparepart.getText());
          String date_cr=text_cmd_date_cr.getText();
           
        
        Command c = new Command(id_cmd, id_user,id_spt,date_cr);
        sc.modifycommand(c);
        
        
    }

    @FXML
    private void displaycommand(ActionEvent event) {
        
    }

    @FXML
    private void deletecommand(ActionEvent event) throws SQLException {
        
         //bich t3ayat lel service bich ifas5 el user selectionne 3abra el boutton supprimer (ActionEvent )
        System.out.println("Bontton supprimer Lu");
        ServicesCommand us = new ServicesCommand();
        try {
            us = new ServicesCommand();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentcommandController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer idu = Integer.parseInt(text_cmd_id_command.getText());
        //inti hne keteb int idu fi wistha Ineteger hedheka alech howa ma9rach el button
        //ena tw raditha Integer idu bich mchetli ou maadesh t7ot el int idu
        us.deletecommand(idu);
        //refresh(event);

        // refrech();

    }

    @FXML
    private void clickMouseec(MouseEvent event) {
        
        int id_cmd = tablecommand.getSelectionModel().getSelectedItems().get(0).getId_command();
      int id_user = tablecommand.getSelectionModel().getSelectedItems().get(0).getId_user();
        int id_sp = tablecommand.getSelectionModel().getSelectedItems().get(0).getId_sparepart();
       
        this.text_cmd_id_command.setText(String.valueOf(id_cmd));
        this.text_cmd_id_user.setText(String.valueOf(id_user));
        this.text_cmd_id_sparepart.setText(String.valueOf(id_sp));
        
        
        
    }
    
}
