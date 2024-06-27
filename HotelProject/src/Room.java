public class Room {
    private int maxCapacity;
    private String roomId;

    // Default constructor
    public Room() {
    }

    // Parameterized constructor
    public Room(int maxCapacity, String roomId) {
        this.maxCapacity = maxCapacity;
        this.roomId = roomId;
    }

    // Getters and Setters
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Room{" +
                "maxCapacity=" + maxCapacity +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
