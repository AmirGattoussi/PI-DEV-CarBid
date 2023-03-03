/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author gtsia
 */
public class User {

    private int id_user;
    private String name;
    private String email;
    private String password;
    private int phone_number;
    private String location;

    public User(String name, String email, String password, int phone_number, String location) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.location = location;
    }

    public User(int id_user, String name, String email, String password, int phone_number) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;

    }

    public User(int id_user, String name, String email, String password, int phone_number, String location) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.location = location;
    }

    public int getId() {
        return id_user;
    }

    public void setId(int id_user) {
        this.id_user = id_user;
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

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", name=" + name + ", email=" + email + ", password=" + password
                + ", phone_number=" + phone_number + '}';
    }

}
