/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.File;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author rima
 */
public class Car {

    private int id_car;
    private String model;
    private String color;
    private String type;
    private String make;
    private String description;
    private int mileage;
    private int year;
    private int fiscalpower;
    private String transmission;
    private String loss;
    private String primarydamage;
    private String secondarydamage;
    private String fueltype;
    private double baseprice = 10000;
    private String carImg;
    

    public Car() {

    }

    public Car(String model, String color, String type, String make, String description, int mileage, int year, int fiscalpower, String transmission, String loss, String primarydamage, String secondarydamage, String fueltype,String carImg) {
        this.model = model;
        this.color = color;
        this.type = type;
        this.make = make;
        this.description = description;
        this.mileage = mileage;
        this.year = year;
        this.fiscalpower = fiscalpower;
        this.transmission = transmission;
        this.loss = loss;
        this.primarydamage = primarydamage;
        this.secondarydamage = secondarydamage;
        this.fueltype = fueltype;
        this.carImg=carImg;
        
        
        
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public Car(int id_car, String model, String color, String type, String make, String description, int mileage, int year, int fiscalpower, String transmission, String loss,
            String primarydamage, String secondarydamage, String fueltype) {
        this.id_car = id_car;
        this.model = model;
        this.color = color;
        this.type = type;
        this.make = make;
        this.description = description;
        this.mileage = mileage;
        this.year = year;
        this.fiscalpower = fiscalpower;
        this.transmission = transmission;
        this.loss = loss;
        this.primarydamage = primarydamage;
        this.secondarydamage = secondarydamage;
        this.fueltype= fueltype;
        
        
    }

  

   

    public int getId() {
        return id_car;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }


    public String getMake() {
        return make;
    }

    public String getDescription() {
        return description;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public int getFiscalpower() {
        return fiscalpower;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getLoss() {
        return loss;
    }

    public String getPrimarydamage() {
        return primarydamage;
    }

    public String getSecondarydamage() {
        return secondarydamage;
    }

    public void setId(int id_car) {
        this.id_car = id_car;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFiscalpower(int fiscalpower) {
        this.fiscalpower = fiscalpower;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public void setPrimarydamage(String primarydamage) {
        this.primarydamage = primarydamage;
    }

    public void setSecondarydamage(String secondarydamage) {
        this.secondarydamage = secondarydamage;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    @Override
    public String toString() {
        return "Cars{" + "id=" + id_car + ", model=" + model + ", color=" + color + ",type=" + type + ",make=" + make + ",description=" + description + ",mileage=" + mileage + ",year=" + year + ",fiscalpower=" + fiscalpower
                + ",transmission=" + transmission + ",loss=" + loss + ", primarydamage=" + primarydamage + ",secondarydamage=" + secondarydamage + ",fueltype=" + fueltype + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id_car;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.id_car != other.id_car) {
            return false;
        }
        return true;
    }
    
    public double getbasevalue(){
        
        double x = baseprice*this.fiscalpower;
        System.out.println(x);
        if(mileage>10000) x= x- x *0.05 *(mileage /10000 );
        System.out.println(x);
        int y = LocalDate.now().getYear() - year;
        System.out.println(x-x*y*0.05);
     return x-x*y*0.05;
    }
    
    }


