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
import Utils.DBconnexion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
    java.sql.Connection cnx;

    private Connection connection;

    public UserDao() throws SQLException {
        this.cnx = DBconnexion.getInstance().getConnection();
    }


    // CREATE operation
    
    public void createUser(User user)  {
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
    public User getUserById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE id = ?");

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }

        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }

        return users;
    }

    // UPDATE operation
    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?");

        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getId());

        statement.executeUpdate();
    }

    // DELETE operation
    public void deleteUser(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?");

        statement.setInt(1, id);

        statement.executeUpdate();
    }
}

