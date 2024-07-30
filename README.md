# HotelProject
welcome to Hotel reservation system.
You can login the system as guest / customer / admin

Reservation flow - User choose Find my next vacation on menu ---> User choose room ---> user login with username and password / user sign up (GUI) ---> user choose number of guests ---> user choose dates ---> user choose payment method (cash/card) 
---> if cash, payment created and sent to the hotel. if card, user choose credit card company ---> payment created and sent to the payment processcor company ---> user return to the main menu

there is a thread that running in the background that waiting for new payments to arrive into payments list in each Payment Processor Company. if there is a new payment, the credit card company makes payment validation and sent Payment Confirmation
to the hotel

there is another thread that waiting for new Payment Confirmations to arrive into Hotel confirmations list. when a new confirmation added to the List, the function sent the confirmation ID to the phone number of the customer.

sign up customer options - 
1. update profile (GUI)
2. view past reservations
3. edit reservations (change dates, add/remove guests, change room). ---> if the price of the reservation changes (more/less days, another pricePerNight for room) , the price is changing in line with the new details and the customer get refund/extra charge
4. cancel reservation - the reservation is deleted from the hotel reservation list, and the payment is deleted from payments list of the credit card company

admin options - 
1. add new room for hotel
2. update exist room details 
3. view logs of the users that login the system (logs shows the customer details and the date of login)
