/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Reservation;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Services.*;
import Utils.DBconnexion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neil
 */
public class ReservationDao implements IReservationDao {

    Connection cnx;
    PreparedStatement statement;

    public ReservationDao() {
        try {
            cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD method for creating a reservation
    @Override
    public void createReservation(Reservation reservation) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                java.util.Date utilDate = format.parse(reservation.getDate());
                java.sql.Date mysqlDate = new java.sql.Date(utilDate.getTime());

                statement = cnx.prepareStatement(
                        "INSERT INTO reservation (id_user, id_car, date, location, id_agent) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, reservation.getUser());
                statement.setInt(2, reservation.getCar());
                statement.setDate(3, mysqlDate);
                statement.setString(4, reservation.getLocation());
                statement.setInt(5, reservation.getAgent());

                statement.executeUpdate();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // CRUD method for deleting a reservation
    @Override
    public void deleteReservation(int id_user, int id_car) {
        try {
            statement = cnx.prepareStatement("DELETE FROM reservation WHERE id_user=? AND id_car=?");
            statement.setInt(1, id_user);
            statement.setInt(2, id_car);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    // CRUD methods for updating/modifying a reservation
    @Override
    public void updateReservationDate(int id_user, int id_car, String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                java.util.Date utilDate = format.parse(date);
                java.sql.Date mysqlDate = new java.sql.Date(utilDate.getTime());

                statement = cnx.prepareStatement("UPDATE reservation SET date=? WHERE id_user=? AND id_car=?");
                statement.setDate(1, mysqlDate);
                statement.setInt(2, id_user);
                statement.setInt(3, id_car);

                statement.executeUpdate();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateReservationLocation(int id_user, int id_car, String location) {
        try {
            statement = cnx.prepareStatement("UPDATE reservation SET location=? WHERE id_user=? AND id_car=?");
            statement.setString(1, location);
            statement.setInt(2, id_user);
            statement.setInt(3, id_car);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    // CRUD method for getting information for specific a reservation
    @Override
    public Reservation getReservation(int id_user, int id_car) {
        try {
            statement = cnx.prepareStatement("SELECT * FROM reservation WHERE id_user=? AND id_car=?");
            statement.setInt(1, id_user);
            statement.setInt(2, id_car);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_car"),
                        resultSet.getString("date"),
                        resultSet.getString("location"),
                        resultSet.getInt("id_agent"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    // CRUD method for getting list of all reservations
    @Override
    public List<Reservation> getReservations() {
        List<Reservation> data = new ArrayList<Reservation>();

        try {
            statement = cnx.prepareStatement("SELECT * FROM reservation");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new Reservation(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_car"),
                        resultSet.getString("date"),
                        resultSet.getString("location"),
                        resultSet.getInt("id_agent")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // JOBS methods for filtering reservations
    // Note: The following methods, all of them, return a List of reservations.
    @Override
    public List<Reservation> filterReservationsByUser(int id_user) {
        List<Reservation> filteredData = new ArrayList<Reservation>();

        try {
            statement = cnx.prepareStatement("SELECT * FROM reservation WHERE id_user=?");
            statement.setInt(1, id_user);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                filteredData.add(new Reservation(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_car"),
                        resultSet.getString("date"),
                        resultSet.getString("location"),
                        resultSet.getInt("id_agent")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filteredData;
    }

    @Override
    public List<Reservation> filterReservationsByCar(int id_car) {
        List<Reservation> filteredData = new ArrayList<Reservation>();

        try {
            statement = cnx.prepareStatement("SELECT * FROM reservation WHERE id_car=?");
            statement.setInt(1, id_car);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                filteredData.add(new Reservation(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_car"),
                        resultSet.getString("date"),
                        resultSet.getString("location"),
                        resultSet.getInt("id_agent")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filteredData;
    }

    @Override
    public List<Reservation> filterReservationsByDate(String date) {
        List<Reservation> filteredData = new ArrayList<Reservation>();

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                java.util.Date utilDate = format.parse(date);
                java.sql.Date mysqlDate = new java.sql.Date(utilDate.getTime());

                statement = cnx.prepareStatement("SELECT * FROM reservation WHERE date=?");
                statement.setDate(1, mysqlDate);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    filteredData.add(new Reservation(
                            resultSet.getInt("id_user"),
                            resultSet.getInt("id_car"),
                            resultSet.getString("date"),
                            resultSet.getString("location"),
                            resultSet.getInt("id_agent")));
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filteredData;
    }
}
