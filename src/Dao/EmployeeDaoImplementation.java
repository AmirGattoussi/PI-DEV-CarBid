/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.User;
import java.sql.*;
import Services.*;
import Utils.DBconnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gtsia
 */
public class EmployeeDaoImplementation {
      
    Connection cnx;

    public EmployeeDaoImplementation()throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }
    
    public void addEmployee(){
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO employee (id, name) VALUES ( '2', 'yosr')";
            stm.executeUpdate(query);
        }  catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void updateEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getEmployee() {
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
