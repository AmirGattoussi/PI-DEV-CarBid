/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Api.MailApi;
import Entities.*;
import Entities.Auction;
import Entities.Bid;
import Entities.User;
import Services.AuctionDao;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class AuctionDaoImplementation implements AuctionDao {
    int i=0;
    Connection cnx;

    public AuctionDaoImplementation() {
        try {
            cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addAuction(Auction auction) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "INSERT INTO auction (startDate, endDate, startingPrice,highestBid,status,idCar) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setDate(1, auction.getStartDate());
            statement.setDate(2, auction.getEndDate());
            statement.setFloat(3, auction.getStartingPrice());
            statement.setFloat(4, auction.getHighestBid());
            statement.setString(5, auction.getStatus());
            statement.setInt(6, auction.getCarId());
            statement.executeUpdate();
            System.out.println("added successfully");

        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Auction getAuction(int id) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "SELECT * FROM auction WHERE idAuction = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Auction(
                        resultSet.getInt("idAuction"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate"),
                        resultSet.getFloat("startingPrice"),
                        resultSet.getFloat("highestBid"),
                        resultSet.getString("status"),
                        resultSet.getInt("idCar"));

            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void updateAuction(int id, float highestBid, String status, Date endDate) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "UPDATE auction SET  `highestBid` = ?, `status` = ?,endDate = ? WHERE `auction`.`idAuction` = ?;");
            statement.setFloat(1, highestBid);
            statement.setString(2, status);
            statement.setDate(3, endDate);
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println("updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAuction(int id) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "DELETE FROM auction WHERE idAuction = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("deleted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Float getHighestBidById(int idAuction) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "SELECT highestBid FROM auction WHERE auction.idAuction = ?");
            statement.setInt(1, idAuction);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                float floatValue = resultSet.getFloat("highestBid");
                return floatValue;
            } else {
                System.out.println("error id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Date getDeadline(int idAuction) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "SELECT endDate FROM auction WHERE auction.idAuction = ?");
            statement.setInt(1, idAuction);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Date deadline = resultSet.getDate("endDate");
                return deadline;
            } else {
                System.out.println("error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Auction> getAllAuctions() {
        List<Auction> data = new ArrayList<Auction>();
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement("SELECT * FROM auction");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new Auction(
                        resultSet.getInt("idAuction"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate"),
                        resultSet.getFloat("startingPrice"),
                        resultSet.getFloat("highestBid"),
                        resultSet.getString("status"),
                        resultSet.getInt("idCar")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    @Override
    public String getEmailWinner(int idUser) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "SELECT email FROM user u join auction a on u.user_id=a.  WHERE id_user = ?");
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                return email;
            } else {
                System.out.println("error in getting the email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void WinnerNotificationMail() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {

                try {
                    System.out.println("start ");
                    PreparedStatement statement = cnx.prepareStatement(
                            "SELECT idAuction,endDate,highestBid,status FROM auction");
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        Date deadline = resultSet.getDate("endDate");
                        int idAuc = resultSet.getInt("idAuction");
                        float highestBid = resultSet.getFloat("highestBid");
                        String status = resultSet.getString("status");
                        LocalDateTime now = LocalDateTime.now();
                        LocalDateTime deadl = resultSet.getTimestamp("endDate").toLocalDateTime();
                        Duration timeLeft = Duration.between(now, deadl);
                        long hours = timeLeft.toHours();
                        long minutes = timeLeft.toMinutes() % 60;
                        long seconds = timeLeft.getSeconds() % 60;
                        String remainingTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                        if (remainingTime.compareTo("00:00:00")<0 && status.equals("open")) {
                            System.out.println("entered");

                            try {
                                PreparedStatement statement1 = cnx.prepareStatement(
                                        "SELECT DISTINCT(email) FROM user u join bid b join auction a on u.id_user=b.userId and b.idAuction=a.idAuction where a.idAuction=? and a.highestBid=b.liveBidAmount");
                                PreparedStatement statement2 = cnx.prepareStatement(
                                        "update auction set status='closed' where idAuction=?");
                                statement1.setInt(1, idAuc);
                                System.out.println(idAuc);
                                statement2.setInt(1, idAuc);
                                // statement1.setFloat(2, highestBid);
                                ResultSet resultSet2 = statement1.executeQuery();
                                statement2.executeUpdate();
                                if (resultSet2.next()) {
                                    String winnerEmail = resultSet2.getString("email");
                                    System.out.println(winnerEmail);
                                    MailApi.sendMail(winnerEmail);

                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            i++;
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }, 0, 1, TimeUnit.MINUTES);

    }

    @Override
    public void IncrementBid(int id, int userId, float amount) {
        PreparedStatement statement;
        PreparedStatement statement1;
        try {
            statement = cnx.prepareStatement(
                    "UPDATE auction SET  `highestBid` = ? WHERE `auction`.`idAuction` = ?;");
            statement1 = cnx.prepareStatement(
                    "INSERT INTO bid (userId,idAuction, date,type,liveBidAmount,maxBidAmount) VALUES (?, ?, now(),'Live', ?,0)");
            PreparedStatement statement2 = cnx.prepareStatement(
                    "update auction set highestBid= ? WHERE auction.idAuction = ?");
            statement.setFloat(1, amount);
            statement.setInt(2, id);
            statement1.setInt(1, userId);
            statement1.setInt(2, id);
            statement1.setFloat(3, amount);
            statement.executeUpdate();
            statement1.executeUpdate();
            statement2.setFloat(1, amount);
            statement2.setInt(2, id);
            statement2.executeUpdate();
            System.out.println("updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void IncrementBidMax(int id, int userId, float liveAmount, float maxAmount) {
        try {
            PreparedStatement statement2 = cnx.prepareStatement(
                    "update auction set highestBid= ? WHERE auction.idAuction = ?");
            statement2.setFloat(1, liveAmount);
            statement2.setInt(2, id);
            statement2.executeUpdate();
            System.out.println("updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void closeAuction(int id) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "update auction set status='closed' WHERE auction.idAuction = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    
    }

    public int getIdAuctionByCar(int idCar) {
        PreparedStatement statement;
        int idAuction = 0;
        try {
            statement = cnx.prepareStatement("SELECT idAuction FROM auction WHERE idCar= ?");
            statement.setInt(1, idCar);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                idAuction = resultSet.getInt("idAuction");
            }
            else{
                System.err.println("Auction does not exist");}
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idAuction;

    }
}
