/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Yasmine
 */
public class Command {
    private int id_command;
    private int id_user;
    private int id_sparepart;

    public Command(int id_command, int id_user, int id_sparepart) {
        this.id_command = id_command;
        this.id_user = id_user;
        this.id_sparepart = id_sparepart;
    }

    public Command() {
    };

    public int getId_command() {
        return id_command;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_sparepart() {
        return id_sparepart;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_sparepart(int id_sparepart) {
        this.id_sparepart = id_sparepart;
    }

    @Override
    public String toString() {
        return "Command{" + "id_command=" + id_command + ", id_user=" + id_user + ", id_sparepart=" + id_sparepart
                + '}';
    }

}
