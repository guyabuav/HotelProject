import java.util.*;

public class PaymentProcessorThread extends Thread {
	private PaymentProcessorCompany paymentProcessorCompany;
	private List<Hotel> hotels;

	public PaymentProcessorThread(PaymentProcessorCompany paymentProcessorCompany, List<Hotel> hotels) {
		this.paymentProcessorCompany = paymentProcessorCompany;
		this.hotels = hotels;
	}

	@Override
	public void run() {
		while (true) {
			try {
				processPayments();
				Thread.sleep(10000); // Sleep for 10 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void processPayments() {
		List<Payment> payments = paymentProcessorCompany.getPayments();
		for (Payment payment : payments) {
			if (!payment.isProcessed()) {
				System.out.println("Processing payment: " + payment);
				String validationMessage = paymentProcessorCompany.paymentValidation(payment);
				if (validationMessage.equals("Payment confirmed")) {
					payment.setProcessed(true); // Mark the payment as processed
					Hotel hotel = findHotelByPayment(payment);
					if (hotel != null) {
						PaymentConfirmation confirmation = new PaymentConfirmation(
								"CONF" + payment.getPaymentId(),
								payment.getPaymentMethod(),
								new GregorianCalendar(),
								payment.getPrice(),
								paymentProcessorCompany,
								hotel
								);
						hotel.addPaymentConfirmation(confirmation);
						paymentProcessorCompany.sendPaymentConfirmation(confirmation);
					}
				} else {
					System.out.println(validationMessage);
				}
			}
		}
	}

	private Hotel findHotelByPayment(Payment payment) {
		for (Hotel hotel : hotels) {
			if (hotel.getReservations().stream()
					.anyMatch(reservation -> reservation.getCustomer().equals(payment.getCustomer()))) {
				return hotel;
			}
		}
		return null;
	}
}
