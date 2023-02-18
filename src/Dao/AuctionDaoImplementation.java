/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Auction;
import Entities.User;
import Services.AuctionDao;
import Utils.DBconnexion;
import java.sql.Connection;
import java.sql.Date;
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

    public AuctionDaoImplementation()throws SQLException {
        cnx = DBconnexion.getInstance().getConnection();
    }
    @Override
    public void addAuction() {
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO auction VALUES ('3','12-07-01','17-08-01',25000,'canceled',37000)";
            stm.executeUpdate(query);
            System.out.println("added successfully");
        }  catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteAuction() {
        try {
            Statement stm = cnx.createStatement();
            String query = "delete from auction where id_auction=2";
            stm.executeUpdate(query);
            System.out.println("deleted successfully");
        }  catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateAuction() {
        try {
            Statement stm = cnx.createStatement();
            String query = "update auction set end_date='07-07-19',highest_bid=70000,status='open' where id_auction=3";
            stm.executeUpdate(query);
            System.out.println("updated successfully");
        }  catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Auction getAuction() {
        try {
            Statement stm = cnx.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM auction WHERE id_auction = 1");
            if (resultSet.next()) {
            int id = resultSet.getInt("id_auction");
            Date start = resultSet.getDate("start_date");
            Date end = resultSet.getDate("end_date");
            float starting_price = resultSet.getFloat("starting_price");
            float highest_bid = resultSet.getFloat("highest_bid");
            String status = resultSet.getString("status");
    
    // create a new Person object with the extracted values
    Auction auc = new Auction(id, start, end, starting_price, highest_bid, status);
                System.out.println(auc.toString());
    return auc;
}
        return resultSet.getObject(0, Auction.class);
        }  catch (SQLException ex) {
            Logger.getLogger(AuctionDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
}
}
