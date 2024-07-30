import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hotel {
    private String telephone;
    private int stars;
    private int hotelId;
    private static int nextHotelId = 1;
    public int numOfRooms;
    private String city;
    private String hotelName;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<PaymentConfirmation> confirmations;


    // Default constructor
    public Hotel() {
    	this.hotelId = nextHotelId++;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.confirmations = new ArrayList<>();
    }

    // Parameterized constructor
    public Hotel(String telephone, int stars, int numOfRooms, String city, String hotelName) {
    	   this();
           this.telephone = telephone;
           this.stars = stars;
           this.numOfRooms = numOfRooms;
           this.city = city;
           this.hotelName = hotelName;
    }

    // Getters and Setters
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        if (stars >= 1 && stars <= 5) {
            this.stars = stars;
        } else {
            System.out.println("Error: Stars should be between 1 and 5.");
        }
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> Reservations) {
        this.reservations = Reservations;
    }

    public void setConfirmations(List<PaymentConfirmation> confirmation) {
    	this.confirmations = confirmation;
    }
    
    public List<PaymentConfirmation> getConfirmation(){
    	return confirmations;
    }
    
    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", stars=" + stars +
                ", reservations=" + reservations.stream().map(r -> r.getReservationId()).collect(Collectors.toList()) + // Avoid full reservation objects
                ", city='" + city + '\'' +
                ", hotelName='" + hotelName + '\'' +
                '}';
    }

    
    // Get room by ID
    public Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null; // Room not found
    }

 // Check available rooms between start and end dates
    public List<Integer> checkAvailableRooms(GregorianCalendar startDate, GregorianCalendar endDate) {
        List<Integer> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean isAvailable = true;
            for (Reservation reservation : reservations) {
                if (reservation.getRoomId() == room.getRoomId()) {
                    if (!(endDate.before(reservation.getStartDate()) || startDate.after(reservation.getEndDate()))) {
                        isAvailable = false;
                        break;
                    }
                }
            }
            if (isAvailable) {
                availableRooms.add(room.getRoomId());
            }
        }
        return availableRooms;
    }

    // Add Reservation
    public void addReservation(Reservation Reservation) {
        reservations.add(Reservation);
    }
    
 // Method to return room details
    public String returnRoomDetails(int roomId) {
        Room room = getRoomById(roomId);
        if (room == null) {
            return "Error: Room not found.";
        }
        return room.toString();
    }

    // Method to update room details
    public String updateRoomDetails(int roomId) {
        Room room = getRoomById(roomId);
        if (room == null) {
            return "Error: Room not found.";
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which field do you want to update? (roomId/maxCapacity/pricePerNight)");
        String field = scanner.nextLine().toLowerCase();

        System.out.println("Enter the new value for " + field + ":");
        switch (field) {
            case "roomid":
                int newRoomId = Integer.parseInt(scanner.nextLine());
                if (getRoomById(newRoomId) != null) {
                    return "Error: Room with this new ID already exists.";
                }
                room.setRoomId(newRoomId);
                break;
            case "maxcapacity":
                int newMaxCapacity = Integer.parseInt(scanner.nextLine());
                room.setMaxCapacity(newMaxCapacity);
                break;
            case "pricepernight":
                double newPricePerNight = Double.parseDouble(scanner.nextLine());
                room.setPricePerNight(newPricePerNight);
                break;
            default:
                return "Error: Invalid field specified.";
        }
        return "Room details updated successfully: " + room.toString();
    }
    
 
    
 // Method to add a room by object
    public String addRoom(Room room) {
        if (getRoomById(room.getRoomId()) != null) {
            return "Error: Room with this ID already exists.";
        }
        rooms.add(room);
        numOfRooms++;
        return "Room added successfully: " + room.toString();
    }
    
    // Method to add a payment confirmation
    public void addPaymentConfirmation(PaymentConfirmation confirmation) {
    	confirmations.add(confirmation);
    }
    
    public static Hotel searchHotelById(List<Hotel> hotels, int hotelId) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelId() == hotelId) {
                return hotel;
            }
        }
        System.out.println("Hotel not found\n");
        return null;
    }
    
    public boolean hasPayment(Payment payment) {
        for (PaymentConfirmation confirmation : confirmations) {
            if (confirmation.getPaymentMethod().equals(payment.getPaymentMethod()) &&
                confirmation.getPrice() == payment.getPrice() &&
                confirmation.getPaymentProcessorCompany().equals(payment.getPaymentProcessorCompany())) {
                return true;
            }
        }
        return false;
    }
}


