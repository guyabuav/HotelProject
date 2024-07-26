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

//Optional: toString method for easier debugging and logging
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

//Method to create a reservation
public void createReservation(List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
 Hotel hotel = selectHotel(hotels);
 if (hotel == null) {
     return;
 }

 Scanner scanner = new Scanner(System.in);

 int numOfGuests;
 do {
     System.out.println("Enter number of guests:");
     numOfGuests = Integer.parseInt(scanner.nextLine());
     if (numOfGuests <= 0) {
         System.out.println("Error: Number of guests must be greater than 0.");
     }
 } while (numOfGuests <= 0);

 GregorianCalendar startDate;
 GregorianCalendar endDate;
 GregorianCalendar today = new GregorianCalendar();
 today.set(GregorianCalendar.HOUR_OF_DAY, 0);
 today.set(GregorianCalendar.MINUTE, 0);
 today.set(GregorianCalendar.SECOND, 0);
 today.set(GregorianCalendar.MILLISECOND, 0);

 while (true) {
     System.out.println("Enter start date (yyyy-mm-dd):");
     String[] startDateInput = scanner.nextLine().split("-");
     if (!isValidDate(startDateInput)) {
         System.out.println("Error: Invalid date format or range. Please enter a valid date in yyyy-mm-dd format.");
         continue;
     }
     startDate = new GregorianCalendar(
             Integer.parseInt(startDateInput[0]),
             Integer.parseInt(startDateInput[1]) - 1,
             Integer.parseInt(startDateInput[2])
     );

     System.out.println("Enter end date (yyyy-mm-dd):");
     String[] endDateInput = scanner.nextLine().split("-");
     if (!isValidDate(endDateInput)) {
         System.out.println("Error: Invalid date format or range. Please enter a valid date in yyyy-mm-dd format.");
         continue;
     }
     endDate = new GregorianCalendar(
             Integer.parseInt(endDateInput[0]),
             Integer.parseInt(endDateInput[1]) - 1,
             Integer.parseInt(endDateInput[2])
     );

     if (startDate.after(endDate)) {
         System.out.println("Error: Start date must be before end date.");
     } else if (startDate.before(today) || endDate.before(today)) {
         System.out.println("Error: Dates cannot be in the past.");
     } else {
         break; // Dates are valid, exit the loop
     }
 }

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

//Helper method to validate date format and ranges
private boolean isValidDate(String[] dateInput) {
 if (dateInput.length != 3) return false;
 try {
     int year = Integer.parseInt(dateInput[0]);
     int month = Integer.parseInt(dateInput[1]);
     int day = Integer.parseInt(dateInput[2]);
     GregorianCalendar today = new GregorianCalendar();
     int currentYear = today.get(GregorianCalendar.YEAR);
     
     if (year < currentYear) return false;
     if (month < 1 || month > 12) return false;
     if (day < 1 || day > 31) return false;
     
     return true;
 } catch (NumberFormatException e) {
     return false;
 }
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
    boolean validPaymentMethod = false;
    while (!validPaymentMethod) {
        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        int paymentMethodChoice = Integer.parseInt(scanner.nextLine());
        
        switch (paymentMethodChoice) {
            case 1:
                paymentMethod = "cash";
                validPaymentMethod = true;
                break;
            case 2:
                paymentMethod = "credit-card";
                validPaymentMethod = true;
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    // Menu for payment processor company if credit-card is chosen
    if (paymentMethod.equals("credit-card")) {
        boolean validProcessor = false;
        while (!validProcessor) {
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
                System.out.println("Invalid choice, please try again.");
            } else {
                validProcessor = true;
            }
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
    if (company != null ) {
    company.payments.add(payment);
    System.out.println("Payment sent successfully to the payment processor company.");
    }
    if (company == null && payment.getPaymentMethod().equals("cash")) {
        System.out.println("Payment method cash sent to the hotel");
    }
    if (company == null && payment.getPaymentMethod() != "cash") {
    System.out.println("No payment processor company selected. Cannot send payment.");
    }
    
}


//Method to search for available rooms
public List<Room> searchRoom(List<Hotel> hotels) {
 Scanner scanner = new Scanner(System.in);

 // Prompt the user to select a hotel by ID
 System.out.println("Select a hotel by ID:");
 for (Hotel hotel : hotels) {
     System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
 }
 int hotelId = Integer.parseInt(scanner.nextLine());
 Hotel selectedHotel = Hotel.searchHotelById(hotels, hotelId);
 if (selectedHotel == null) {
     System.out.println("Invalid hotel ID. No hotel found.");
     return new ArrayList<>();
 }

 GregorianCalendar startDate;
 GregorianCalendar endDate;
 GregorianCalendar today = new GregorianCalendar();
 today.set(GregorianCalendar.HOUR_OF_DAY, 0);
 today.set(GregorianCalendar.MINUTE, 0);
 today.set(GregorianCalendar.SECOND, 0);
 today.set(GregorianCalendar.MILLISECOND, 0);

 while (true) {
     System.out.println("Enter start date (yyyy-mm-dd):");
     String[] startDateInput = scanner.nextLine().split("-");
     if (!isValidDate(startDateInput)) {
         System.out.println("Error: Invalid date format or range. Please enter a valid date in yyyy-mm-dd format.");
         continue;
     }
     startDate = new GregorianCalendar(
             Integer.parseInt(startDateInput[0]),
             Integer.parseInt(startDateInput[1]) - 1,
             Integer.parseInt(startDateInput[2])
     );

     System.out.println("Enter end date (yyyy-mm-dd):");
     String[] endDateInput = scanner.nextLine().split("-");
     if (!isValidDate(endDateInput)) {
         System.out.println("Error: Invalid date format or range. Please enter a valid date in yyyy-mm-dd format.");
         continue;
     }
     endDate = new GregorianCalendar(
             Integer.parseInt(endDateInput[0]),
             Integer.parseInt(endDateInput[1]) - 1,
             Integer.parseInt(endDateInput[2])
     );

     if (startDate.after(endDate)) {
         System.out.println("Error: Start date must be before end date.");
     } else if (startDate.before(today) || endDate.before(today)) {
         System.out.println("Error: Dates cannot be in the past.");
     } else {
         break; // Dates are valid, exit the loop
     }
 }

 int roomCapacity;
 do {
     System.out.println("Enter room capacity:");
     roomCapacity = Integer.parseInt(scanner.nextLine());
     if (roomCapacity <= 0) {
         System.out.println("Error: Room capacity must be greater than 0.");
     }
 } while (roomCapacity <= 0);

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

//Method to add a room to a selected hotel by prompting the user for arguments
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

 int maxCapacity;
 do {
     System.out.println("Enter maximum capacity for the room:");
     maxCapacity = Integer.parseInt(scanner.nextLine());
     if (maxCapacity <= 0) {
         System.out.println("Error: Maximum capacity must be greater than 0.");
     }
 } while (maxCapacity <= 0);

 double pricePerNight;
 do {
     System.out.println("Enter price per night for the room:");
     pricePerNight = Double.parseDouble(scanner.nextLine());
     if (pricePerNight <= 0) {
         System.out.println("Error: Price per night must be greater than 0.");
     }
 } while (pricePerNight <= 0);

 Room room = new Room(maxCapacity, pricePerNight);
 selectedHotel.addRoom(room);
 System.out.println("Room added successfully: " + room.toString());
}

//Method to update room details by prompting the user for inputs
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
         int newMaxCapacity;
         do {
             System.out.println("Enter the new value for Max Capacity:");
             newMaxCapacity = Integer.parseInt(scanner.nextLine());
             if (newMaxCapacity <= 0) {
                 System.out.println("Error: Max capacity must be greater than 0.");
             }
         } while (newMaxCapacity <= 0);
         room.setMaxCapacity(newMaxCapacity);
         break;
     case 2:
         double newPricePerNight;
         do {
             System.out.println("Enter the new value for Price Per Night:");
             newPricePerNight = Double.parseDouble(scanner.nextLine());
             if (newPricePerNight <= 0) {
                 System.out.println("Error: Price per night must be greater than 0.");
             }
         } while (newPricePerNight <= 0);
         room.setPricePerNight(newPricePerNight);
         break;
     default:
         System.out.println("Error: Invalid choice.");
         return;
 }
 System.out.println("Room details updated successfully: " + room.toString());
}



  
          
public void addGuest(Hotel hotel, Reservation reservation) {
    Scanner scanner = new Scanner(System.in);
    int additionalGuests;
    do {
        System.out.println("Enter number of additional guests:");
        additionalGuests = Integer.parseInt(scanner.nextLine());
        if (additionalGuests <= 0) {
            System.out.println("Error: Number of additional guests must be greater than 0.");
        }
    } while (additionalGuests <= 0);

    int newGuestCount = reservation.getNumOfGuests() + additionalGuests;

    Room room = hotel.getRoomById(reservation.getRoomId());
    if (newGuestCount > room.getMaxCapacity()) {
        System.out.println("Error: Number of guests exceeds room capacity.");
        return;
    }

    reservation.setNumOfGuests(newGuestCount);
    System.out.println("Guests added successfully. New number of guests: " + newGuestCount);
}

public void removeGuest(Hotel hotel, Reservation reservation) {
    Scanner scanner = new Scanner(System.in);
    int guestsToRemove;
    do {
        System.out.println("Enter number of guests to remove:");
        guestsToRemove = Integer.parseInt(scanner.nextLine());
        if (guestsToRemove <= 0) {
            System.out.println("Error: Number of guests to remove must be greater than 0.");
        }
    } while (guestsToRemove <= 0);

    int newGuestCount = reservation.getNumOfGuests() - guestsToRemove;

    if (newGuestCount < 1) {
        System.out.println("Error: Number of guests cannot be less than 1.");
        return;
    }

    reservation.setNumOfGuests(newGuestCount);
    System.out.println("Guests removed successfully. New number of guests: " + newGuestCount);
}

public void editReservation(List<Hotel> hotels) {
    Scanner scanner = new Scanner(System.in);

    // Prompt the user to select a hotel by ID
    System.out.println("Select a hotel by ID:");
    for (Hotel hotel : hotels) {
        System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
    }
    int hotelId = Integer.parseInt(scanner.nextLine());
    Hotel selectedHotel = Hotel.searchHotelById(hotels, hotelId);
    if (selectedHotel == null) {
        System.out.println("Invalid hotel ID. No hotel found.");
        return;
    }

    // Prompt the user to select a reservation by ID
    System.out.println("Select a reservation by ID:");
    for (Reservation reservation : selectedHotel.getReservations()) {
        System.out.println(reservation.getReservationId() + ": Reservation for " + reservation.getNumOfGuests() + " guests from " + reservation.getStartDate().getTime() + " to " + reservation.getEndDate().getTime());
    }
    int reservationId = Integer.parseInt(scanner.nextLine());
    Reservation selectedReservation = Reservation.searchReservationById(selectedHotel.getReservations(), reservationId);
    if (selectedReservation == null) {
        System.out.println("Invalid reservation ID. No reservation found.");
        return;
    }

    // Prompt the user to select the field to edit
    System.out.println("Which field do you want to edit?");
    System.out.println("1. Number of Guests");
    System.out.println("2. Start Date");
    System.out.println("3. End Date");
    System.out.println("4. Room ID");
    System.out.println("5. Hotel ID (Cannot be changed)");
    System.out.println("6. Price (Cannot be changed)");
    int fieldChoice = Integer.parseInt(scanner.nextLine());

    GregorianCalendar today = new GregorianCalendar();
    today.set(GregorianCalendar.HOUR_OF_DAY, 0);
    today.set(GregorianCalendar.MINUTE, 0);
    today.set(GregorianCalendar.SECOND, 0);
    today.set(GregorianCalendar.MILLISECOND, 0);

    switch (fieldChoice) {
        case 1:
            System.out.println("Do you want to add or remove guests? (add/remove)");
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("add")) {
                addGuest(selectedHotel, selectedReservation);
            } else if (action.equals("remove")) {
                removeGuest(selectedHotel, selectedReservation);
            } else {
                System.out.println("Invalid action.");
            }
            break;
        case 2:
            while (true) {
                System.out.println("Enter new start date (yyyy-mm-dd):");
                String[] startDateInput = scanner.nextLine().split("-");
                if (!isValidDate(startDateInput)) {
                    System.out.println("Error: Invalid date format or range. Please enter a valid date in yyyy-mm-dd format.");
                    continue;
                }
                GregorianCalendar newStartDate = new GregorianCalendar(
                        Integer.parseInt(startDateInput[0]),
                        Integer.parseInt(startDateInput[1]) - 1,
                        Integer.parseInt(startDateInput[2])
                );

                if (newStartDate.after(selectedReservation.getEndDate())) {
                    System.out.println("Error: Start date must be before end date.");
                } else if (newStartDate.before(today)) {
                    System.out.println("Error: Start date cannot be in the past.");
                } else if (selectedReservation.validateDateChange(newStartDate, selectedReservation.getEndDate(), selectedReservation.getRoomId(), selectedHotel)) {
                    long oldDays = (selectedReservation.getEndDate().getTimeInMillis() - selectedReservation.getStartDate().getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1;
                    long newDays = (selectedReservation.getEndDate().getTimeInMillis() - newStartDate.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1;
                    double priceDifference = (newDays - oldDays) * selectedHotel.getRoomById(selectedReservation.getRoomId()).getPricePerNight();
                    selectedReservation.setStartDate(newStartDate);
                    selectedReservation.setPrice(selectedReservation.getPrice() + priceDifference);
                    System.out.println("Start date and price updated successfully.");
                    break;
                }
            }
            break;
        case 3:
            while (true) {
                System.out.println("Enter new end date (yyyy-mm-dd):");
                String[] endDateInput = scanner.nextLine().split("-");
                if (!isValidDate(endDateInput)) {
                    System.out.println("Error: Invalid date format or range. Please enter a valid date in yyyy-mm-dd format.");
                    continue;
                }
                GregorianCalendar newEndDate = new GregorianCalendar(
                        Integer.parseInt(endDateInput[0]),
                        Integer.parseInt(endDateInput[1]) - 1,
                        Integer.parseInt(endDateInput[2])
                );

                if (newEndDate.before(selectedReservation.getStartDate())) {
                    System.out.println("Error: End date must be after start date.");
                } else if (newEndDate.before(today)) {
                    System.out.println("Error: End date cannot be in the past.");
                } else if (selectedReservation.validateDateChange(selectedReservation.getStartDate(), newEndDate, selectedReservation.getRoomId(), selectedHotel)) {
                    long oldDays = (selectedReservation.getEndDate().getTimeInMillis() - selectedReservation.getStartDate().getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1;
                    long newDays = (newEndDate.getTimeInMillis() - selectedReservation.getStartDate().getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1;
                    double priceDifference = (newDays - oldDays) * selectedHotel.getRoomById(selectedReservation.getRoomId()).getPricePerNight();
                    selectedReservation.setEndDate(newEndDate);
                    selectedReservation.setPrice(selectedReservation.getPrice() + priceDifference);
                    System.out.println("End date and price updated successfully.");
                    break;
                }
            }
            break;
        case 4:
            while (true) {
                System.out.println("Enter new room ID:");
                int newRoomId = Integer.parseInt(scanner.nextLine());
                Room newRoom = selectedHotel.getRoomById(newRoomId);
                if (newRoom == null) {
                    System.out.println("Error: Room not found. Please enter a valid room ID.");
                    continue;
                }
                if (selectedReservation.validateDateChange(selectedReservation.getStartDate(), selectedReservation.getEndDate(), newRoomId, selectedHotel)) {
                    double oldRoomPrice = selectedHotel.getRoomById(selectedReservation.getRoomId()).getPricePerNight();
                    double newRoomPrice = newRoom.getPricePerNight();
                    long days = (selectedReservation.getEndDate().getTimeInMillis() - selectedReservation.getStartDate().getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1;
                    double priceDifference = (newRoomPrice - oldRoomPrice) * days;
                    selectedReservation.setPrice(selectedReservation.getPrice() + priceDifference);
                    selectedReservation.setRoomId(newRoomId);
                    System.out.println("Room ID and price updated successfully.");
                    break;
                }
            }
            break;
        case 5:
            System.out.println("Error: You must cancel the reservation and make a new one to change the hotel.");
            break;
        case 6:
            System.out.println("Error: Price cannot be changed.");
            break;
        default:
            System.out.println("Invalid choice.");
            break;
    }
}   
   
   public void cancelReservation(List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
	    Scanner scanner = new Scanner(System.in);

	    // Prompt the user to select a hotel by ID
	    System.out.println("Select a hotel by ID:");
	    for (Hotel hotel : hotels) {
	        System.out.println(hotel.getHotelId() + ": " + hotel.getHotelName() + " in " + hotel.getCity());
	    }
	    int hotelId = Integer.parseInt(scanner.nextLine());
	    Hotel selectedHotel = Hotel.searchHotelById(hotels, hotelId);
	    if (selectedHotel == null) {
	        System.out.println("Invalid hotel ID. No hotel found.");
	        return;
	    }

	    // Prompt the user to select a reservation by ID
	    System.out.println("Select a reservation by ID:");
	    for (Reservation reservation : selectedHotel.getReservations()) {
	        System.out.println(reservation.getReservationId() + ": Reservation for " + reservation.getNumOfGuests() + " guests from " + reservation.getStartDate().getTime() + " to " + reservation.getEndDate().getTime());
	    }
	    int reservationId = Integer.parseInt(scanner.nextLine());
	    Reservation selectedReservation = Reservation.searchReservationById(selectedHotel.getReservations(), reservationId);
	    if (selectedReservation == null) {
	        System.out.println("Invalid reservation ID. No reservation found.");
	        return;
	    }

	    // Cancel the reservation
	    selectedHotel.getReservations().remove(selectedReservation);
	    System.out.println("Reservation cancelled successfully.");

	    // Call the refundPayment method from the Payment class
    	    Payment.refundPayment(selectedReservation.getPrice(), this, companies);
    	}
   
   
   

   
   
   
   
   
   
   
}
