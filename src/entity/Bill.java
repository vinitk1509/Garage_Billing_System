package entity;

import java.sql.Timestamp;
import java.util.List;

public class Bill {
    private int id; // auto-generated
    private int customerId;
    private Timestamp date;
    private double totalCost;
    private List<BillItem> items; // optional, can be populated later

    // Constructor before ID exists
    public Bill(int customerId, Timestamp date, double totalCost) {
        this.customerId = customerId;
        this.date = date;
        this.totalCost = totalCost;
    }

    // Full constructor
    public Bill(int id, int customerId, Timestamp date, double totalCost) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.totalCost = totalCost;
    }

    // Getters / setters
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public Timestamp getDate() { return date; }
    public double getTotalCost() { return totalCost; }
    public List<BillItem> getItems() { return items; }
    public void setItems(List<BillItem> items) { this.items = items; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Bill ID: " + id + ", Customer ID: " + customerId + ", Date: " + date + ", Total: ₹" + totalCost;
    }
}
