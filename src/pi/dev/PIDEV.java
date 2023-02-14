/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;
import java.io.IOException;
import java.sql.*;
import Utils.DBconnexion;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gtsia
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        
      DBconnexion database = DBconnexion.getInstance();
Connection connection = database.getConnection();

String  name="name";
    
        try {
            Statement stm = connection.createStatement();
            String query ="INSERT INTO employee (id, name) VALUES ( '1', 'name')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("failed");
        }

        // TODO code application logic here
        
    }
    
}   
