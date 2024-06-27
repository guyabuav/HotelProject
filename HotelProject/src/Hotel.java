public class Hotel {
    private String telephone;
    private int stars;
    private String hotelId;
    private int numOfRooms;
    private String city;
    private String hotelName;

    // Default constructor
    public Hotel() {
    }

    // Parameterized constructor
    public Hotel(String telephone, int stars, String hotelId, int numOfRooms, String city, String hotelName) {
        this.telephone = telephone;
        this.stars = stars;
        this.hotelId = hotelId;
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
                '}';
    }
}
