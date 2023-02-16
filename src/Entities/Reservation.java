/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author neil
 */
public class Reservation {
    Date date;
    String location;
    String user;
    String car;
    String agent;


    public Reservation(Date date, String location, String user, String car, String agent) {
        this.date = date;
        this.location = location;
        this.user = user;
        this.car = car;
        this.agent = agent;
    }

    // get set for Date attribute
    public Date getDate() {
        return date;
    }

    public void setId(Date date) {
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
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // get set for car attribute
    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    // get set for agent attribute
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "reservation{" + "date=" + date + ", location=" + location + "user=" + user + ", car=" + car + ", agent=" + agent + '}';
    }
    
}
