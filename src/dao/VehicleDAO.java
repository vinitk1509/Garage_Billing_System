package dao;

import config.DbConfig;
import entity.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    //method to add new vehicle to DB
    public void addVehicle(Vehicle vehicle) throws SQLException {
        Connection conn = DbConfig.getConnection();
        String sql = "INSERT INTO vehicles (customer_id, number_plate, model, type) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, vehicle.getCustomerId());
        ps.setString(2, vehicle.getNumberPlate());
        ps.setString(3, vehicle.getModel());
        ps.setString(4, vehicle.getType());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    // Method to fetch all vehicles from the DB
    public static List<Vehicle> getAllVehicles() throws SQLException{
        List<Vehicle> vehicles = new ArrayList<>();
        Connection conn = DbConfig.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM vehicles");

        while (rs.next()){
            Vehicle v = new Vehicle(
                    rs.getInt("id"),
                    rs.getInt("customer_id"),
                    rs.getString("number_plate"),
                    rs.getString("model"),
                    rs.getString("type")
            );
            vehicles.add(v);
        }

        rs.close();
        st.close();
        conn.close();

        return vehicles;
    }

}
