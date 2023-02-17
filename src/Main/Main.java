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
import Entities.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gtsia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
        Connection cnx=DBconnexion.getInstance().getConnection();
    
      /*UserDao userDao;
        User amir = new User (1,"amir","Gattoussi","pass");
        userDao = new UserDao();
        userDao.createUser(amir);*/
    }

}
