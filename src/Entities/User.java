/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author gtsia
 */
public class User {
    private int id_user;
    private String name;
    private String email;
    private String password;

    public User(int id_user, String name, String email, String password) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id_user;
    }

    public void setId(int id_user) {
        this.id_user= id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", name=" + name + ", email=" + email + ", password=" + password + '}';
    }

    
    
  
    
}
