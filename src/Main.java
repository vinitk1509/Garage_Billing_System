import dao.CustomerDAO;
import dao.ServiceDAO;
import dao.VehicleDAO;
import entity.Customer;
import entity.Service;
import entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        ServiceDAO serviceDAO = new ServiceDAO();



        try{
//            int customerId = CustomerDAO.addCustomer(new Customer("Vinit", "9876543210", "vinit@example.com"));
//            System.out.println(" Customer added with ID: " + customerId);
            Customer customer = new Customer( "Aryan", "9350280890", "aryan@gmail.com");
//            dao.addCustomer(customer);
            int customerId = CustomerDAO.addCustomer(customer);
            System.out.println("Customer added successfully with ID: " + customerId);

            Vehicle vehicle = new Vehicle(customerId,"PB79 0968", "Vitara Brezza", "hatchback");
            vehicleDAO.addVehicle(vehicle);
            System.out.println("Vehicle added!");


//            List<Customer> customers = dao.getAllCustomers();
//            System.out.println("Customer List: ");
//            for (Customer c : customers){
//                System.out.println(c);
//            }

            List<Vehicle> vehicles = VehicleDAO.getAllVehicles();
            System.out.println("Vehicle List: ");
            for (Vehicle v : vehicles){
                System.out.println(v);
            }

            System.out.println("---- Available Services ----");
            List<Service> services = ServiceDAO.getAllServices();
            for (Service service : services) {
                System.out.println("ID: " + service.getId() +
                        ", Name: " + service.getName() +
                        ", Price: ₹" + service.getCost());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}