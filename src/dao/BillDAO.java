package dao;

import config.DbConfig;

import entity.Bill;
import entity.BillItem;

import java.sql.*;
import java.util.List;

public class BillDAO {

    // Inserts bill and returns generated bill id
    public int addBill(Bill bill) throws SQLException {
        String sql = "INSERT INTO bills (customer_id, date, total_cost) VALUES (?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, bill.getCustomerId());
            ps.setTimestamp(2, bill.getDate());
            ps.setDouble(3, bill.getTotalCost());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int billId = rs.getInt(1);
                    return billId;
                }
            }
        }
        return -1;
    }

    // Inserts multiple bill items for a given bill
    public void addBillItems(int billId, List<BillItem> items) throws SQLException {
        String sql = "INSERT INTO bill_items (bill_id, service_id, cost) VALUES (?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (BillItem item : items) {
                ps.setInt(1, billId);
                ps.setInt(2, item.getServiceId());
                ps.setDouble(3, item.getCost());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
