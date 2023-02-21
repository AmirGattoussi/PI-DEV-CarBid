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
    private int id_user;
    private int id_car;
    private int id_agent;

    public Reservation(int id_user, int id_car, String date, String location, int id_agent) {
        this.date = date;
        this.location = location;
        this.id_user = id_user;
        this.id_car = id_car;
        this.id_agent = id_agent;
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

    // get set for id_user attribute
    public int getUser() {
        return id_user;
    }

    public void setUser(int id_user) {
        this.id_user = id_user;
    }

    // get set for id_car attribute
    public int getCar() {
        return id_car;
    }

    public void setCar(int id_car) {
        this.id_car = id_car;
    }

    // get set for id_agent attribute
    public int getAgent() {
        return id_agent;
    }

    public void setAgent(int id_agent) {
        this.id_agent = id_agent;
    }

    @Override
    public String toString() {
        return "reservation{" + "user=" + id_user + ", car=" + id_car + ", date=" + date + ", location=" + location
                + ", agent=" + id_agent + '}';
    }

}
