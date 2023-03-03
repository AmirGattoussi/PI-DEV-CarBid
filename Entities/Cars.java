/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author rima
 */
public class Cars {
   private int id;
   private String model;
   private String color;
   private String type;
   private String make;
   private String description;
   private Float mileage;
   private int year; 
   private Float fiscalpower;
   private String transmission;
   private String loss ;
   private String primarydamage ;
   private String secondarydamage ;
   private String fueltype;
      
 
   
    public Cars() {
       
    }
   
   public Cars(int id,String model, String color,String type,String make,String description,Float mileage,int year,Float fiscalpower,String transmission,String loss,
String primarydamage, String secondarydamage,String fueltype) {
       this.id=id;
       this.model=model;
       this.color=color;
       this.type=type;
       this.make=make;
       this.description=description;
       this.mileage=mileage;
       this.year=year;
       this.fiscalpower=fiscalpower;
       this.transmission=transmission;
       this.loss=loss;
       this.primarydamage=primarydamage;
       this.secondarydamage=secondarydamage;
       
    }

    public int getId() {
        return id; 
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

    public Float getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public Float getFiscalpower() {
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

    public void setId(int id) {
        this.id = id;
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

    public void setMileage(Float mileage) {
        this.mileage = mileage;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFiscalpower(Float fiscalpower) {
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
        return "Cars{" + "id=" + id + ", model=" + model + ", color=" + color + ",type=" + type + ",make=" + make + ",description=" + description + ",mileage=" + mileage + ",yeae=" + year + ",fiscalpower=" +fiscalpower +
                ",transmission=" + transmission + ",loss=" + loss + ", primarydamage=" +primarydamage + ",secondarydamage=" + secondarydamage + ",fueltype=" + fueltype +'}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
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
        final Cars other = (Cars) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
   
    }
 
    
   
   
   
    

