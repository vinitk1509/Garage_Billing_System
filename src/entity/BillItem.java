package entity;

// Represents one service line in a bill
public class BillItem {
    private int id;         // auto-increment from DB
    private int billId;     // parent bill
    private int serviceId;  // which service
    private double cost;    // cost of that service

    // Constructor used before we know the generated ID
    public BillItem(int billId, int serviceId, double cost) {
        this.billId = billId;
        this.serviceId = serviceId;
        this.cost = cost;
    }

    // Full constructor (if you load from DB)
    public BillItem(int id, int billId, int serviceId, double cost) {
        this.id = id;
        this.billId = billId;
        this.serviceId = serviceId;
        this.cost = cost;
    }

    // Getters and setters
    public int getId() { return id; }
    public int getBillId() { return billId; }
    public int getServiceId() { return serviceId; }
    public double getCost() { return cost; }

    public void setId(int id) { this.id = id; }
    public void setBillId(int billId) { this.billId = billId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "BillItem [Service ID: " + serviceId + ", Cost: ₹" + cost + "]";
    }
}
