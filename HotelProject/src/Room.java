public class Room {
    private int maxCapacity;
    private int roomId;
    private static int nextRoomId = 1;
    private double pricePerNight;

    // Default constructor
    public Room() {
        this.roomId = nextRoomId++;
    }

    // Parameterized constructor
    public Room(int maxCapacity , double pricePerNight) {
    	this();
        this.maxCapacity = maxCapacity;
        this.pricePerNight = pricePerNight;
    }

    // Getters and Setters
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean capacityCheck(int numOfGuests) {
        return numOfGuests <= maxCapacity;
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Room{" +
                "maxCapacity=" + maxCapacity +
                ", roomId='" + roomId + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }

    
}
