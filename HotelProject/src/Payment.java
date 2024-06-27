import java.util.GregorianCalendar;

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private GregorianCalendar paymentDate;
    private double price;
    private Customer customer;
    private PaymentProcessorCompany paymentProcessorCompany;

    // Default constructor
    public Payment() {
    }

    // Parameterized constructor
    public Payment(String paymentId, String paymentMethod, GregorianCalendar paymentDate, double price, Customer customer, PaymentProcessorCompany paymentProcessorCompany) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.price = price;
        this.customer = customer;
        this.paymentProcessorCompany = paymentProcessorCompany;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public GregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(GregorianCalendar paymentDate) {
        this.paymentDate = paymentDate;
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

    public PaymentProcessorCompany getPaymentProcessorCompany() {
        return paymentProcessorCompany;
    }

    public void setPaymentProcessorCompany(PaymentProcessorCompany paymentProcessorCompany) {
        this.paymentProcessorCompany = paymentProcessorCompany;
    }

    // Optional: toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + (paymentDate != null ? paymentDate.getTime() : null) +
                ", price=" + price +
                ", customer=" + customer +
                ", paymentProcessorCompany=" + paymentProcessorCompany +
                '}';
    }
}
