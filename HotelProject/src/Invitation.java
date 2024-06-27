import java.util.GregorianCalendar;

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
}
