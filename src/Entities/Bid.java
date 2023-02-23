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
    private int idBid;
    private int userId;
    private int idAuction;
    private Date date;
    private String type;
    private float liveBidAmount;
    private float maxBidAmount;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bid(String name,Date date, float liveBidAmount ) {
        this.date = date;
        this.liveBidAmount = liveBidAmount;
        this.name = name;
    }
    
    public Bid(int id_bid, int userId, int idAuction, Date date, String type, float liveBidAmount, float maxBidAmount) {
        this.idBid = id_bid;
        this.userId = userId;
        this.idAuction = idAuction;
        this.date = date;
        this.type = type;
        this.liveBidAmount = liveBidAmount;
        this.maxBidAmount = maxBidAmount;
    }

    public Bid(int userId, int idAuction, Date date, String type, float liveBidAmount, float maxBidAmount) {
        this.userId = userId;
        this.idAuction = idAuction;
        this.date = date;
        this.type = type;
        this.liveBidAmount = liveBidAmount;
        this.maxBidAmount = maxBidAmount;
    }

    public int getIdBid() {
        return idBid;
    }

    public void setIdBid(int idBid) {
        this.idBid = idBid;
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

    public float getLiveBidAmount() {
        return liveBidAmount;
    }

    public void setLiveBidAmount(float liveBidAmount) {
        this.liveBidAmount = liveBidAmount;
    }

    public float getMaxBidAmount() {
        return maxBidAmount;
    }

    public void setMaxBidAmount(float maxBidAmount) {
        this.maxBidAmount = maxBidAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIdAuction() {
        return idAuction;
    }

    public void setIdAuction(int idAuction) {
        this.idAuction = idAuction;
    }

    @Override
    public String toString() {
        return "Bid{" + "idBid=" + idBid + ", userId=" + userId + ", idAuction=" + idAuction + ", date=" + date
                + ", type=" + type + ", liveBidAmount=" + liveBidAmount + ", maxBidAmount=" + maxBidAmount + '}';
    }

    public Bid() {
    }

    public Bid(int userId, int idAuction, float liveBidAmount) {
        this.userId = userId;
        this.idAuction = idAuction;
        this.liveBidAmount = liveBidAmount;
    }

    public Bid(int userId, int idAuction, float liveBidAmount, float maxBidAmount) {
        this.userId = userId;
        this.idAuction = idAuction;
        this.liveBidAmount = liveBidAmount;
        this.maxBidAmount = maxBidAmount;
    }

    public Bid(int userId, Date date, float liveBidAmount) {
        this.userId = userId;
        this.date = date;
        this.liveBidAmount = liveBidAmount;
    }
    

    
    
}
