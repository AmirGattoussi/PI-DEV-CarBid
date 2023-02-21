/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import java.util.Optional;

// import java.sql.Date;

import Entities.*;

/**
 *
 * @author neil
 */
public interface IReservationDao {

    // CRUD methods
    public void createReservation(Reservation reservation);

    public void deleteReservation(int id_user, int id_car);

    public void updateReservationDate(int id_user, int id_car, String date);

    public void updateReservationLocation(int id_user, int id_car, String location);

    public Reservation getReservation(int id_user, int id_car);

    public List<Reservation> getReservations();

    // JOB methods
    public List<Reservation> filterReservationsByUser(int id_user);

    public List<Reservation> filterReservationsByCar(int id_car);

    public List<Reservation> filterReservationsByDate(String date);
}
