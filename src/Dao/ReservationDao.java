package Dao;

import Entities.*;
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

    // *********************************************
    // Attributes
    // *********************************************

    Connection cnx;
    PreparedStatement statement;

    // *********************************************
    // Methods
    // *********************************************

    /**
     * Constructor method. Handles the connection to database.
     * 
     * @param void
     */
    public ReservationDao() {
        try {
            cnx = DBconnexion.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * CRUD method for creating a reservation.
     * 
     * @param reservation Object
     */
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

    /**
     * CRUD method for deleting a reservation.
     * 
     * @param id_user ID of user
     * @param id_car  ID of car
     *                Note: id_user,id_car together make the primary key of DB
     *                table.
     */
    //
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

    /**
     * CRUD method that updates reservation's date.
     * 
     * @param id_user ID of user
     * @param id_car  ID of car
     * @param date    value that date will get updated to.
     */
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

    /**
     * CRUD method that updates reservation's location.
     * 
     * @param id_user ID of user
     * @param id_car  ID of car
     * @param date    value that location will get updated to.
     */
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

    /**
     * CRUD method for getting information for specific a reservation.
     * 
     * @param id_user ID of user
     * @param id_car  ID of car
     */
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

    /**
     * CRUD method for getting list of all reservations.
     * 
     * @param void
     */
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

    /**
     * This method filters reservations by user and returns a list.
     * 
     * @param id_user ID of user.
     */
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

    /**
     * This method filters reservations by car and returns a list.
     * 
     * @param id_car ID of car.
     */
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

    /**
     * This method filters reservations by date and return a list.
     * 
     * @param date reservation date.
     */
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

    /**
     * This method return the total number of reservations.
     * 
     * @param void
     */
    @Override
    public int getNumberOfReservations() {
        int count = 0;
        try {
            statement = cnx.prepareStatement("SELECT COUNT(*) FROM reservation");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(ReservationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * This Job method returns a list of the informations that will be diplayed in
     * the
     * details popup for a normal user
     * 
     * @param void
     */
    @Override
    public List<ReservationDetail> reservationDetails(int id_user, int id_car) {
        List<ReservationDetail> data = new ArrayList<ReservationDetail>();

        try {
            statement = cnx.prepareStatement(
                    "SELECT u.name, u.phone_number,u.email,c.make,c.model,r.date,r.location FROM user u JOIN reservation r JOIN cars c ON u.id_agent=r.id_agent AND r.id_car=c.id_car WHERE u.id_agent IS NOT NULL AND r.id_user=? AND r.id_car=?;");

            statement.setInt(1, id_user);
            statement.setInt(2, id_car);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new ReservationDetail(
                        resultSet.getString("name"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("date"),
                        resultSet.getString("location")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * This Job method returns a list of the informations that will be displayed in
     * the details popup of an agency.
     * 
     * @param void
     */
    @Override
    public List<ReservationDetail> reservationDetailsAgency(int id_user, int id_car) {
        List<ReservationDetail> data = new ArrayList<ReservationDetail>();

        try {
            statement = cnx.prepareStatement(
                    "SELECT u.name, u.phone_number,u.email,c.make,c.model,r.date,r.location FROM user u JOIN reservation r JOIN cars c ON u.id_user=r.id_user AND r.id_car=c.id_car WHERE r.id_user=? AND r.id_car=?;");

            statement.setInt(1, id_user);
            statement.setInt(2, id_car);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new ReservationDetail(
                        resultSet.getString("name"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getInt("id_car"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("date"),
                        resultSet.getString("location")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
