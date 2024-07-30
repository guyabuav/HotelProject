import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainMethod {

    public static void main(String[] args) {
        Customer c1 = new Customer("0525515555", "guy ab", "12345", "Haifa", "guyab", "12345");
        Customer c2 = new Customer("0521234567", "admin user", "admin123", "Admin City", "admin", "adminpass");
        Customer c3 = new Customer("0521234111", "guy abua", "123457", "Netanya", "guyabua", "123456");
        Customer c4 = new Customer("0521112311", "guy abuav", "123458", "Kadima", "admin", "admin");

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);

        Hotel h1 = new Hotel("091231231", 5, 0, "Eilat", "Agamim");
        Hotel h2 = new Hotel("095551231", 4, 0, "Kineret", "Salonim");

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(h1);
        hotels.add(h2);

        Room r1 = new Room(5, 200);
        Room r2 = new Room(3, 200);
        Room r3 = new Room(4, 300);
        Room r4 = new Room(5, 400);
        Room r5 = new Room(7, 700);
        Room r6 = new Room(2, 1000);
        Room r7 = new Room(2, 150);

        h1.addRoom(r1);
        h1.addRoom(r2);
        h1.addRoom(r3);
        h1.addRoom(r4);
        h2.addRoom(r5);
        h2.addRoom(r6);
        h2.addRoom(r7);

        PaymentProcessorCompany comp1 = new PaymentProcessorCompany("0523123123", "ashdod", "MasterCard");
        PaymentProcessorCompany comp2 = new PaymentProcessorCompany("0523123123", "haifa", "Visa");
        PaymentProcessorCompany comp3 = new PaymentProcessorCompany("0523123123", "jerusalem", "Pepper");

        List<PaymentProcessorCompany> companies = new ArrayList<>();
        companies.add(comp1);
        companies.add(comp2);
        companies.add(comp3);
        
        // Start the payment processor thread for each company
        for (PaymentProcessorCompany company : companies) {
            PaymentProcessorThread paymentProcessorThread = new PaymentProcessorThread(company, hotels);
            paymentProcessorThread.start();
            System.out.println("Started payment processor thread for: " + company.getName());
        }
        
        // Start the hotel confirmation thread for each hotel
        for (Hotel hotel : hotels) {
            HotelConfirmationThread hotelConfirmationThread = new HotelConfirmationThread(hotel);
            hotelConfirmationThread.start();
            System.out.println("Started hotel confirmation thread for: " + hotel.getHotelName());
        }

        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c3.toString());
        System.out.println(c4.toString());

        Scanner scanner = new Scanner(System.in);
        Customer loggedInCustomer = null;
        while (loggedInCustomer == null) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            loggedInCustomer = Customer.authenticate(customers, username, password);

            if (loggedInCustomer == null) {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Log login details after successful authentication
        Customer.logLoginDetails(loggedInCustomer);

        System.out.println("Welcome, " + loggedInCustomer.getName() + "!");

        if ("admin".equals(loggedInCustomer.getUsername())) {
            displayAdminMenu(scanner, loggedInCustomer, hotels, companies);
        } else {
            displayCustomerMenu(scanner, loggedInCustomer, hotels, companies);
        }
    }

    private static void displayCustomerMenu(Scanner scanner, Customer loggedInCustomer, List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. Create Reservation");
            System.out.println("2. Update Profile");
            System.out.println("3. View Past Reservations");
            System.out.println("4. View Available Rooms");
            System.out.println("5. Edit Reservation");
            System.out.println("6. Cancel Reservation");
            System.out.println("7. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Creating a reservation...");
                    loggedInCustomer.createReservation(hotels, companies);
                    break;
                case 2:
                    System.out.println("Updating profile...");
                    loggedInCustomer.updateProfileGUI();
                    break;
                case 3:
                    System.out.println("Viewing past reservations...");
                    loggedInCustomer.viewPastReservations(hotels);
                    break;
                case 4:
                    System.out.println("Searching for room...");
                    loggedInCustomer.searchRoom(hotels);
                    break;
                case 5:
                    System.out.println("Edit reservation...");
                    loggedInCustomer.editReservation(hotels, companies);
                    break;
                case 6:
                    System.out.println("Cancel reservation...");
                    loggedInCustomer.cancelReservation(hotels, companies);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void displayAdminMenu(Scanner scanner, Customer loggedInCustomer, List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room Details");
            System.out.println("3. View Logs");
            System.out.println("4. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Adding room...");
                    loggedInCustomer.addRoom(hotels);
                    break;
                case 2:
                    System.out.println("Updating Room Details...");
                    loggedInCustomer.updateRoomDetails(hotels);
                    break;
                case 3:
                    System.out.println("Viewing logs...");
                    viewLogs();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void viewLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("logins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from log file: " + e.getMessage());
        }
    }
}
