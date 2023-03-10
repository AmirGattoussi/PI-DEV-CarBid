/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Entities.Command;
import Entities.SpareParts;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yasmine
 */
public class ServicesCommand {
    Statement ste;

    Connection cnx;

    public ServicesCommand() throws SQLException {
        cnx = (Connection) DBconnexion.getInstance().getConnection();
    }

    public List<Command> displaycommand() {
        List<Command> listpieces = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            String req_select = "SELECT * FROM `carbid`.`command`";
            ResultSet res = ste.executeQuery(req_select);
            while (res.next()) {
                int id_command = res.getInt(1);
                int id_user = res.getInt(2);
                  
                int id_sparepart = res.getInt(3);
                String date_cr=res.getString(4);
                 // int id_sparepart = res.getInt(2);
                   Command s = new Command(id_command,id_user,id_sparepart,date_cr);
                //Command s = new Command(id_user,id_sparepart);
              
                listpieces.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }

        return listpieces;
    }

    public void addcommand(Command u) throws SQLException {
        //PreparedStatement pre = cnx.prepareStatement("INSERT INTO `command`(`id_command`, `id_user`, `id_sparepart`) VALUES (?,?,?)");
PreparedStatement pre = cnx.prepareStatement("INSERT INTO `command`( `id_user`, `id_sparepart`,`date_cr`) VALUES (?,?,?)");
      // pre.setInt(1, u.getId_command());
       // pre.setInt(2, u.getId_user());
        //pre.setInt(3, u.getId_sparepart());
          pre.setInt(1, u.getId_user());
        pre.setInt(2, u.getId_sparepart());
         pre.setString(3, u.getDate_cr());
        
        pre.executeUpdate();
        /*
         * if ((text_live_id.getText().isEmpty()) &&
         * (!(txt_main_price.getText().isEmpty())) ) {
         * Alert alert = new Alert(AlertType.WARNING);
         * alert.setTitle("Invalid Input");
         * alert.setHeaderText(null);
         * alert.setContentText("Please enter only a live data.");
         * alert.showAndWait();}}
         */

    }

    public void deletecommand(int id) {

        try {
            PreparedStatement pre = cnx.prepareStatement("delete from command where id_command = ?");
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void modifycommand(Command u) {

        try {
            PreparedStatement pre = cnx.prepareStatement("Update command set id_user=?,id_sparepart=?,date_cr=? where id_command = ?");

            pre.setInt(4, u.getId_command());

         pre.setInt(1, u.getId_user());

          pre.setInt(2, u.getId_sparepart());
          pre.setString(3, u.getDate_cr());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public Command GetcommandById(int id) {

        try {

            PreparedStatement pre = cnx.prepareStatement("select * from command where id_command= ?");

            pre.setInt(1, id);
            ResultSet result = pre.executeQuery();
            while (result.next()) {

                Command u = new Command(result.getInt(1), result.getInt(2), result.getInt(3),result.getString(4));
                return u;
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    
    
    

}
