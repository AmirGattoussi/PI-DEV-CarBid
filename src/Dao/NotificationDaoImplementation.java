/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Notification;
import Services.NotificationDao;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class NotificationDaoImplementation implements NotificationDao {

    Connection cnx;

    public NotificationDaoImplementation() {
        try {
            cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> getAllNotifications(int idUser) {
        List<Notification> data = new ArrayList<Notification>();
        PreparedStatement statement;
        PreparedStatement statement1;
        try {
            statement = cnx.prepareStatement("SELECT * FROM notification where status ='not read' and userId=?");
            //statement1 = cnx.prepareStatement("UPDATE `notification` SET `status`='read',`isSent`='false' WHERE userId=?");
            statement.setInt(1, idUser);
            //statement1.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            //ResultSet resultSet1 = statement1.executeQuery();
            while (resultSet.next()) {
                data.add(new Notification(
                        resultSet.getInt("idNotif"),
                        resultSet.getInt("userId"),
                        resultSet.getString("content"),
                        resultSet.getString("status"),
                        resultSet.getString("isSent"))
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    @Override
    public int sendNotification(Notification notification) {
        PreparedStatement queryStatement;
        try {
            queryStatement = cnx.prepareStatement(
                    "SELECT idNotif FROM notification WHERE userId = ? AND content = ? AND isSent = false");
            queryStatement.setInt(1, notification.getUserId());
            queryStatement.setString(2, notification.getContent());
            ResultSet resultSet = queryStatement.executeQuery();
            if (resultSet.next()) {
                return 0;
            }
            else{
        try {
            PreparedStatement statement;
            statement = cnx.prepareStatement(
                    "INSERT INTO notification (userId, content, status,isSent) VALUES (?, ?,'not read','true')", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, notification.getUserId());
            statement.setString(2, notification.getContent());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    PreparedStatement updateStmt = cnx.prepareStatement(
                    "UPDATE notification SET isSent='false' WHERE idNotif=?");
                    updateStmt.setInt(1, id);
                    updateStmt.executeUpdate();
                    return id;
                } else {
                    throw new SQLException("Error.");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(NotificationDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        }catch (SQLException ex) {
            Logger.getLogger(NotificationDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void updateNotification(int userId) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement("UPDATE `notification` SET `status`='read' WHERE userId=?");
            statement.setInt(1, userId);
            statement.executeUpdate();   
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public int getNumberOfNotifications() {
        int count = 0;
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement("SELECT COUNT(*) FROM NOTIFICATION");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
