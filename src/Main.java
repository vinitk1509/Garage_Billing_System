import dao.BillDAO;
import dao.CustomerDAO;
import dao.ServiceDAO;
import entity.Bill;
import entity.BillItem;
import entity.Customer;
import entity.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final ServiceDAO serviceDAO = new ServiceDAO();
    private static final BillDAO billDAO = new BillDAO();

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("\n=== Garage Billing System ===");
            System.out.println("1. Add New Customer");
            System.out.println("2. List All Customers");
            System.out.println("3. List Services");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> listCustomers();
                case 3 -> listServices();
                case 4 -> generateBill();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(name, phone, email);
        customerDAO.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void listCustomers() throws SQLException {
        List<Customer> customers = customerDAO.getAllCustomers();
        System.out.println("\n--- All Customers ---");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    private static void listServices() throws SQLException {
        List<Service> services = serviceDAO.getAllServices();
        System.out.println("\n--- Available Services ---");
        for (Service s : services) {
            System.out.println(s);
        }
    }

    private static void generateBill() throws SQLException {
        listCustomers();
        System.out.print("Enter customer ID: ");
        int customerId = getIntInput();

        List<Service> services = serviceDAO.getAllServices();
        if (services.isEmpty()) {
            System.out.println("No services available.");
            return;
        }

        List<BillItem> billItems = new ArrayList<>();
        double totalCost = 0;

        // 1. Show available services ONCE
        System.out.println("\n--- Available Services ---");
        List<Service> allServices = ServiceDAO.getAllServices(); // ✅ returns List<Service>
        for (Service s : allServices) {
            System.out.println(s.getId() + " - " + s.getName());
        }


// 2. Now accept multiple IDs without repeating the list
        while (true) {
            System.out.print("Enter service ID to add to bill (or 0 to finish): ");
            int serviceId = scanner.nextInt();

            if (serviceId == 0) break;

            Service selected = ServiceDAO.getServiceById(serviceId);
            if (selected == null) {
                System.out.println("Invalid service ID. Try again.");
            } else {
                totalCost += selected.getCost();
                billItems.add(new BillItem(0, serviceId, selected.getCost()));
            }
        }


        if (billItems.isEmpty()) {
            System.out.println("No items added to bill.");
            return;
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Bill bill = new Bill(customerId, timestamp, totalCost);
        int billId = billDAO.addBill(bill);

        for (BillItem item : billItems) {
            item.setBillId(billId);
        }
        billDAO.addBillItems(billId, billItems);

        Customer customer = CustomerDAO.getCustomerById(customerId); // make sure this method exists

        System.out.println("\n✅ Bill generated successfully with ID: " + billId);
        System.out.println("Customer Details:");
        System.out.println("Name: " + customer.getName());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Total: ₹" + totalCost);

    }

    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}
