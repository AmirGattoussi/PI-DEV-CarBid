/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Auction;
import Entities.Bid;
import Entities.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public interface BidDao {
    public void addLiveBid(Bid bid);

    public void addMaxBid(Bid bid);

    public void deleteBid(int id);

    public void updateBid(int idBid, Date date, String type, float liveBidAmount, float maxBidAmount);

    public Bid getBid(int id);

    public List<Bid> getAllBids();

    public List<Bid> getBidsById(int id);

    public int getNumberBids(int idCar);

    public Bid getMaxBidById(int id);
}
