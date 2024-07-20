package org.mobile.phone.reservation.main;

import java.util.Map;
import java.util.Scanner;

import org.mobile.phone.reservation.exception.BookingFailedException;
import org.mobile.phone.reservation.exception.CannotFindMobilePhoneByNameException;
import org.mobile.phone.reservation.exception.MobileBookingNotFoundException;
import org.mobile.phone.reservation.exception.ReturnFailedException;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.BookingPersistenceRepository;
import org.mobile.phone.reservation.repository.BookingRepository;
import org.mobile.phone.reservation.repository.UsersRepository;
import org.mobile.phone.reservation.service.BookingPersistenceService;
import org.mobile.phone.reservation.service.BookingPersistenceServiceImpl;
import org.mobile.phone.reservation.service.BookingService;
import org.mobile.phone.reservation.service.BookingServiceImpl;
import org.mobile.phone.reservation.service.UsersService;
import org.mobile.phone.reservation.service.UsersServiceImpl;

public class Main {

	private static UsersRepository userRepository;
	private static BookingRepository bookingRepository;
	private static BookingPersistenceRepository bookingPersistenceRepository;
	private static UsersService usersService;
	private static BookingService bookingService;
	private static BookingPersistenceService bookingPersistenceService;

	public static void main(String[] args) {
		userRepository = UsersRepository.getInstance();
		bookingRepository = BookingRepository.getInstance();
		bookingPersistenceRepository = BookingPersistenceRepository.getInstance();

		usersService = UsersServiceImpl.getInstance(userRepository);
		bookingService = BookingServiceImpl.getInstance(bookingRepository, bookingPersistenceRepository);
		bookingPersistenceService = BookingPersistenceServiceImpl.getInstance(bookingPersistenceRepository);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			// Options for the user
			System.out.println();
			System.out.println("===== Mobile Phone Booking App =====");
			System.out.println("1. View mobile phones available for booking");
			System.out.println("2. Book mobile phone");
			System.out.println("3. Return mobile phone");
			System.out.println("4. Display mobile phone booking information by id");
			System.out.println("5. Display mobile phone availability");
			System.out.println("6. Exit");
			System.out.println();
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				viewMobilePhonesAvailableForBooking();
				break;
			}
			case 2: {
				bookMobilePhone(scanner);
				break;
			}
			case 3: {
				returnMobilePhone(scanner);
				break;
			}
			case 4: {
				displayMobilePhoneBookingInformation(scanner);
				break;
			}
			case 5: {
				displayMobilePhoneAvailability(scanner);
				break;
			}
			case 6: {
				exitApp(scanner);
			}
			default: {
				System.out.println("Invalid choice. Please enter valid option...");
			}
			}
		}
	}

	private static void exitApp(Scanner scanner) {
		System.out.println("Thank you for using the Mobile Phone Booking App. Goodbye!");
		scanner.close();
		System.exit(0);
	}

	private static void displayMobilePhoneAvailability(Scanner scanner) {
		System.out.print("Enter mobile phone name: ");
		String mobilePhoneName = scanner.nextLine();

		try {
			boolean isMobilePhoneAvailable = bookingRepository.isMobilePhoneAvailable(mobilePhoneName);

			if(isMobilePhoneAvailable) {
				System.out.print("Mobile phone with name: " + mobilePhoneName + " is available");
			} else {
				System.out.print("Mobile phone with name: " + mobilePhoneName + " is not available");
			}
		} catch (CannotFindMobilePhoneByNameException e) {
			System.out.print(e.getMessage());
		}
	}

	private static void displayMobilePhoneBookingInformation(Scanner scanner) {
		System.out.print("Enter mobile phone booking id: ");
		String id = scanner.nextLine();
		MobilePhoneBooking mobilePhoneBooking = null;

		try {
			mobilePhoneBooking = bookingPersistenceService.getMobilePhoneBooking(Integer.parseInt(id));
			System.out.print("Mobile phone booking for mobile phone name: "
					+ mobilePhoneBooking.getMobilePhone().getName() + " booked by user: "
					+ mobilePhoneBooking.getUser().getFirstName() + " with id: " + mobilePhoneBooking.getId()
					+ " date of booking: " + mobilePhoneBooking.getDateTime());
		} catch (MobileBookingNotFoundException e) {
			System.out.print(e.getMessage());
		}
	}

	private static void returnMobilePhone(Scanner scanner) {
		System.out.print("Enter your first name: ");
		String name = scanner.nextLine();
		System.out.print("Enter your last name: ");
		String surname = scanner.nextLine();

		usersService.addUser(name, surname);
		System.out.print("Enter mobile phone name: ");
		String mobilePhoneName = scanner.nextLine();
		User user = usersService.getUser(name, surname);
		MobilePhoneBooking mobilePhoneBooking = null;

		try {
			mobilePhoneBooking = bookingService.returnPhone(mobilePhoneName, user);
			System.out.print("Mobile phone with name: " + mobilePhoneBooking.getMobilePhone().getName()
					+ " returned successfully by user: " + mobilePhoneBooking.getUser().getFirstName() + " "
					+ mobilePhoneBooking.getUser().getLastName());
		} catch (ReturnFailedException e) {
			System.out.print(e.getMessage());
		}
	}

	private static void bookMobilePhone(Scanner scanner) {
		System.out.print("Enter your first name: ");
		String name = scanner.nextLine();
		System.out.print("Enter your last name: ");
		String surname = scanner.nextLine();

		usersService.addUser(name, surname);
		System.out.print("Enter mobile phone name: ");
		String mobilePhoneName = scanner.nextLine();
		User user = usersService.getUser(name, surname);
		MobilePhoneBooking mobilePhoneBooking = null;

		try {
			mobilePhoneBooking = bookingService.bookPhone(mobilePhoneName, user);
			System.out.print("Mobile phone with name: " + mobilePhoneBooking.getMobilePhone().getName()
					+ " booked successfully by user: " + mobilePhoneBooking.getUser().getFirstName() + " "
					+ mobilePhoneBooking.getUser().getLastName() + ". Id: " + mobilePhoneBooking.getId());
		} catch (BookingFailedException e) {
			System.out.print(e.getMessage());
		}
	}

	private static void viewMobilePhonesAvailableForBooking() {
		System.out.print("Vendor | Name | Quantity");
		System.out.println();

		for (Map.Entry<MobilePhone, Integer> mapEntry : bookingRepository.getMobilePhones().entrySet()) {
			System.out.print(
					mapEntry.getKey().getVendor() + " | " + mapEntry.getKey().getName() + " | " + mapEntry.getValue());
			System.out.println();
		}
	}
}
