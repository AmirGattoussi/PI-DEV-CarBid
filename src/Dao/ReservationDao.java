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
public class ReservationDao implements IReservationDao{

    Connection cnx;

    public ReservationDao()throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    @Override
    public void createReservation(Reservation reservation){
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement("INSERT INTO reservation (id_user, id_car, date, location, id_agent) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, reservation.getUser());
            statement.setInt(2, reservation.getCar());
            statement.setDate(3, (Date)reservation.getDate());
            statement.setString(4, reservation.getLocation());
            statement.setInt(5, reservation.getAgent());
            
            statement.executeUpdate();
        }  catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
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
