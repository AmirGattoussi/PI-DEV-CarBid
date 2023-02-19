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
    private int Id;
    private String Type;
     private int Pou;
    private String Description;
     private double Price;
    private String Typec;
   
   
     public SpareParts(int Id, String Type,  int Pou, String Description, double Price, String Typec) {
        this.Id = Id;
        this.Type = Type;
         this.Pou = Pou;
        this.Description = Description;
        
       
        this.Price = Price;
        this.Typec = Typec;
    }

   

    public SpareParts() {
    }

   

    public int getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }
    public int getPou() {
        return Pou;
    }

    public String getDescription() {
        return Description;
    }
  public double getPrice() {
        return Price;
    }

    public String getTypec() {
        return Typec;
    }

    

  
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
     public void setPou(int Pou) {
        this.Pou = Pou;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setTypec(String Typec) {
        this.Typec = Typec;
    }

   

    
    @Override
    public String toString() {
        return "SpareParts{" + "Id=" + Id + ", Type=" + Type + ", Pou=" + Pou + ", description=" + Description + ", Price=" + Price + ", Typec=" + Typec + '}';
    }

   
    
    
}
