import java.util.GregorianCalendar;
import java.util.Scanner;

public class Invitation {
    private String invitationId;
    private int numOfGuests;
    private GregorianCalendar invitationDate;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String roomId;
    private double price;
    private Customer customer;
    private Hotel hotel;

    // Default constructor
    public Invitation() {
    }

    // Parameterized constructor
    public Invitation(String invitationId, int numOfGuests, GregorianCalendar invitationDate, GregorianCalendar startDate, GregorianCalendar endDate, String roomId, double price, Customer customer, Hotel hotel) {
        this.invitationId = invitationId;
        setNumOfGuests(numOfGuests); // Use setter to validate numOfGuests
        this.invitationDate = invitationDate;
        setStartDate(startDate); // Use setter to validate startDate
        setEndDate(endDate); // Use setter to validate endDate
        this.roomId = roomId;
        this.price = price;
        this.customer = customer;
        this.hotel = hotel;
    }

    // Getters and Setters
    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests) {
        if (numOfGuests > 0) {
            this.numOfGuests = numOfGuests;
        } else {
            System.out.println("Error: Number of guests must be greater than 0.");
        }
    }

    public GregorianCalendar getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(GregorianCalendar invitationDate) {
        this.invitationDate = invitationDate;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        if (this.endDate != null && startDate.after(this.endDate)) {
            System.out.println("Error: Start date must be before end date.");
        } else {
            this.startDate = startDate;
        }
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        if (this.startDate != null && endDate.before(this.startDate)) {
            System.out.println("Error: End date must be after start date.");
        } else {
            this.endDate = endDate;
        }
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Invitation{" +
                "invitationId='" + invitationId + '\'' +
                ", numOfGuests=" + numOfGuests +
                ", invitationDate=" + (invitationDate != null ? invitationDate.getTime() : null) +
                ", startDate=" + (startDate != null ? startDate.getTime() : null) +
                ", endDate=" + (endDate != null ? endDate.getTime() : null) +
                ", roomId='" + roomId + '\'' +
                ", price=" + price +
                ", customer=" + customer +
                ", hotel=" + hotel +
                '}';
    }
    
 // Method to add guests
    public String addGuest(int additionalGuests) {
        Room room = hotel.getRoomById(roomId);
        if (room == null) {
            return "Error: Room not found.";
        }
        int newGuestCount = numOfGuests + additionalGuests;
        if (newGuestCount > room.getMaxCapacity()) {
            return "Error: Number of guests exceeds room capacity.";
        }
        numOfGuests = newGuestCount;
        return "Guests added successfully. New number of guests: " + numOfGuests;
    }

    // Method to remove guests
    public String removeGuest(int guestsToRemove) {
        int newGuestCount = numOfGuests - guestsToRemove;
        if (newGuestCount < 1) {
            return "Error: Number of guests cannot be less than 1.";
        }
        numOfGuests = newGuestCount;
        return "Guests removed successfully. New number of guests: " + numOfGuests;
    }
    
    // Method to update invitation details
    public void updateInvitationDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which field do you want to update? (numOfGuests/startDate/endDate/roomId/price)");
        String field = scanner.nextLine().toLowerCase();

        System.out.println("Enter the new value for " + field + ":");
        switch (field) {
            case "numofguests":
                int newNumOfGuests = Integer.parseInt(scanner.nextLine());
                Room room = hotel.getRoomById(roomId);
                if (room == null || newNumOfGuests > room.getMaxCapacity()) {
                    System.out.println("Error: Number of guests exceeds room capacity or room not found.");
                } else {
                    setNumOfGuests(newNumOfGuests);
                    System.out.println("numOfGuests updated successfully.");
                }
                break;
            case "startdate":
                String[] startDateInput = scanner.nextLine().split("-");
                GregorianCalendar newStartDate = new GregorianCalendar(
                        Integer.parseInt(startDateInput[0]),
                        Integer.parseInt(startDateInput[1]) - 1,
                        Integer.parseInt(startDateInput[2])
                );
                setStartDate(newStartDate);
                System.out.println("startDate updated successfully.");
                break;
            case "enddate":
                String[] endDateInput = scanner.nextLine().split("-");
                GregorianCalendar newEndDate = new GregorianCalendar(
                        Integer.parseInt(endDateInput[0]),
                        Integer.parseInt(endDateInput[1]) - 1,
                        Integer.parseInt(endDateInput[2])
                );
                setEndDate(newEndDate);
                System.out.println("endDate updated successfully.");
                break;
            case "roomid":
                String newRoomId = scanner.nextLine();
                setRoomId(newRoomId);
                System.out.println("roomId updated successfully.");
                break;
            case "price":
                double newPrice = Double.parseDouble(scanner.nextLine());
                setPrice(newPrice);
                System.out.println("price updated successfully.");
                break;
            default:
                System.out.println("Invalid field specified.");
                break;
        }
    }
    
    public void sendInvitation(Customer customer) {
        String phoneNumber = customer.getTelephone();
        // Methodically send the message to the customer's phone number
        System.out.println("Sending invitation to phone number: " + phoneNumber);
        // Simulating sending message
        System.out.println("Invitation sent successfully to " + phoneNumber);
}
}
