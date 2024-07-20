I have created a simple Mobile Phone Reservation App using Java.

Summary:

You can book or return a mobile phone from the ones available via inputs from command line. You can pull the latest tag from docker hub from here: https://hub.docker.com/r/tomislavnedanovski/mobile-phone-reservation/tags. Run the image with command:  'docker run -i -t mobile-phone-reservation' to allow for command line inputs.

Contents:

Model classes are defined in org.mobile.phone.reservation.model packege. MobilePhone is parent class to BasicMobilePhone and SmartMobilePhone. MobilePhoneVendor and Platform are enums to define vendors and platforms. MobilePhoneBooking represents booking of mobile phone. User represents simple user with first name and last name which are entered before making a booking or returning a phone.

Repository classes are located in org.mobile.phone.reservation.model.repository package. UsersRepository is used for persistance of users, BookingRepository for persistance of mobile phones available for booking and BookingPersistenceRepository is for persistence of the mobile phone bookings that are made by users. All data is stored in memory in maps or lists. 

Service classes are defined in org.mobile.phone.reservation.model.service package. There is UserService, BookingService and BookingPersistenceService which are used to add/get users, book/return/isAvailable for a mobile phone and save/delete/getById for a MobilePhoneBooking.

Unit tests are located in org.mobile.phone.reservation.model.unit.test package. There are unit tests for the repository and the service classes.

Details:
When the app is started the user can choose 1 of 6 opions:
1. View mobile phones available for booking
-view all mobile phones available with their quantity with this option
2. Book mobile phone
-book a mobile phone with this option, you will be prompted to enter first name and last name, and then the name of the phone which you can copy from the output from option 1 (for example: Samsung Galaxy S9), when mobile phone is successfully booked their quantity is decreased, if there is no mobile phone available you will not be able to book it
3. Return mobile phone
-return a mobile phone, you will be prompted to enter first name and last name, and then the name of the phone (for example: Samsung Galaxy S9) - you must enter the same first name and last name with which you booked the phone to return it successfully, when mobile phone is successfully returned their quantity is increased 
4. Display mobile phone booking information by id
-check phone booking information by an id, when you make the booking using option 2 you can see the id for the booking made and enter it when prompted in this step to get detailed info about the mobile phone booking
5. Display mobile phone availability
-display phone availability by entering phone name (for example: Samsung Galaxy S9)
6. Exit the App
-press 6 to exit the app