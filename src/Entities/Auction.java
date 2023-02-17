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
    private int id_auction;
    private Date start_date;
    private Date end_date;
    private float starting_price;
    private float highest_bid;
    private String status;
    private List<Bid> bids;

 
    @Override
    public String toString() {
        return "Auction{" + "id_auction=" + id_auction + ", start_date=" + start_date + ", end_date=" + end_date + ", starting_price=" + starting_price + ", highest_bid=" + highest_bid + ", status=" + status + ", bids=" + bids + '}';
    }

    public int getId_auction() {
        return id_auction;
    }

    public void setId_auction(int id_auction) {
        this.id_auction = id_auction;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public float getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(float starting_price) {
        this.starting_price = starting_price;
    }

    public float getHighest_bid() {
        return highest_bid;
    }

    public void setHighest_bid(float highest_bid) {
        this.highest_bid = highest_bid;
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

    public Auction(int id_auction, Date start_date, Date end_date, float starting_price, float highest_bid, String status) {
        this.id_auction = id_auction;
        this.start_date = start_date;
        this.end_date = end_date;
        this.starting_price = starting_price;
        this.highest_bid = highest_bid;
        this.status = status;
        
    }
    
    

    
    
}
