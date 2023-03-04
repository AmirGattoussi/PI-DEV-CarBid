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
// import java.util.ArrayList;
// import java.util.List;
import Entities.*;
import Services.IUserDao;
import Utils.DBconnexion;
import Utils.PasswordHasher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements IUserDao {

    java.sql.Connection cnx;
    private static UserDao instance;

    // Connexion
    public UserDao() {
        try {
            this.cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // CREATE operation

    @Override
    public void createUser(User user) {
        PreparedStatement statement;
        try {

            statement = cnx.prepareStatement(
                    "INSERT INTO user (name, email, password, phone_number,location) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, PasswordHasher.hash(user.getPassword()));
            statement.setInt(4, user.getPhone_number());
            statement.setString(5, user.getLocation());

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
                        resultSet.getString("password"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("location")
                );
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
                        resultSet.getString("password"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("location")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public User getUserByMail(String email) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "SELECT * FROM user WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id_user"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("location")
                );
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
                    "UPDATE user SET name = ?, email = ?, password = ?, phone_number=?, location= ? WHERE id_user = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getPhone_number());
            statement.setString(5, user.getLocation());

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

    public boolean login(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // prepare a statement to query the database for a user with the given username and password
            statement = cnx.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            // execute the query and check if the result set contains any rows
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // handle any exceptions that occur while querying the database
            e.printStackTrace();
            return false;

        }
    }

    public boolean resetPassword(String email, String newPassword) {
        PreparedStatement stmt = null;
        try {
            // Prepare SQL statement to update password
            stmt = cnx.prepareStatement("UPDATE user SET password=? WHERE email=?");

            stmt.setString(1, PasswordHasher.hash(newPassword));
            System.out.println(PasswordHasher.hash(newPassword));
            stmt.setString(2, email);

            // Execute SQL statement and check if any rows were affected
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                return true; // Password reset successfully
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
            }
        }
        return false; // Password reset failed

    }

    public int getUserIdAtLogin(String email) {
        PreparedStatement statement;
        int loggedInID = 0;
        try {
            statement = cnx.prepareStatement(
                    "SELECT id_user FROM user WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                loggedInID = resultSet.getInt("id_user");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loggedInID;

    }

    public boolean doesUserExist(String email) {
        try {

            // Prepare SQL statement
            String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, email);

            // Execute SQL query and get result
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            // Close database connection and statement
            resultSet.close();
            statement.close();

            // Return true if user exists, false otherwise
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getNumberOfUsers() 
    {
        int count = 0;
        PreparedStatement statement;

        try {
            statement = cnx.prepareStatement("SELECT COUNT(*) FROM reservation");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
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
}
