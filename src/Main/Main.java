/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.io.IOException;
import java.sql.*;
import Utils.DBconnexion;
import Dao.*;
// import java.util.logging.Level;
// import java.util.logging.Logger;
/**
 *
 * @author neil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        
    DBconnexion database = DBconnexion.getInstance();
Connection connection = database.getConnection();
ReservationDaoImplementation e= new ReservationDaoImplementation();
e.createReservation();

/*String  name="name";
    
        try {
            Statement stm = connection.createStatement();
            String query ="INSERT INTO employee (id, name) VALUES ( '1', 'name')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("failed");
        }*/
//first commit
        // TODO code application logic here
        
    }
    
}   
