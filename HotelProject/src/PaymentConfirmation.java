import java.util.GregorianCalendar;

public class PaymentConfirmation {
    private String confirmationId;
    private String paymentMethod;
    private GregorianCalendar confirmationDate;
    private double price;
    private PaymentProcessorCompany paymentProcessorCompany;
    private Hotel hotel;

    // Default constructor
    public PaymentConfirmation() {
    }

    // Parameterized constructor
    public PaymentConfirmation(String confirmationId, String paymentMethod, GregorianCalendar confirmationDate, double price, PaymentProcessorCompany paymentProcessorCompany, Hotel hotel) {
        this.confirmationId = confirmationId;
        this.paymentMethod = paymentMethod;
        this.confirmationDate = confirmationDate;
        this.price = price;
        this.paymentProcessorCompany = paymentProcessorCompany;
        this.hotel = hotel;
    }

    // Getters and Setters
    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public GregorianCalendar getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(GregorianCalendar confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentProcessorCompany getPaymentProcessorCompany() {
        return paymentProcessorCompany;
    }

    public void setPaymentProcessorCompany(PaymentProcessorCompany paymentProcessorCompany) {
        this.paymentProcessorCompany = paymentProcessorCompany;
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
        return "PaymentConfirmation{" +
                "confirmationId='" + confirmationId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", confirmationDate=" + (confirmationDate != null ? confirmationDate.getTime() : null) +
                ", price=" + price +
                ", paymentProcessorCompany=" + paymentProcessorCompany +
                ", hotel=" + hotel +
                '}';
    }
}
