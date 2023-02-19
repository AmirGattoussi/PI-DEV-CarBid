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
public class Agent extends User{
int id_agent;

    public Agent(int id_agent, int id_user, String name, String email, String password) {
        super(id_user, name, email, password);
        this.id_agent = id_agent;
    }

    public int getId_agent() {
        return id_agent;
    }

    public void setId_agent(int id_agent) {
        this.id_agent = id_agent;
    }
    
    
}
