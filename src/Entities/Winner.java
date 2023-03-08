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
public class Winner {
    private String name;
    private int phone_number;
    private int numberOfWinningBids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public int getNumberOfWinningBids() {
        return numberOfWinningBids;
    }

    public void setNumberOfWinningBids(int numberOfWinningBids) {
        this.numberOfWinningBids = numberOfWinningBids;
    }

    public Winner(String name, int phone_number, int numberOfWinningBids) {
        this.name = name;
        this.phone_number = phone_number;
        this.numberOfWinningBids = numberOfWinningBids;
    }
    
}
