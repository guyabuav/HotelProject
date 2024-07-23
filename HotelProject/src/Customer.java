import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private String telephone;
    private String name;
    private String id;
    private String address;
    private String username;
    private String password;

    // Default constructor
    public Customer() {}

    // Parameterized constructor
    public Customer(String telephone, String name, String id, String address, String username, String password) {
        this.telephone = telephone;
        this.name = name;
        this.id = id;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // GUI for updating profile
    public void updateProfileGUI() {
        Frame frame = new Frame("Update Profile");

        frame.setLayout(new GridLayout(7, 2));

        Label labelTelephone = new Label("Telephone:");
        TextField tfTelephone = new TextField(telephone);
        frame.add(labelTelephone);
        frame.add(tfTelephone);

        Label labelName = new Label("Name:");
        TextField tfName = new TextField(name);
        frame.add(labelName);
        frame.add(tfName);

        Label labelId = new Label("ID:");
        TextField tfId = new TextField(id);
        frame.add(labelId);
        frame.add(tfId);

        Label labelAddress = new Label("Address:");
        TextField tfAddress = new TextField(address);
        frame.add(labelAddress);
        frame.add(tfAddress);

        Label labelUsername = new Label("Username:");
        TextField tfUsername = new TextField(username);
        frame.add(labelUsername);
        frame.add(tfUsername);

        Label labelPassword = new Label("Password:");
        TextField tfPassword = new TextField(password);
        frame.add(labelPassword);
        frame.add(tfPassword);

        Button btnUpdate = new Button("Update");
        frame.add(new Label());  // Empty label for spacing
        frame.add(btnUpdate);

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telephone = tfTelephone.getText();
                name = tfName.getText();
                id = tfId.getText();
                address = tfAddress.getText();
                username = tfUsername.getText();
                password = tfPassword.getText();

                System.out.println("Profile updated: " + Customer.this);
                frame.dispose();
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    // Method to select a hotel from a list
    private Hotel selectHotel(List<Hotel> hotels) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a hotel by ID:");
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
        }
        int hotelId = Integer.parseInt(scanner.nextLine());
        for (Hotel hotel : hotels) {
            if (hotel.getHotelId() == hotelId) {
                return hotel;
            }
        }
        System.out.println("Invalid hotel ID.");
        return null;
    }

    // Method to create a reservation
    public void createReservation(List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
        Hotel hotel = selectHotel(hotels);
        if (hotel == null) {
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of guests:");
        int numOfGuests = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter start date (yyyy-mm-dd):");
        String[] startDateInput = scanner.nextLine().split("-");
        GregorianCalendar startDate = new GregorianCalendar(
                Integer.parseInt(startDateInput[0]),
                Integer.parseInt(startDateInput[1]) - 1,
                Integer.parseInt(startDateInput[2])
        );

        System.out.println("Enter end date (yyyy-mm-dd):");
        String[] endDateInput = scanner.nextLine().split("-");
        GregorianCalendar endDate = new GregorianCalendar(
                Integer.parseInt(endDateInput[0]),
                Integer.parseInt(endDateInput[1]) - 1,
                Integer.parseInt(endDateInput[2])
        );

        System.out.println("Enter room ID:");
        int roomId = Integer.parseInt(scanner.nextLine());

        GregorianCalendar reservationDate = new GregorianCalendar(); // Today's date

        // Check if the room is available
        if (!hotel.checkAvailableRooms(startDate, endDate).contains(roomId)) {
            System.out.println("Error: Room is not available.");
            return;
        }

        // Check if the number of guests is within the room's capacity
        Room room = hotel.getRoomById(roomId);
        if (room == null || !room.capacityCheck(numOfGuests)) {
            System.out.println("Error: Room capacity exceeded or room not found.");
            return;
        }

        // Calculate the price based on the room's price per night
        long diffInMillies = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        long days = (diffInMillies / (1000 * 60 * 60 * 24)) + 1; // +1 to include the end date
        double price = days * room.getPricePerNight();

        // Print the calculated price to the user
        System.out.println("The total price for the reservation is: " + price);

        // Create and save the reservation
        Reservation reservation = new Reservation(
                numOfGuests, reservationDate, startDate, endDate, roomId, price, this, hotel
        );

        hotel.addReservation(reservation);
        System.out.println("Reservation created successfully.");
        this.createPayment(price, companies);
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Customer{" +
                "telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void viewPastReservations(List<Hotel> hotels) {
        System.out.println("Entering viewPastReservations");
        Hotel hotel = selectHotel(hotels);
        if (hotel == null) {
            System.out.println("No hotel selected.");
            return;
        }

        List<Reservation> pastReservations = new ArrayList<>();
        for (Reservation reservation : hotel.getReservations()) {
            if (reservation.getCustomer().getId().equals(this.id)) {
                pastReservations.add(reservation);
            }
        }

        if (pastReservations.isEmpty()) {
            System.out.println("No past Reservations found for this customer.");
        } else {
            for (Reservation reservation : pastReservations) {
                System.out.println(reservation);
            }
        }
        System.out.println("Exiting viewPastReservations");
    }
    
    public Payment createPayment(double price, List<PaymentProcessorCompany> companies) {
        Scanner scanner = new Scanner(System.in);
        String paymentMethod = "";
        PaymentProcessorCompany paymentProcessorCompany = null;
        GregorianCalendar reservationDate = new GregorianCalendar();
        // Menu for payment method
        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        int paymentMethodChoice = Integer.parseInt(scanner.nextLine());

        switch (paymentMethodChoice) {
            case 1:
                paymentMethod = "cash";
                break;
            case 2:
                paymentMethod = "credit-card";
                break;
            default:
                System.out.println("Invalid choice, defaulting to Cash.");
                paymentMethod = "cash";
                break;
        }

        // Menu for payment processor company
        if (paymentMethod.equals("credit-card")) {
            System.out.println("Choose payment processor company:");
            for (PaymentProcessorCompany company : companies) {
                System.out.println(company.getCompanyId() + ". " + company.getName());
            }
            int paymentProcessorChoice = Integer.parseInt(scanner.nextLine());

            paymentProcessorCompany = companies.stream()
                    .filter(c -> c.getCompanyId() == paymentProcessorChoice)
                    .findFirst()
                    .orElse(null);

            if (paymentProcessorCompany == null) {
                System.out.println("Invalid choice, defaulting to Visa.");
                paymentProcessorCompany = companies.stream()
                        .filter(c -> c.getName().equals("Visa"))
                        .findFirst()
                        .orElse(null);
            }
        }

        Payment payment = new Payment(paymentMethod, reservationDate, price, this, paymentProcessorCompany);
        System.out.println("Payment created successfully: " + payment);
        sendPayment(payment);

        return payment;
    }
    
    public static Customer authenticate(List<Customer> customers, String username, String password) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }
    
    public void sendPayment(Payment payment) {
        PaymentProcessorCompany company = payment.getPaymentProcessorCompany();
        if (company != null) {
        company.payments.add(payment);
        System.out.println("Payment sent successfully to the payment processor company.");
        }
        else {
        System.out.println("No payment processor company selected. Cannot send payment.");
        }
        
    }

    
    // Method to search for available rooms
       public List<Room> searchRoom(List<Hotel> hotels) {
           Scanner scanner = new Scanner(System.in);

           // Prompt the user to select a hotel by ID
           System.out.println("Select a hotel by ID:");
           for (Hotel hotel : hotels) {
               System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
           }
           int hotelId = Integer.parseInt(scanner.nextLine());
           Hotel selectedHotel = null;
           for (Hotel hotel : hotels) {
               if (hotel.getHotelId() == hotelId) {
                   selectedHotel = hotel;
                   break;
               }
           }
           if (selectedHotel == null) {
               System.out.println("Invalid hotel ID. No hotel found.");
               return new ArrayList<>();
           }

           // Prompt the user to enter the search criteria
           System.out.println("Enter start date (yyyy-mm-dd):");
           String[] startDateInput = scanner.nextLine().split("-");
           GregorianCalendar startDate = new GregorianCalendar(
                   Integer.parseInt(startDateInput[0]),
                   Integer.parseInt(startDateInput[1]) - 1,
                   Integer.parseInt(startDateInput[2])
           );

           System.out.println("Enter end date (yyyy-mm-dd):");
           String[] endDateInput = scanner.nextLine().split("-");
           GregorianCalendar endDate = new GregorianCalendar(
                   Integer.parseInt(endDateInput[0]),
                   Integer.parseInt(endDateInput[1]) - 1,
                   Integer.parseInt(endDateInput[2])
           );

           System.out.println("Enter room capacity:");
           int roomCapacity = Integer.parseInt(scanner.nextLine());

           // Find available rooms in the selected hotel
           List<Room> availableRooms = new ArrayList<>();
           for (Room room : selectedHotel.getRooms()) {
               if (room.getMaxCapacity() >= roomCapacity && selectedHotel.checkAvailableRooms(startDate, endDate).contains(room.getRoomId())) {
                   availableRooms.add(room);
               }
           }

           if (availableRooms.isEmpty()) {
               System.out.println("No available rooms found.");
           } else {
               System.out.println("Available rooms:");
               for (Room room : availableRooms) {
                   System.out.println(room);
               }
           }

           return availableRooms;
       }
       
       
       // Method to add a room to a selected hotel by prompting the user for arguments
       public void addRoom(List<Hotel> hotels) {
           Scanner scanner = new Scanner(System.in);

           // Prompt the user to select a hotel by ID
           System.out.println("Select a hotel by ID to add a room:");
           for (Hotel hotel : hotels) {
               System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
           }
           int hotelId = Integer.parseInt(scanner.nextLine());
           Hotel selectedHotel = null;
           for (Hotel hotel : hotels) {
               if (hotel.getHotelId() == hotelId) {
                   selectedHotel = hotel;
                   break;
               }
           }
           if (selectedHotel == null) {
               System.out.println("Invalid hotel ID. No hotel found.");
               return;
           }

           System.out.println("Enter maximum capacity for the room:");
           int maxCapacity = Integer.parseInt(scanner.nextLine());

           System.out.println("Enter price per night for the room:");
           double pricePerNight = Double.parseDouble(scanner.nextLine());

           Room room = new Room(maxCapacity, pricePerNight);
           selectedHotel.addRoom(room);
           System.out.println("Room added successfully: " + room.toString());
       }
       
  
       
       // Method to update room details by prompting the user for inputs
       public void updateRoomDetails(List<Hotel> hotels) {
           Scanner scanner = new Scanner(System.in);

           // Prompt the user to select a hotel by ID
           System.out.println("Select a hotel by ID to update room details:");
           for (Hotel hotel : hotels) {
               System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
           }
           int hotelId = Integer.parseInt(scanner.nextLine());
           Hotel selectedHotel = null;
           for (Hotel hotel : hotels) {
               if (hotel.getHotelId() == hotelId) {
                   selectedHotel = hotel;
                   break;
               }
           }
           if (selectedHotel == null) {
               System.out.println("Invalid hotel ID. No hotel found.");
               return;
           }

           System.out.println("Enter the room ID to update:");
           int roomId = Integer.parseInt(scanner.nextLine());
           Room room = selectedHotel.getRoomById(roomId);
           if (room == null) {
               System.out.println("Error: Room not found.");
               return;
           }

           System.out.println("Which field do you want to update?");
           System.out.println("1. Max Capacity");
           System.out.println("2. Price Per Night");
           int fieldChoice = Integer.parseInt(scanner.nextLine());

           switch (fieldChoice) {
               case 1:
                   System.out.println("Enter the new value for Max Capacity:");
                   int newMaxCapacity = Integer.parseInt(scanner.nextLine());
                   room.setMaxCapacity(newMaxCapacity);
                   break;
               case 2:
                   System.out.println("Enter the new value for Price Per Night:");
                   double newPricePerNight = Double.parseDouble(scanner.nextLine());
                   room.setPricePerNight(newPricePerNight);
                   break;
               default:
                   System.out.println("Error: Invalid choice.");
                   return;
           }
           System.out.println("Room details updated successfully: " + room.toString());
       }
       
       
       
}
