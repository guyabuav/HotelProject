import java.util.List;

public class mainMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	Customer c1 = new Customer("0525515555", "guy ab", "12345", "Haifa" );
	Customer c2 = new Customer("0521234567", "guy abu", "123456", "Tel Aviv" );
	Customer c3 = new Customer("0521234111", "guy abua", "123457", "Netanya" );
	Customer c4 = new Customer("0521112311", "guy abuav", "123458", "Kadima" );

	Hotel h1 = new Hotel("091231231" , 5, "1", 0, "Eilat", "Agamim");
	Hotel h2 = new Hotel("095551231" , 4, "2", 0, "Kineret", "Salonim");

	Room r1 = new Room(5, "1");
	Room r2 = new Room(3, "2");
	Room r3 = new Room(4, "3");
	Room r4 = new Room(5, "4");
	Room r5 = new Room(7, "5");
	Room r6 = new Room(2, "6");
	Room r7 = new Room(2, "7");
	
	h1.addRoom(r1);
	h1.addRoom(r2);
	h1.addRoom(r3);
	h1.addRoom(r4);
	h2.addRoom(r5);
	h2.addRoom(r6);
	h2.addRoom(r7);
	
	System.out.println(c1.toString());
    System.out.println(c2.toString());
    System.out.println(c3.toString());
    System.out.println(c4.toString());
    System.out.println(h1.toString());
    System.out.println(h2.toString());



		

	}







}
