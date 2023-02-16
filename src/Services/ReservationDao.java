/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;


/**
 *
 * @author neil
 */
public interface ReservationDao {
    public void createReservation();
    public void deleteReservation();
    public void updateReservation();
    public Reservation getReservation();
}
