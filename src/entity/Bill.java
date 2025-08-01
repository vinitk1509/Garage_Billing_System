package entity;

import java.util.List;

public class Bill {

    private int id;
    private int vehicleId;
    private List<Integer> serviceIds;
    private double totalCost;

    public Bill(int vehicleId, List<Integer> serviceIds, double totalCost) {
        this.vehicleId = vehicleId;
        this.serviceIds = serviceIds;
        this.totalCost = totalCost;
    }

    public Bill(int id, int vehicleId, List<Integer> serviceIds, double totalCost) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.serviceIds = serviceIds;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<Integer> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Integer> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
