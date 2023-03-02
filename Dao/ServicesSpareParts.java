/*
 * //Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * //Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
//import Entities.SpareParts;
//import Entities.*;
//import Entities.SpareParts;
//import java.sql.*;
//import Services.*;
//import Utils.DBconnexion;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import Utils.*;

import Entities.SpareParts;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import Services.InterfaceSpareParts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DBconnexion;

/*
import entities.SpareParts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.InterfaceSpareParts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyConnection;
 */
/**
 *
 * @author Yasmine
 *
 * public class ReservationDao implements IReservationDao{
 *
 * Connection cnx;
 *
 * public ReservationDao()throws SQLException { cnx =
 * DBconnexion.getInstance().getConnection(); }
 */
public class ServicesSpareParts {

    Statement ste;

    Connection cnx;

    public ServicesSpareParts() throws SQLException {
        cnx = (Connection) DBconnexion.getInstance().getConnection();
    }

    public List<SpareParts> display() {
        List<SpareParts> listpieces = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            String req_select = "SELECT * FROM `carbid`.`spareparts`";
            ResultSet res = ste.executeQuery(req_select);
            while (res.next()) {
                int Id = res.getInt(1);
                String Type = res.getString(2);
                int Pou = res.getInt(3);
                String Description = res.getString(4);
                double Price = res.getDouble(5);
                String Typec = res.getString(6);
                //   SpareParts s = new SpareParts(Id,Type,Pou,Description,Price,Typec);
                SpareParts s = new SpareParts(Id, Type, Pou, Description, Price, Typec);
                listpieces.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }

        return listpieces;
    }

    public void add(SpareParts u) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("INSERT INTO `spareparts`(`Type`, `Pou`, `Description`, `Price`, `Typec`) VALUES (?,?,?,?,?)");

     //   pre.setInt(1, u.getId());
        pre.setString(1, u.getType());
        pre.setInt(2, u.getPou());
        pre.setString(3, u.getDescription());
        pre.setDouble(4, u.getPrice());

        pre.setString(5, u.getTypec());

        pre.executeUpdate();
        /*
           if ((text_live_id.getText().isEmpty()) && (!(txt_main_price.getText().isEmpty())) ) {
          Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter only a live data.");
                alert.showAndWait();}}
         */

    }

    public void delete(int id) {

        try {
            PreparedStatement pre = cnx.prepareStatement("delete from spareparts where id_sparepart = ?");
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void modify(SpareParts u) {

        try {
            PreparedStatement pre = cnx.prepareStatement("Update spareparts set Type=?,Pou=?,Description=?,Price=?,Typec=? where id_sparepart = ?");

            pre.setInt(6, u.getId());

            pre.setString(1, u.getType());

            pre.setInt(2, u.getPou());

            pre.setString(3, u.getDescription());

            pre.setDouble(4, u.getPrice());

            pre.setString(5, u.getTypec());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public SpareParts GetSparePartsById(int id) {

        try {

            PreparedStatement pre = cnx.prepareStatement("select * from spareparts where id_sparepart = ?");
//PreparedStatement pre = cnx.prepareStatement("select * from spareparts where pou = ?");
            pre.setInt(1, id);
             // pre.setInt(3,pou );
            ResultSet result = pre.executeQuery();
            while (result.next()) {

                SpareParts u = new SpareParts(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getDouble(5), result.getString(6));
                return u;
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }

}
