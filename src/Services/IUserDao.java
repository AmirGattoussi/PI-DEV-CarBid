/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;

/**
 *
 * @author asus
 */
public interface IUserDao {
    public void createUser(User user);

    public void updateUser(User user);

    public User getUserById(int id_user);
}
