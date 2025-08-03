import dao.BillDAO;
import dao.CustomerDAO;
import dao.ServiceDAO;
import entity.Bill;
import entity.BillItem;
import entity.Customer;
import entity.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        BillDAO billDAO = new BillDAO();

        try {
            // ---------- CUSTOMER ----------
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter contact (phone): ");
            String phone = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            Customer customer = new Customer(name, phone, email);
            int customerId = customerDAO.addCustomer(customer); // returns generated ID
            if (customerId == -1) {
                System.out.println("❌ Failed to add customer.");
                return;
            }
            System.out.println("✅ Customer added with ID: " + customerId);

            // ---------- SERVICES ----------
            List<Service> services = serviceDAO.getAllServices();
            System.out.println("\nAvailable Services:");
            for (int i = 0; i < services.size(); i++) {
                Service s = services.get(i);
                System.out.printf("%d. %s - ₹%.2f%n", i + 1, s.getName(), s.getCost());
            }

            System.out.print("\nEnter number of services used: ");
            int count = scanner.nextInt();
            List<BillItem> billItems = new ArrayList<>();
            double total = 0;

            for (int i = 0; i < count; i++) {
                System.out.print("Enter service number: ");
                int choice = scanner.nextInt();
                if (choice < 1 || choice > services.size()) {
                    System.out.println("Invalid selection, try again.");
                    i--;
                    continue;
                }
                Service selected = services.get(choice - 1);
                total += selected.getCost();
                billItems.add(new BillItem(0, selected.getId(), selected.getCost())); // billId placeholder
            }

            // ---------- BILL CREATION ----------
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Bill bill = new Bill(customerId, now, total);
            int billId = billDAO.addBill(bill);
            if (billId == -1) {
                System.out.println("❌ Failed to create bill.");
                return;
            }

            // ---------- ASSIGN BILL ID TO ITEMS AND SAVE ----------
            for (BillItem item : billItems) {
                item.setBillId(billId);
            }
            billDAO.addBillItems(billId, billItems);

            // ---------- SUMMARY ----------
            System.out.println("\n✅ Bill created successfully!");
            System.out.println("Bill ID: " + billId);
            System.out.println("Customer ID: " + customerId);
            System.out.println("Services:");
            for (BillItem bi : billItems) {
                System.out.println("  - Service ID: " + bi.getServiceId() + ", Cost: ₹" + bi.getCost());
            }
            System.out.printf("Total Amount: ₹%.2f%n", total);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
