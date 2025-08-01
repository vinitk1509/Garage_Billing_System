package dao;

import config.DbConfig;
import entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    //method to add service in DB
    public static void addService(Service service) throws SQLException {
        Connection conn = DbConfig.getConnection();
        String sql = "INSERT INTO services (name, price) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, service.getName());
        ps.setDouble(2, service.getCost());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()){
            service.setId(rs.getInt(1));
            System.out.println("Service added with ID: " + service.getId());
        }
        rs.close();
        ps.close();
        conn.close();
    }

    public static List<Service> getAllServices() throws SQLException {
        List<Service> services = new ArrayList<>();
        Connection conn = DbConfig.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM services");

        while (rs.next()){
            services.add(new Service(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
            ));
        }

        rs.close();
        st.close();
        conn.close();

        return services;
    }
}
