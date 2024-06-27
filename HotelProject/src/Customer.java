
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
