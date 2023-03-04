/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.*;

public class DBconnexion {
  
  private static DBconnexion instance;
  private Connection connection;
  private String url = "jdbc:mysql://localhost:3306/carbid";
  private String username = "root";
  private String password = "";

  private DBconnexion() throws SQLException {
     try {
      this.connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connextion etablie !");

     }
     catch (SQLException ex) {
            System.out.println("Erreur de connextion");
            System.out.println(ex.getMessage());
        }
     
    
  }

  public Connection getConnection() {
    return connection;
  }

  public static DBconnexion getInstance() throws SQLException {
    if (instance == null) {
      instance = new DBconnexion();
    } else if (instance.getConnection().isClosed()) {
      instance = new DBconnexion();
    }

    return instance;
  }
}