import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class PaymentProcessorCompany {
    private static int nextCompanyId = 1;
    private int companyId;
    private String telephone;
    private String address;
    public String companyName;
    public List<Payment> payments;

    // Default constructor
    public PaymentProcessorCompany() {
    }

    // Parameterized constructor
    public PaymentProcessorCompany(String telephone, String address, String name) {
    	
        this.companyId = nextCompanyId++;
        this.telephone = telephone;
        this.address = address;
        this.payments = new ArrayList<>();
        this.companyName = name;
    }

    
    public List<Payment> getPayments() {
        return payments;
    }
    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getName() {
        return this.companyName;
    }

    public void setName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

    // Method to validate a payment
    public String paymentValidation(Payment payment) {
        if (payment.getPaymentId() == 0 || payment.getPaymentId() < 0) {
            return "Error: Payment ID is null or empty.";
        }
        if (payment.getPrice() <= 0) {
            return "Error: Invalid price.";
        }
        if (payment.getPaymentDate().after(new GregorianCalendar())) {
            return "Error: Payment date cannot be in the future.";
        }
        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().isEmpty()) {
            return "Error: Payment method is null or empty.";
        }
        return "Payment confirmed";
    }

    // Method to send payment confirmation
    public PaymentConfirmation sendPaymentConfirmation(PaymentConfirmation confirmation) {
        // Simulating sending payment confirmation to the hotel
        System.out.println("Sending payment confirmation: " + confirmation);
        return confirmation;
    }
    

    // Method to add a payment
    public void addPaymentConfirmation(Payment payment, Hotel hotel) {
        String validationMessage = paymentValidation(payment);
        if (validationMessage.equals("Payment confirmed")) {
            payments.add(payment);
            PaymentConfirmation confirmation = new PaymentConfirmation(
                "CONF" + payment.getPaymentId(), // Generate a confirmation ID
                payment.getPaymentMethod(), // Use the payment method from the payment
                new GregorianCalendar(), // Use the current date as the confirmation date
                payment.getPrice(), // Use the price from the payment
                this, // PaymentProcessorCompany reference
                hotel // Hotel reference
            );
            hotel.addPaymentConfirmation(confirmation);
            sendPaymentConfirmation(confirmation);
        } else {
            System.out.println(validationMessage);
        }
    }

    @Override
    public String toString() {
        return "PaymentProcessorCompany{" +
                "companyId='" + companyId + '\'' +
                "companyName='" + companyName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
