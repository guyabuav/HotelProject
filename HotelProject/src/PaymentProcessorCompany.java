public class PaymentProcessorCompany {
    private String companyId;
    private String telephone;
    private String address;

    // Default constructor
    public PaymentProcessorCompany() {
    }

    // Parameterized constructor
    public PaymentProcessorCompany(String companyId, String telephone, String address) {
        this.companyId = companyId;
        this.telephone = telephone;
        this.address = address;
    }

    // Getters and Setters
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "PaymentProcessorCompany{" +
                "companyId='" + companyId + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
