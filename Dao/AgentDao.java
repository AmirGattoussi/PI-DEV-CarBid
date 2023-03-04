/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Agent;
import Utils.*;
import Entities.Agent;
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
public class AgentDao {

    Connection cnx;

    public AgentDao() {
        try {
            cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AgentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createAgent(Agent agent) {
        PreparedStatement statement;
        try {
            //Create withoud agent ID
            statement = cnx.prepareStatement(
                    "INSERT INTO user (name, email, password, phone_number,location) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, agent.getName());
            statement.setString(2, agent.getEmail());
            statement.setString(3, agent.getPassword());
            statement.setInt(4, agent.getPhone_number());
            statement.setString(5, agent.getLocation());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgentDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        int id_agent;
        PreparedStatement statement2;
        PreparedStatement statement3;

        try {
            //Get Id_agent from Id_User
            statement2 = cnx.prepareStatement(
                    "SELECT id_user FROM user WHERE name = ? AND email = ? AND password = ? AND phone_number = ? AND location = ?");
            statement2.setString(1, agent.getName());
            statement2.setString(2, agent.getEmail());
            statement2.setString(3, agent.getPassword());
            statement2.setInt(4, agent.getPhone_number());
            statement2.setString(5, agent.getLocation());
            ResultSet resultSet = statement2.executeQuery();
            //Set ID User to ID Agent
            if (resultSet.next()) {
                id_agent = resultSet.getInt("id_user");
                System.out.println(id_agent);
                statement3 = cnx.prepareStatement(
                        "UPDATE user SET id_agent = ? WHERE id_user = ?");
                statement3.setInt(1, id_agent);
                statement3.setInt(2, id_agent);
                statement3.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
