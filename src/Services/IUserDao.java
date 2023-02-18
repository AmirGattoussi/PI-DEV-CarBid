/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Auction;


/**
 *
 * @author asus
 */
public interface IUserDao {
public void createUser(User user);
public void updateUser(User user);
public User getUserById(int id_user);}

public interface AuctionDao {
    public void addAuction();
    public void deleteAuction();
    public void updateAuction();
    public Auction getAuction(); 
}
