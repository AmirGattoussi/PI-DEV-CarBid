/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.*;
import Services.AuctionDao;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class AuctionDaoImplementation implements AuctionDao {
    Connection cnx;

    public AuctionDaoImplementation() throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }

    @Override
    public void addAuction(Auction auction) {
        PreparedStatement statement;
        try {
            statement = cnx.prepareStatement(
                    "INSERT INTO auction (startDate, endDate, startingPrice,highestBid,status,carId) VALUES (?, ?, ?, ?, ?, ?)");
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
                        resultSet.getInt("carId")

                );

            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void updateAuction(int id, float highestBid, String status) {
        PreparedStatement statement;
    try {
        statement = cnx.prepareStatement(
                "UPDATE auction SET  highestBid= ?, status = ? WHERE idAuction = ?");
        statement.setFloat(1, highestBid);
        statement.setString(2, status);
        statement.setInt(3, id);
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
    public Float getHighestBidById(int idCar) {
 try {
          PreparedStatement statement = cnx.prepareStatement(
                  "SELECT highestBid FROM auction WHERE auction.idCar = ?");     
        statement.setInt(1, idCar);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
       float floatValue = resultSet.getFloat("highestBid");
        return floatValue;}
        else {
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
        return deadline;}
        else {
            System.out.println("error");
        }  
      } catch (SQLException ex) {
          Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
      }
 return null;
    }


}
