import java.util.*;

public class Payment {
	private static int nextPaymentId = 1;
	private int paymentId;
	private String paymentMethod;
	private GregorianCalendar paymentDate;
	private double price;
	private Customer customer;
	private PaymentProcessorCompany paymentProcessorCompany;
	private boolean processed; // Add this field
	// Default constructor
	public Payment() {
	}

	// Parameterized constructor
	public Payment(String paymentMethod, GregorianCalendar paymentDate, double price, Customer customer, PaymentProcessorCompany paymentProcessorCompany) {
		this.paymentId = nextPaymentId++;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.price = price;
		this.customer = customer;
		this.paymentProcessorCompany = paymentProcessorCompany;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	// Getters and Setters
	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
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
				"Payment Id='" + paymentId + '\'' +
				", Payment Method='" + paymentMethod + '\'' +
				", Payment Date=" + (paymentDate != null ? paymentDate.getTime() : null) +
				", Price=" + price +
				", Customer=" + customer +
				", Processed=" + processed +
				", Payment Processor Company=" + paymentProcessorCompany +
				'}';
	}

	public static void refundPayment(double amount, Customer customer, List<PaymentProcessorCompany> companies) {
		// Send refund message to customer
		System.out.println("Refunding " + amount + " to customer " + customer.getName());

		// Delete the payment from the list of payments in PaymentProcessorCompany
		for (PaymentProcessorCompany company : companies) {
			company.getPayments().removeIf(payment -> payment.getCustomer().equals(customer) && payment.getPrice() == amount);
		}
	}

}
