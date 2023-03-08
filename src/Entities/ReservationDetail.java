package Entities;

/**
 *
 * @author neil
 */
public class ReservationDetail {

    // *********************************************
    // Attributes
    // *********************************************

    private String name;
    private int phone_number;
    private String email;
    private int id_car;
    private String make;
    private String model;
    private String date;
    private String location;

    // *********************************************
    // Methods
    // *********************************************

    public ReservationDetail(){}

    public ReservationDetail(String name, int phone_number, String email, String make, String model, String date,
            String location) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.make = make;
        this.model = model;
        this.date = date;
        this.location = location;
    }

    public ReservationDetail(String name, int phone_number, String email, int id_car, String make, String model,
            String date,
            String location) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.id_car = id_car;
        this.make = make;
        this.model = model;
        this.date = date;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public int getCarID() {
        return id_car;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

}
