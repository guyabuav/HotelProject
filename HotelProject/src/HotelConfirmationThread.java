import java.util.*;

public class HotelConfirmationThread extends Thread {
	private Hotel hotel;

	public HotelConfirmationThread(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public void run() {
		while (true) {
			try {
				checkForNewConfirmations();
				Thread.sleep(10000); // Sleep for 10 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkForNewConfirmations() {
		List<PaymentConfirmation> confirmations = hotel.getConfirmation();
		for (PaymentConfirmation confirmation : confirmations) {
			if (!confirmation.isNotified()) {
				sendConfirmationToCustomer(confirmation);
				confirmation.setNotified(true); // Mark as notified
			}
		}
	}

	private void sendConfirmationToCustomer(PaymentConfirmation confirmation) {
		Customer customer = confirmation.getPaymentProcessorCompany().getPayments().stream()
				.filter(payment -> payment.getPrice() == confirmation.getPrice())
				.map(Payment::getCustomer)
				.findFirst()
				.orElse(null);

		if (customer != null) {
			// Simulate sending a message to the customer's phone
			System.out.println("Sending confirmation to customer: " + customer.getTelephone() +
					" with confirmation ID: " + confirmation.getConfirmationId());
		}
	}
}
