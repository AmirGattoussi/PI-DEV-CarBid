/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Notification;
import java.util.List;

/**
 *
 * @author asus
 */
public interface NotificationDao {
    public List<Notification> getAllNotifications(int idUser);
    public int sendNotification(Notification notification);
    public void updateNotification(int idUser);
}
