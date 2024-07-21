import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    private String telephone;
    private int stars;
    private String hotelId;
    public int numOfRooms;
    private String city;
    private String hotelName;
    private List<Room> rooms;
    private List<Invitation> invitations;

    // Default constructor
    public Hotel() {
        this.rooms = new ArrayList<>();
        this.invitations = new ArrayList<>();
    }

    // Parameterized constructor
    public Hotel(String telephone, int stars, String hotelId, int numOfRooms, String city, String hotelName) {
        this.telephone = telephone;
        this.stars = stars;
        this.hotelId = hotelId;
        this.numOfRooms = numOfRooms;
        this.city = city;
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.invitations = new ArrayList<>();
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

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
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

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Hotel{" +
                "telephone='" + telephone + '\'' +
                ", stars=" + stars +
                ", hotelId='" + hotelId + '\'' +
                ", numOfRooms=" + numOfRooms +
                ", city='" + city + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", rooms=" + rooms +
                ", invitations=" + invitations +
                '}';
    }

    
 // Method to search for available rooms
    public List<Room> searchRoom(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);

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

        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (room.getMaxCapacity() >= roomCapacity && hotel.checkAvailableRooms(startDate, endDate).contains(room.getRoomId())) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
    // Get room by ID
    public Room getRoomById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null; // Room not found
    }

    // Check available rooms between start and end dates
    public List<String> checkAvailableRooms(GregorianCalendar startDate, GregorianCalendar endDate) {
        List<String> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean isAvailable = true;
            for (Invitation invitation : invitations) {
                if (invitation.getRoomId().equals(room.getRoomId())) {
                    if (!(endDate.before(invitation.getStartDate()) || startDate.after(invitation.getEndDate()))) {
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

    // Add invitation
    public void addInvitation(Invitation invitation) {
        invitations.add(invitation);
    }
    
 // Method to return room details
    public String returnRoomDetails(String roomId) {
        Room room = getRoomById(roomId);
        if (room == null) {
            return "Error: Room not found.";
        }
        return room.toString();
    }

    // Method to add a room by arguments
    public String addRoom(String roomId, int maxCapacity) {
        if (getRoomById(roomId) != null) {
            return "Error: Room with this ID already exists.";
        }
        Room room = new Room(maxCapacity, roomId);
        rooms.add(room);
        numOfRooms++;
        return "Room added successfully: " + room.toString();
    }

    // Method to update room details
    public String updateRoomDetails(String roomId) {
        Room room = getRoomById(roomId);
        if (room == null) {
            return "Error: Room not found.";
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which field do you want to update? (roomId/maxCapacity)");
        String field = scanner.nextLine().toLowerCase();

        System.out.println("Enter the new value for " + field + ":");
        switch (field) {
            case "roomid":
                String newRoomId = scanner.nextLine();
                if (getRoomById(newRoomId) != null) {
                    return "Error: Room with this new ID already exists.";
                }
                room.setRoomId(newRoomId);
                break;
            case "maxcapacity":
                int newMaxCapacity = Integer.parseInt(scanner.nextLine());
                room.setMaxCapacity(newMaxCapacity);
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
}


