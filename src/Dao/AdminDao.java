/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Services.IAdminDao;
import Utils.*;
import Entities.Admin;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gtsia
 */
public class AdminDao implements IAdminDao{
    Connection cnx;

    public AdminDao()throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    @Override
    
       public void createAdmin(Admin admin) throws SQLException {
        PreparedStatement statement = cnx.prepareStatement(
                "INSERT INTO users (name, email, password) VALUES (?, ?, ?)");

        statement.setString(1, admin.getName());
        statement.setString(2, admin.getEmail());
        statement.setString(3, admin.getPassword());

        statement.executeUpdate();
    }
 //To change body of generated methods, choose Tools | Templates.

    @Override
    public void deleteAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> view_users() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> sortUsers_byUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> searchUser_byUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void suspend_user() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
