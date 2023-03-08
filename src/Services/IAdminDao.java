/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import Entities.*;

/**
 *
 * @author gtsia
 */
public interface IAdminDao {

    public void createAdmin(Admin admin);

    public void deleteAdmin(Admin admin);

    public void updateAdmin(Admin admin);

    public List<User> view_users();

    public List<User> sortUsers_byUsername();

    public List<User> searchUser_byUsername(String username);

    public void suspend_user();

}
