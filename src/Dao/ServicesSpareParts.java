/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

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
 *         public class ReservationDao implements IReservationDao{
 * 
 *         Connection cnx;
 * 
 *         public ReservationDao()throws SQLException {
 *         cnx = DBconnexion.getInstance().getConnection();
 *         }
 */
public class ServicesSpareParts {
    Connection connection;
    Statement ste;

    Connection cnx;

    public ServicesSpareParts() throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    public ArrayList<SpareParts> display() {
        ArrayList<SpareParts> listpieces = new ArrayList<>();
        try {
            ste = connection.createStatement();
            String req_select = "SELECT * FROM `pidev`.`spareparts`";
            ResultSet res = ste.executeQuery(req_select);
            while (res.next()) {
                int id_sparepart = res.getInt(1);
                String Type = res.getString(2);
                int Pou = res.getInt(3);
                String Description = res.getString(4);
                double Price = res.getDouble(5);
                String Typec = res.getString(6);
                // SpareParts s = new SpareParts(Id,Type,Pou,Description,Price,Typec);
                SpareParts s = new SpareParts(id_sparepart, Type, Pou, Description, Price, Typec);
                listpieces.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }

        return listpieces;
    }

    public void add(SpareParts u) throws SQLException {
        PreparedStatement pre = connection.prepareStatement(
                "INSERT INTO `carbid`.`spareparts` (`id_sparepart`,`Type`,`Pou`,`Description`,`Price`,`Typec`) VALUES (?,?,?,?,?,?)");

        pre.setInt(1, u.getId());
        pre.setString(2, u.getType());
        pre.setInt(3, u.getPou());
        pre.setString(4, u.getDescription());
        pre.setDouble(5, u.getPrice());

        pre.setString(6, u.getTypec());

        pre.executeUpdate();

    }

    public void delete(int id) {

        try {
            PreparedStatement pre = connection.prepareStatement("delete from carbid where id_sparepart = ?");
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void modify(SpareParts u) {

        try {
            PreparedStatement pre = connection.prepareStatement(
                    "Update spareparts set Type=?,Pou=?,Description=?,Price=?,Typec=? where id_sparepart = ?");

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

            PreparedStatement pre = connection.prepareStatement("select * from carbid where id_sparepart = ?");

            pre.setInt(1, id);
            ResultSet result = pre.executeQuery();
            while (result.next()) {

                SpareParts u = new SpareParts(result.getInt(1), result.getString(2), result.getInt(3),
                        result.getString(4), result.getDouble(5), result.getString(6));
                return u;
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }

}
