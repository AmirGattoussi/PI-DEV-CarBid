/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

// import java.sql.*;
// import java.text.SimpleDateFormat;

/**
 *
 * @author neil
 */
public class Reservation {
    private String date;
    private String location;
    private int user;
    private int car;
    private int agent;


    public Reservation(int user, int car, String date, String location, int agent) {
        this.date = date;
        this.location = location;
        this.user = user;
        this.car = car;
        this.agent = agent;
    }

    // get set for Date attribute
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // get set for location attribute
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // get set for user attribute
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    // get set for car attribute
    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    // get set for agent attribute
    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "reservation{" + "user=" + user + ", car=" + car + ", date=" + date + ", location=" + location + ", agent=" + agent + '}';
    }
    
}
