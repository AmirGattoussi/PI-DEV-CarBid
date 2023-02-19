/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *
 * @author gtsia
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Entities.*;
import Services.IUserDao;
import Utils.DBconnexion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements IUserDao {
    java.sql.Connection cnx;

    // Connexion
    public UserDao() throws SQLException {
        this.cnx = DBconnexion.getInstance().getConnection();
    }
    // CREATE operation

    @Override
    public void createUser(User user) {
        PreparedStatement statement;
        try {

            statement = cnx.prepareStatement(
                    "INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // READ operation
    @Override
    public User getUserById(int id_user) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "SELECT * FROM user WHERE id_user = ?");
            statement.setInt(1, id_user);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public User getUserByName(String name) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "SELECT * FROM user WHERE name = ?");
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    // UPDATE operation
    @Override
    public void updateUser(User user) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "UPDATE user SET name = ?, email = ?, password = ? WHERE id_user = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // DELETE operation
    public void deleteUser(int id_user) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "DELETE FROM user WHERE id_user = ?");
            statement.setInt(1, id_user);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
