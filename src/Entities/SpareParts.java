/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Yasmine
 */
public class SpareParts {

    private int id_sparepart;
    private String type;
    private int pou;
    private String description;
    private double price;
    private String typec;

    public SpareParts(int id_sparepart, String type, int pou, String description, double price, String typec) {
        this.id_sparepart = id_sparepart;
        this.type = type;
        this.pou = pou;
        this.description = description;

        this.price = price;
        this.typec = typec;
    }

    public SpareParts() {
    }

    public int getId() {
        return id_sparepart;
    }

    public String getType() {
        return type;
    }

    public int getPou() {
        return pou;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getTypec() {
        return typec;
    }

    public void setId(int Id) {
        this.id_sparepart = Id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPou(int pou) {
        this.pou = pou;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTypec(String typec) {
        this.typec = typec;
    }

    @Override
    public String toString() {
        return "SpareParts{" + "id_sparepart=" + id_sparepart + ", Type=" + type + ", Pou=" + pou + ", description=" + description + ", Price=" + price + ", typec=" + typec + '}';
    }

}
