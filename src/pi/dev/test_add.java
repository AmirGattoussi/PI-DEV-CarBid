/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gtsia
 */
public class test_add {
      public ServiceAbonne() {
        cnx = MaConnexion.getInstance().getConnection();
    }
    @Override
    public void AddAbonne() {
        name="name";
    }
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO `employee`( `id`, `name`) VALUES ('" + 2 + "','" + name"')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*@Override
     public void ModifierAbonne(Abonne a) throws SQLException
    { 
          PreparedStatement pst = cnx.prepareStatement(" update abonne set nomabonne = ? where nomabonne=?");
                  
           
           pst.executeUpdate();
    }
    
    
    
}
