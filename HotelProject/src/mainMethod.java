import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


public class mainMethod {

	private static Room displayAvailableRooms(List<Hotel> hotels, Scanner scanner) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BOLD = "\u001B[1m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";

		List<Room> availableRooms = new ArrayList<>();
		int optionNumber = 1;
		System.out.println(ANSI_BOLD + "Available Rooms:" + ANSI_RESET);
		for (Hotel hotel : hotels) {
			System.out.println(ANSI_BLUE + "\nHotel: " + hotel.getHotelName() + " (" + hotel.getCity() + ")" + ANSI_RESET);
			for (Room room : hotel.getRooms()) {
				if (room.getAvailable()) {
					System.out.println(optionNumber + ". Room for " + room.getMaxCapacity()+ " at $" + room.getPricePerNight() + " per night");
					availableRooms.add(room); // Store the room
					optionNumber++;
				}
			}
		}
		System.out.println(ANSI_BOLD+"\nWhich offer would you like to order? (enter 0 to go back)"+ANSI_RESET);
		int choice = scanner.nextInt();
		if (choice > 0 && choice <= availableRooms.size()) {
			Room selectedRoom = availableRooms.get(choice - 1);	
			return selectedRoom;
		} else if (choice == 0) {
			System.out.println("Going back...\n");
			return null;
		} else {
			System.out.println(ANSI_RED + "Invalid option. Please try again.\n" + ANSI_RESET);
			return null;
		}

	}

	private static Customer signUp(){
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BOLD = "\u001B[1m";
		final String ANSI_BLUE = "\u001B[34m";
		Customer c = new Customer();
		Dialog dialog = new Dialog((Frame) null, "Update Profile", true); // Modal dialog

		dialog.setLayout(new GridLayout(7, 2));

		Label labelTelephone = new Label("Telephone:");
		TextField tfTelephone = new TextField(c.getTelephone());
		dialog.add(labelTelephone);
		dialog.add(tfTelephone);

		Label labelName = new Label("Name:");
		TextField tfName = new TextField(c.getName());
		dialog.add(labelName);
		dialog.add(tfName);

		Label labelId = new Label("ID:");
		TextField tfId = new TextField(c.getId());
		dialog.add(labelId);
		dialog.add(tfId);

		Label labelAddress = new Label("Address:");
		TextField tfAddress = new TextField(c.getAddress());
		dialog.add(labelAddress);
		dialog.add(tfAddress);

		Label labelUsername = new Label("Username:");
		TextField tfUsername = new TextField(c.getUsername());
		dialog.add(labelUsername);
		dialog.add(tfUsername);

		Label labelPassword = new Label("Password:");
		TextField tfPassword = new TextField(c.getPassword());
		dialog.add(labelPassword);
		dialog.add(tfPassword);

		Button btnUpdate = new Button("Update");
		dialog.add(new Label());
		dialog.add(btnUpdate);

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setTelephone(tfTelephone.getText());
				c.setName(tfName.getText());
				c.setId(tfId.getText()); 
				c.setAddress(tfAddress.getText());
				c.setUsername(tfUsername.getText());
				c.setPassword(tfPassword.getText());
				dialog.dispose(); // Close the dialog
			}
		});

		dialog.setSize(400, 300);
		dialog.setVisible(true);
		return c;
	}

	public static void main(String[] args) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BOLD = "\u001B[1m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";

		// DB
		Customer c1 = new Customer("0525515555", "guy ab", "12345", "Haifa", "guyab", "12345");
		Customer c2 = new Customer("0521234567", "admin user", "admin123", "Admin City", "admin", "adminpass");
		Customer c3 = new Customer("0521234111", "guy abua", "123457", "Netanya", "guyabua", "123456");
		Customer c4 = new Customer("0521112311", "guy abuav", "123458", "Kadima", "admin", "admin");
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
		Hotel h1 = new Hotel("091231231", 5, 0, "Eilat", "Agamim");
		Hotel h2 = new Hotel("095551231", 4, 0, "Kineret", "Salonim");
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(h1);
		hotels.add(h2);
		Room r1 = new Room(5, 200);
		Room r2 = new Room(3, 200);
		Room r3 = new Room(4, 300);
		Room r4 = new Room(5, 400);
		Room r5 = new Room(7, 700);
		Room r6 = new Room(2, 1000);
		Room r7 = new Room(2, 150);
		h1.addRoom(r1);
		h1.addRoom(r2);
		h1.addRoom(r3);
		h1.addRoom(r4);
		h2.addRoom(r5);
		h2.addRoom(r6);
		h2.addRoom(r7);
		PaymentProcessorCompany comp1 = new PaymentProcessorCompany("0523123123", "ashdod", "MasterCard");
		PaymentProcessorCompany comp2 = new PaymentProcessorCompany("0523123123", "haifa", "Visa");
		PaymentProcessorCompany comp3 = new PaymentProcessorCompany("0523123123", "jerusalem", "Pepper");
		List<PaymentProcessorCompany> companies = new ArrayList<>();
		companies.add(comp1);
		companies.add(comp2);
		companies.add(comp3);

		// Starts the payment processor thread for each company
		for (PaymentProcessorCompany company : companies) {
			PaymentProcessorThread paymentProcessorThread = new PaymentProcessorThread(company, hotels);
			paymentProcessorThread.start();
		}

		// Starts the hotel confirmation thread for each hotel
		for (Hotel hotel : hotels) {
			HotelConfirmationThread hotelConfirmationThread = new HotelConfirmationThread(hotel);
			hotelConfirmationThread.start();
		}

		// Main Menu
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(ANSI_BOLD+ANSI_BLUE + "Welcome to HotelsApp!"+ ANSI_RESET);
			System.out.println(ANSI_BOLD +"What do you want to do?:" + ANSI_RESET);
			System.out.println("1. Find my next vacation");
			System.out.println("2. Log In");
			System.out.println("3. Exit");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				Room selectedRoom = displayAvailableRooms(hotels, scanner);
				System.out.println("You selected: Room for " + selectedRoom.getMaxCapacity() + " at $" + selectedRoom.getPricePerNight() + " per night\n");
				System.out.println("To complete the reservation you need to login (enter 1) or sign up (enter 2):");
				Scanner subscan = new Scanner(System.in);
				int opt = scanner.nextInt();
				if (opt == 1) {
					System.out.println(ANSI_BOLD + ANSI_BLUE + "Enter username:" + ANSI_RESET);
					String username = subscan.nextLine();
					System.out.println(ANSI_BOLD + ANSI_BLUE + "\nEnter password:" + ANSI_RESET);
					String password = subscan.nextLine();
					try {
						Customer c  = Customer.authenticate(customers, username, password);
						c.createReservation(hotels, companies, selectedRoom);
					} catch (Exception e) {
						System.out.println(ANSI_RED + "\nAn error occurred during authentication"+ ANSI_RESET);
					}
				}
				if (opt == 2) {
					Customer c = signUp();
					customers.add(c);
					c.createReservation(hotels, companies, selectedRoom);
				}
				else {
					System.out.println("\nYou entered wrong choice, please try again...");
				}
				break;
			case 2:
				login(customers, hotels, companies);
				break;
			case 3:
				System.out.println("Exiting...");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	private static void login(List<Customer> customers, List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BOLD = "\u001B[1m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		Scanner scanner = new Scanner(System.in);
		Customer loggedInCustomer = null;
		while (loggedInCustomer == null) {
			System.out.println(ANSI_BOLD + ANSI_BLUE + "Enter username:" + ANSI_RESET);
			String username = scanner.nextLine();
			System.out.println(ANSI_BOLD + ANSI_BLUE + "\nEnter password:" + ANSI_RESET);
			String password = scanner.nextLine();

			loggedInCustomer = Customer.authenticate(customers, username, password);

			if (loggedInCustomer == null) {
				System.out.println(ANSI_RED + "\nInvalid username or password. Please try again." + ANSI_RESET);
			}
		}

		// saves Log of login details after successful authentication
		Customer.logLoginDetails(loggedInCustomer);

		System.out.println(ANSI_BOLD + ANSI_GREEN + "\nHello, " + loggedInCustomer.getName() + "!" + ANSI_RESET);

		if ("admin".equals(loggedInCustomer.getUsername())) {
			displayAdminMenu(scanner, loggedInCustomer, hotels, companies);
		} else {
			displayCustomerMenu(scanner, loggedInCustomer, hotels, companies);
		}
	}

	private static void displayCustomerMenu(Scanner scanner, Customer loggedInCustomer, List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BOLD = "\u001B[1m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		while (true) {
			System.out.println(ANSI_BOLD + "What do you want to do?:" + ANSI_RESET);
			System.out.println("1. Update Profile");
			System.out.println("2. View Past Reservations");
			System.out.println("3. Edit Reservation");
			System.out.println("4. Cancel Reservation");
			System.out.println("5. Exit");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Updating profile...");
				loggedInCustomer.updateProfileGUI();
				System.out.println("\nProfile updated successfully: " + loggedInCustomer+"\n");
				break;
			case 2:
				System.out.println("Viewing past reservations...");
				loggedInCustomer.viewPastReservations(hotels);
				break;
			case 3:
				System.out.println("Edit reservation...");
				loggedInCustomer.editReservation(hotels, companies);
				break;
			case 4:
				System.out.println("Cancel reservation...");
				loggedInCustomer.cancelReservation(hotels, companies);
				break;
			case 5:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	private static void displayAdminMenu(Scanner scanner, Customer loggedInCustomer, List<Hotel> hotels, List<PaymentProcessorCompany> companies) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BOLD = "\u001B[1m";
		while (true) {
			System.out.println(ANSI_BOLD + "Admin Menu:" + ANSI_RESET);
			System.out.println("1. Add Room");
			System.out.println("2. Update Room Details");
			System.out.println("3. View Logs");
			System.out.println("4. Exit");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Adding room...");
				loggedInCustomer.addRoom(hotels);
				break;
			case 2:
				System.out.println("Updating Room Details...");
				loggedInCustomer.updateRoomDetails(hotels);
				break;
			case 3:
				System.out.println("Viewing logs...");
				viewLogs();
				break;
			case 4:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	private static void viewLogs() {
		try (BufferedReader reader = new BufferedReader(new FileReader("logins.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading from log file: " + e.getMessage());
		}
	}
}
