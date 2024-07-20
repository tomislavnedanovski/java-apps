package org.mobile.phone.reservation.unit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.BookingPersistenceRepository;
import org.mobile.phone.reservation.repository.BookingRepository;
import org.mobile.phone.reservation.repository.UsersRepository;
import org.mobile.phone.reservation.service.BookingService;
import org.mobile.phone.reservation.service.BookingServiceImpl;
import org.mobile.phone.reservation.service.UsersService;
import org.mobile.phone.reservation.service.UsersServiceImpl;

public class BookingServiceImplTest {

	private BookingService bookingService;
	private BookingRepository bookingRepository;
	private BookingPersistenceRepository bookingPersistenceRepository;
	private UsersRepository usersRepository;
	private UsersService usersService;

	@Before
	public void setUp() {
		bookingRepository = BookingRepository.getInstance();
		bookingPersistenceRepository = BookingPersistenceRepository.getInstance();
		bookingService = BookingServiceImpl.getInstance(bookingRepository, bookingPersistenceRepository);
		usersRepository = UsersRepository.getInstance();
		usersService = UsersServiceImpl.getInstance(usersRepository);
		usersService.addUser("Tomislav", "Nedanovski");
	}

	@Test
	public void testBookPhone() throws Exception {
		User user = usersService.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingService.bookPhone("Samsung Galaxy S9", user);
		assertNotEquals(mobilePhoneBooking, null);
		assertEquals(mobilePhoneBooking.getMobilePhone().getName(), "Samsung Galaxy S9");
		assertEquals(mobilePhoneBooking.getUser().getFirstName(), "Tomislav");
		assertEquals(mobilePhoneBooking.getUser().getLastName(), "Nedanovski");
	}

	@Test
	public void testReturnPhone() throws Exception {
		User user = usersService.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingService.bookPhone("Samsung Galaxy S9", user);
		assertNotEquals(mobilePhoneBooking, null);
		MobilePhoneBooking returnMobilePhoneBooking = bookingService.returnPhone("Samsung Galaxy S9", user);
		assertNotEquals(returnMobilePhoneBooking, null);
		assertEquals(returnMobilePhoneBooking.getMobilePhone().getName(), "Samsung Galaxy S9");
		assertEquals(returnMobilePhoneBooking.getUser().getFirstName(), "Tomislav");
		assertEquals(returnMobilePhoneBooking.getUser().getLastName(), "Nedanovski");
	}

	@Test
	public void testIsMobilePhoneAvailable() throws Exception {
		boolean isMobilePhoneAvailable = bookingService.isMobilePhoneAvailable("Samsung Galaxy S9");
		assertTrue(isMobilePhoneAvailable);
	}
}
