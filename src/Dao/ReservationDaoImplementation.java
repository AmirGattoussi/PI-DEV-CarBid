/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Reservation;
import java.sql.*;
import Services.*;
import Utils.DBconnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author neil
 */
public class ReservationDaoImplementation implements ReservationDao{

    Connection cnx;

    public ReservationDaoImplementation()throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    @Override
    public void createReservation(){
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO reservation(date, location, user, car, agent) VALUES('14-February-2023', 'Ariana', 'Amir', 'Ford', 'Samir')";
            stm.executeUpdate(query);
        }  catch (SQLException ex) {
            Logger.getLogger(ReservationDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation getReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    /*@Override
    public void ModifierAbonne(Abonne a) throws SQLException
    { 
        PreparedStatement pst = cnx.prepareStatement(" update abonne set nomabonne = ? where nomabonne=?");

        pst.executeUpdate();
    }
    
    
    
}*/
