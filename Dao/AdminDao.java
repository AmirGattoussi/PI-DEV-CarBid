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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gtsia
 */
public class AdminDao implements IAdminDao {

    Connection cnx;

    public AdminDao() {
        try {
            cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override

    public void createAdmin(Admin admin) {
        PreparedStatement statement;
        try {
            //Create withoud admin ID
            statement = cnx.prepareStatement(
                    "INSERT INTO user (name, email, password, phone_number,location) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getPassword());
            statement.setInt(4, admin.getPhone_number());
            statement.setString(5, admin.getLocation());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        int id_admin;
        PreparedStatement statement2;
        PreparedStatement statement3;

        try {
            //Get Id_admin from Id_User
            statement2 = cnx.prepareStatement(
                    "SELECT id_user FROM user WHERE name = ? AND email = ? AND password = ? AND phone_number = ? AND location = ?");
            statement2.setString(1, admin.getName());
            statement2.setString(2, admin.getEmail());
            statement2.setString(3, admin.getPassword());
            statement2.setInt(4, admin.getPhone_number());
            statement2.setString(5, admin.getLocation());
            ResultSet resultSet = statement2.executeQuery();
            //Set ID User to ID Admin
            if (resultSet.next()) {
                id_admin = resultSet.getInt("id_user");
                System.out.println(id_admin);
                statement3 = cnx.prepareStatement(
                        "UPDATE user SET id_admin = ? WHERE id_user = ?");
                statement3.setInt(1, id_admin);
                statement3.setInt(2, id_admin);
                statement3.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //To change body of generated methods, choose Tools | Templates.

    @Override
    public List<User> view_users() {
        List<User> users = new ArrayList<>();

        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "SELECT * FROM user");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("location")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;

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

    @Override
    public void deleteAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
