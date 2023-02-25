/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;


import Entities.Auction;
import java.sql.Date;
import java.util.List;


/**
 *
 * @author asus
 */
public interface AuctionDao {
    public void addAuction(Auction auction);
    public void deleteAuction(int id);
    public void updateAuction(int id, float highestBid,String status,Date endDate);
    public Auction getAuction(int id); 
    public Float getHighestBidById(int idCar); 
    public Date getDeadline(int idAuction);
    public List<Auction> getAllAuctions();
}
