package Entities;

import java.util.ArrayList;

public class Carwishlist {

    private ArrayList<String> cars;

    public Carwishlist() {
        cars = new ArrayList<String>();
    }

    public void addCar(String car) {
        cars.add(car);
    }

    public void removeCar(String car) {
        cars.remove(car);
    }

    public ArrayList<String> getCars() {
        return cars;
    }
}
