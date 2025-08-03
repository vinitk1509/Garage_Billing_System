// dao is to handle database logic
package dao;

import config.DbConfig;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
//    public void addCustomer(Customer customer) throws SQLException {
//        Connection conn = DbConfig.getConnection();
//        String sql = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, customer.getName());
//        ps.setString(2, customer.getPhone());
//        ps.setString(3, customer.getEmail());
//        ps.executeUpdate();
//        ps.close();
//        conn.close();
//    }

    public static int addCustomer(Customer customer) {
        int generatedId = -1;
        String sql = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    System.out.println("✅ Generated Customer ID: " + generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
    public static Customer getCustomerById(int id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = DbConfig.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM customers");

        while (rs.next()){
            Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email")
            );
            customers.add(customer);
        }

        rs.close();
        st.close();
        conn.close();

        return customers;
    }
}
