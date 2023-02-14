/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;
import java.io.IOException;
import java.sql.*;
import Utils.DBconnexion;
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

        // TODO code application logic here
        
    }
    //iwashere
}
