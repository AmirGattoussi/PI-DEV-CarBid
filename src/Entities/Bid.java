/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Bid {
    private int id_bid;
    private User user;
    private Auction auction;
    private Date date;
    private String type;
    private float live_bid_amount;
    private float max_bid_amount;
    
    public Bid(int id_bid, User user, Auction auction, Date date, String type, float live_bid_amount, float max_bid_amount) {
        this.id_bid = id_bid;
        this.user = user;
        this.auction = auction;
        this.date = date;
        this.type = type;
        this.live_bid_amount = live_bid_amount;
        this.max_bid_amount = max_bid_amount;
    }

    public int getId_bid() {
        return id_bid;
    }

    public void setId_bid(int id_bid) {
        this.id_bid = id_bid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLive_bid_amount() {
        return live_bid_amount;
    }

    public void setLive_bid_amount(float live_bid_amount) {
        this.live_bid_amount = live_bid_amount;
    }

    public float getMax_bid_amount() {
        return max_bid_amount;
    }

    public void setMax_bid_amount(float max_bid_amount) {
        this.max_bid_amount = max_bid_amount;
    }

    @Override
    public String toString() {
        return "Bid{" + "id_bid=" + id_bid + ", user=" + user + ", auction=" + auction + ", date=" + date + ", type=" + type + ", live_bid_amount=" + live_bid_amount + ", max_bid_amount=" + max_bid_amount + '}';
    }
    
    
    
}
