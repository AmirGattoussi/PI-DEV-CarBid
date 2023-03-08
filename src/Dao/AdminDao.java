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
import java.util.Collections;
import java.util.Comparator;
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
            // Create withoud admin ID
            statement = cnx.prepareStatement(
                    "INSERT INTO user (name, email, password, phone_number,location) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, PasswordHasher.hash(admin.getPassword()));
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
            // Get Id_admin from Id_User
            statement2 = cnx.prepareStatement(
                    "SELECT id_user FROM user WHERE name = ? AND email = ? AND phone_number = ? AND location = ?");
            statement2.setString(1, admin.getName());
            statement2.setString(2, admin.getEmail());
            statement2.setInt(3, admin.getPhone_number());
            statement2.setString(4, admin.getLocation());
            ResultSet resultSet = statement2.executeQuery();
            // Set ID User to ID Admin
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
    // To change body of generated methods, choose Tools | Templates.

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
                        resultSet.getString("location")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;

    }

    @Override
    public List<User> sortUsers_byUsername() {
        List<User> userList = view_users();
        Collections.sort(userList, (User u1, User u2) -> u1.getName().compareTo(u2.getName()));
        return userList;
    }

    @Override
    public List<User> searchUser_byUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public void suspend_user() {

    }

    @Override
    public void deleteAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public void updateAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    public boolean isAdmin(int userId) {

        try {
            // create a PreparedStatement to execute a query
            PreparedStatement statement = cnx.prepareStatement("SELECT id_admin FROM user WHERE id_user = ?");
            statement.setInt(1, userId);

            // execute the query and retrieve the results
            ResultSet resultSet = statement.executeQuery();

            // if the result set contains a non-null value for admin_id, the user is an
            // admin
            if (resultSet.next()) {
                int adminId = resultSet.getInt("id_admin");
                return (adminId != 0);
            }

            // if the result set is empty, the user is not found in the database
            // throw an exception to indicate this
            throw new SQLException("User not found in database");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
