/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author asus
 */
public class Notification {
    private int idNotif;
    private int userId;
    private String content;
    private String status;
    private String isSent;

    public String getIsSent() {
        return isSent;
    }

    public void setIsSent(String isSent) {
        this.isSent = isSent;
    }
    
    public Notification() {
    }

    public Notification(int userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Notification(int idNotif, int userId, String content, String status, String isSent) {
        this.idNotif = idNotif;
        this.userId = userId;
        this.content = content;
        this.status = status;
        this.isSent = isSent;
    }

    public Notification(int userId, String content, String status, String isSent) {
        this.userId = userId;
        this.content = content;
        this.status = status;
        this.isSent = isSent;
    }
    

    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
