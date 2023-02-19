/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Auction;
import Entities.Bid;
import Entities.User;
import Services.BidDao;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class BidDaoImplementation implements BidDao {
  Connection cnx;

    public BidDaoImplementation()throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    } 
@Override
 public void addLiveBid(Bid bid)  {
      try {
          PreparedStatement statement = cnx.prepareStatement(
                  "INSERT INTO bid (userId,idAuction, date,type,liveBidAmount,maxBidAmount) VALUES (?, ?, ?,'Live', ?,0)");
        statement.setInt(1, bid.getUserId());
        statement.setInt(2, bid.getIdAuction());
        statement.setDate(3, bid.getDate());
	statement.setFloat(4, bid.getLiveBidAmount());
	statement.executeUpdate();
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
                  "INSERT INTO bid (userId,idAuction, date,type,liveBidAmount,maxBidAmount) VALUES (?, ?, ?,'Max', ?, ?)");
         statement.setInt(1, bid.getUserId());
        statement.setInt(2, bid.getIdAuction());
        statement.setDate(3, bid.getDate());
	statement.setFloat(4, bid.getLiveBidAmount());
	statement.setFloat(5, bid.getMaxBidAmount());
	statement.executeUpdate();
	System.out.println("added successfully");  
      } catch (SQLException ex) {
          Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
      }

     
    }

@Override
public Bid getBid (int id)  {
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
		    resultSet.getFloat("maxBidAmount")           
            );
        }

        return null;
          
      } catch (SQLException ex) {
          Logger.getLogger(BidDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
      }
return null;
    }
//Uncessary update
@Override
 public void updateBid(int idBid ,Date date, String type, float liveBidAmount,float maxBidAmount )  {
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
}

