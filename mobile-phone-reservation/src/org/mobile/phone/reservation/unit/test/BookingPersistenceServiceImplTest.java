package org.mobile.phone.reservation.unit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.MobilePhoneVendor;
import org.mobile.phone.reservation.model.Platform;
import org.mobile.phone.reservation.model.SmartMobilePhone;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.BookingPersistenceRepository;
import org.mobile.phone.reservation.repository.UsersRepository;
import org.mobile.phone.reservation.service.BookingPersistenceService;
import org.mobile.phone.reservation.service.BookingPersistenceServiceImpl;
import org.mobile.phone.reservation.service.UsersService;
import org.mobile.phone.reservation.service.UsersServiceImpl;

public class BookingPersistenceServiceImplTest {

	private BookingPersistenceService bookingPersistenceService;
	private BookingPersistenceRepository bookingPersistenceRepository;
	private UsersRepository usersRepository;
	private UsersService usersService;

	@Before
	public void setUp() {
		bookingPersistenceRepository = BookingPersistenceRepository.getInstance();
		bookingPersistenceService = BookingPersistenceServiceImpl.getInstance(bookingPersistenceRepository);
		usersRepository = UsersRepository.getInstance();
		usersService = UsersServiceImpl.getInstance(usersRepository);
		usersService.addUser("Tomislav", "Nedanovski");
	}

	@Test
	public void testSave() {
		MobilePhone mobilePhone = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		User user = usersService.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingPersistenceService.save(mobilePhone, user);
		assertNotEquals(mobilePhoneBooking, null);
		assertEquals(mobilePhoneBooking.getMobilePhone(), mobilePhone);
		assertEquals(mobilePhoneBooking.getUser(), user);
	}

	@Test
	public void testDelete() {
		MobilePhone mobilePhone = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		User user = usersService.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingPersistenceService.save(mobilePhone, user);
		assertNotEquals(mobilePhoneBooking, null);
		MobilePhoneBooking deletedMobilePhoneBooking = bookingPersistenceService.delete(mobilePhone, user);
		assertNotEquals(deletedMobilePhoneBooking, null);
		assertEquals(deletedMobilePhoneBooking.getMobilePhone(), mobilePhone);
		assertEquals(deletedMobilePhoneBooking.getUser(), user);
	}

	@Test
	public void testGetMobilePhoneBooking() throws Exception {
		MobilePhone mobilePhone = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		User user = usersService.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingPersistenceService.save(mobilePhone, user);
		assertNotEquals(mobilePhoneBooking, null);
		MobilePhoneBooking foundMobilePhoneBooking = bookingPersistenceService.getMobilePhoneBooking(1);
		assertNotEquals(foundMobilePhoneBooking, null);
		assertEquals(foundMobilePhoneBooking.getMobilePhone(), mobilePhone);
		assertEquals(foundMobilePhoneBooking.getUser(), user);
	}
}
