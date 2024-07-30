public class Room {
	private int maxCapacity;
	private int roomId;
	private static int nextRoomId = 1;
	private double pricePerNight;
	private boolean available;

	// Default constructor
	public Room() {
		this.roomId = nextRoomId++;
		this.available = true;
	}

	// Parameterized constructor
	public Room(int maxCapacity , double pricePerNight) {
		this();
		this.maxCapacity = maxCapacity;
		this.pricePerNight = pricePerNight;
		this.available = true;
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

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean getAvailable() {
		return available;
	} 

	//methods
	public boolean capacityCheck(int numOfGuests) {
		return numOfGuests <= maxCapacity;
	}

	@Override
	public String toString() {
		return "Hotel Room{" +
				"Room Id='" + roomId + '\'' +
				", Max Capacity=" + maxCapacity +
				", Price Per Night=" + pricePerNight +
				'}';
	}


}
