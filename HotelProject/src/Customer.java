import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private String telephone;
    private String name;
    private String id;
    private String address;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(String telephone, String name, String id, String address) {
        this.telephone = telephone;
        this.name = name;
        this.id = id;
        this.address = address;
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
    
    public void updateProfile() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Which field do you want to update? (telephone/name/id/address)");
        String field = scanner.nextLine().toLowerCase();
        
        System.out.println("Enter the new value for " + field + ":");
        String newValue = scanner.nextLine();
        
        switch (field) {
            case "telephone":
                setTelephone(newValue);
                break;
            case "name":
                setName(newValue);
                break;
            case "id":
                setId(newValue);
                break;
            case "address":
                setAddress(newValue);
                break;
            default:
                System.out.println("Invalid field specified.");
                break;
        }
        
        System.out.println("Profile updated: " + this);
    }
    
    public void createInvitation(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter invitation ID:");
        String invitationId = scanner.nextLine();

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
        String roomId = scanner.nextLine();

        System.out.println("Enter price:");
        double price = Double.parseDouble(scanner.nextLine());

        GregorianCalendar invitationDate = new GregorianCalendar(); // Today's date

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

        // Create and save the invitation
        Invitation invitation = new Invitation(
                invitationId, numOfGuests, invitationDate, startDate, endDate, roomId, price , this, hotel
        );

        hotel.addInvitation(invitation);
        System.out.println("Invitation created successfully.");
    }
    
 // Method to view past invitations
    public List<Invitation> viewPastInvitations(Hotel hotel) {
        List<Invitation> pastInvitations = new ArrayList<>();
        for (Invitation invitation : hotel.getInvitations()) {
            if (invitation.getCustomer().getId().equals(this.id)) {
                pastInvitations.add(invitation);
            }
        }
        return pastInvitations;
    }
    
    // Method to create a payment
    public void createPayment(String paymentId, String paymentMethod, double price, PaymentProcessorCompany paymentProcessorCompany, Hotel Hotel) {
        GregorianCalendar paymentDate = new GregorianCalendar(); // Today's date
        Payment payment = new Payment(paymentId, paymentMethod, paymentDate, price, this, paymentProcessorCompany);
        System.out.println("Payment created successfully: " + payment);
        paymentProcessorCompany.addPayment(payment,Hotel);
    }



    // Optional: toString method for easier debugging and logging
    public String toString() {
        return "Customer{" +
                "telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
