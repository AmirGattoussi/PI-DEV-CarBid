/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class Auction {
    private int idAuction;
    private Date startDate;
    private Date endDate;
    private float startingPrice;
    private float highestBid;
    private String status;
    private int carId;
    private List<Bid> bids;

    public Auction(Date startDate, Date endDate, float startingPrice, float highestBid, String status, int carId) {
      
        this.startDate = startDate;
        this.endDate = endDate;
        this.startingPrice = startingPrice;
        this.highestBid = highestBid;
        this.status = status;
        this.carId=carId;
       
    }

    public Auction(int idAuction, Date startDate, Date endDate, float startingPrice, float highestBid, String status, int carId) {
        this.idAuction = idAuction;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startingPrice = startingPrice;
        this.highestBid = highestBid;
        this.status = status;
        this.carId = carId;
    }

 
    @Override
    public String toString() {
        return "Auction{" + "id_auction=" + idAuction + ", start_date=" + startDate + ", end_date=" + endDate + ", starting_price=" + startingPrice + ", highest_bid=" + highestBid + ", status=" + status + ", carId=" + carId+ ", bids=" + bids + '}';
    }

    public int getIdAuction() {
        return idAuction;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setIdAuction(int idAuction) {
        this.idAuction = idAuction;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public float getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(float highestBid) {
        this.highestBid = highestBid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }


    
    

    
    
}
