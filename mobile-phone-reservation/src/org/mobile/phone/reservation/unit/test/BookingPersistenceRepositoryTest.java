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

public class BookingPersistenceRepositoryTest {

	private BookingPersistenceRepository bookingPersistenceRepository;
	private UsersRepository usersRepository;

	@Before
	public void setUp() {
		bookingPersistenceRepository = BookingPersistenceRepository.getInstance();
		usersRepository = UsersRepository.getInstance();
		usersRepository.addUser("Tomislav", "Nedanovski");
	}

	@Test
	public void testSave() {
		MobilePhone mobilePhone = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		User user = usersRepository.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingPersistenceRepository.save(mobilePhone, user);
		assertNotEquals(mobilePhoneBooking, null);
		assertEquals(mobilePhoneBooking.getMobilePhone(), mobilePhone);
		assertEquals(mobilePhoneBooking.getUser(), user);
	}

	@Test
	public void testDelete() {
		MobilePhone mobilePhone = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		User user = usersRepository.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingPersistenceRepository.save(mobilePhone, user);
		assertNotEquals(mobilePhoneBooking, null);
		MobilePhoneBooking deletedMobilePhoneBooking = bookingPersistenceRepository.delete(mobilePhone, user);
		assertNotEquals(deletedMobilePhoneBooking, null);
		assertEquals(deletedMobilePhoneBooking.getMobilePhone(), mobilePhone);
		assertEquals(deletedMobilePhoneBooking.getUser(), user);
	}

	@Test
	public void testGetMobilePhoneBooking() throws Exception {
		MobilePhone mobilePhone = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		User user = usersRepository.getUser("Tomislav", "Nedanovski");
		MobilePhoneBooking mobilePhoneBooking = bookingPersistenceRepository.save(mobilePhone, user);
		assertNotEquals(mobilePhoneBooking, null);
		MobilePhoneBooking foundMobilePhoneBooking = bookingPersistenceRepository.getMobilePhoneBooking(1);
		assertNotEquals(foundMobilePhoneBooking, null);
		assertEquals(foundMobilePhoneBooking.getMobilePhone(), mobilePhone);
		assertEquals(foundMobilePhoneBooking.getUser(), user);
	}
}
