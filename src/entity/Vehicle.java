package entity;

public class Vehicle {
    private int id;
    private int customerId;
    private String numberPlate;
    private String model;
    private String type;


    // Constructor for full record (used when reading from DB)
    public Vehicle(int id, int customerId, String numberPlate, String model, String type) {
        this.id = id;
        this.customerId = customerId;
        this.numberPlate = numberPlate;
        this.model = model;
        this.type = type;
    }

//    public Vehicle(String numberPlate, String model, String type) {
//        this.numberPlate = numberPlate;
//        this.model = model;
//        this.type = type;
//    }

//Constructor for inserting a new vehicle


    public Vehicle(int customerId, String numberPlate, String model, String type) {
        this.customerId = customerId;
        this.numberPlate = numberPlate;
        this.model = model;
        this.type = type;
    }

    public Vehicle(String numberPlate, String model, String type) {
        this.numberPlate = numberPlate;
        this.model = model;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", numberPlate='" + numberPlate + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
