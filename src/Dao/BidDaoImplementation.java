/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.*;
import Entities.Bid;
import Services.BidDao;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class BidDaoImplementation implements BidDao {

    Connection cnx;

    public BidDaoImplementation() throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    @Override
    public void addLiveBid(Bid bid) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "INSERT INTO bid (userId,idAuction, date,type,liveBidAmount,maxBidAmount) VALUES (?, ?, now(),'Live', ?,0)");

            PreparedStatement statement2 = cnx.prepareStatement(
                    "update auction set highestBid= ? WHERE auction.idAuction = ?");

            statement.setInt(1, bid.getUserId());
            statement.setInt(2, bid.getIdAuction());
            statement.setFloat(3, bid.getLiveBidAmount());
            statement2.setFloat(1, bid.getLiveBidAmount());
            statement2.setInt(2, bid.getIdAuction());

            statement.executeUpdate();
            statement2.executeUpdate();
            System.out.println("added successfully");
        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void addMaxBid(Bid bid) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "INSERT INTO bid (userId,idAuction,date,type,liveBidAmount,maxBidAmount) VALUES (?, ?, now(),'Max', ?, ?)");
            PreparedStatement statement2 = cnx.prepareStatement(
                    "update auction set highestBid= ? WHERE auction.idAuction = ?");
            statement.setInt(1, bid.getUserId());
            statement.setInt(2, bid.getIdAuction());
            statement.setFloat(3, bid.getLiveBidAmount());
            statement.setFloat(4, bid.getMaxBidAmount());
            statement2.setFloat(1, bid.getLiveBidAmount());
            statement2.setInt(2, bid.getIdAuction());
            statement.executeUpdate();
            statement2.executeUpdate();
            System.out.println("added successfully");
        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Bid getBid(int id) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "SELECT * FROM bid WHERE idBid = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Bid(
                        resultSet.getInt("idBid"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("idAuction"),
                        resultSet.getDate("date"),
                        resultSet.getString("type"),
                        resultSet.getFloat("liveBidAmount"),
                        resultSet.getFloat("maxBidAmount"));
            }

            return null;

        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Uncessary update
    @Override
    public void updateBid(int idBid, Date date, String type, float liveBidAmount, float maxBidAmount) {
        try {
            PreparedStatement statement = cnx.prepareStatement(
                    "UPDATE bid SET  date= ?, type = ?,liveBidAmount= ?, maxBidAmount = ? WHERE idBid = ?");
            statement.setDate(1, date);
            statement.setString(2, type);
            statement.setFloat(3, liveBidAmount);
            statement.setFloat(4, maxBidAmount);
            statement.setInt(5, idBid);
            statement.executeUpdate();
            System.out.println("updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteBid(int id) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "DELETE FROM bid WHERE idBid = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("deleted successfully");

        } catch (SQLException ex) {
            Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Bid> getAllBids() {
        List<Bid> data = new ArrayList<Bid>();
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement("SELECT * FROM bid");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new Bid(
                        resultSet.getInt("idBid"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("idAuction"),
                        resultSet.getDate("date"),
                        resultSet.getString("type"),
                        resultSet.getFloat("liveBidAmount"),
                        resultSet.getFloat("maxBidAmount")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public List<Bid> getBidsById(int id) {

        List<Bid> data = new ArrayList<Bid>();
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement("SELECT name,date,liveBidAmount FROM bid b join auction a join user u WHERE b.idAuction=a.idAuction and a.idAuction=? and u.id_user =b.userId ORDER by 3 DESC;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                data.add(new Bid(
                        resultSet.getString("name"),
                        resultSet.getDate("date"),
                        resultSet.getFloat("liveBidAmount")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    @Override
    public int getNumberBids(int idCar) {

        PreparedStatement statement;
        int count = 0;
        try {
            statement = cnx.prepareStatement("SELECT count(*) FROM bid b join auction a WHERE b.idAuction=a.idAuction and a.idCar=?;");
            statement.setInt(1, idCar);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
