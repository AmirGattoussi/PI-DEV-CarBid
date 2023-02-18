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
public interface IReservationDao {
    public void createReservation(Reservation reservation);
    public void deleteReservation(int id_user, int id_car);
    public void updateReservation();
    public Reservation getReservation(int id_user, int id_car);
}
