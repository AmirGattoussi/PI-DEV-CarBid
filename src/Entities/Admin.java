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
public class Admin extends User {

    int id_admin;

    public Admin(String name, String email, String password, int phone_number, String location) {
        super(name, email, password, phone_number, location);
    }

    public Admin(int id_admin, int id_user, String name, String email, String password, int phone_number, String location) {
        super(id_user, name, email, password, phone_number, location);
        this.id_admin = id_admin;
    }

    @Override
    public String toString() {
        return super.toString(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return super.getPassword(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        super.setName(name); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return super.getName(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id_user) {
        super.setId(id_user); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); // To change body of generated methods, choose Tools | Templates.
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

}
