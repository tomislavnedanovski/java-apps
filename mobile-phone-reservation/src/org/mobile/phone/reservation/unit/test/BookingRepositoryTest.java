package org.mobile.phone.reservation.unit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.repository.BookingRepository;

public class BookingRepositoryTest {

	private BookingRepository bookingRepository;

	@Before
	public void setUp() {
		bookingRepository = BookingRepository.getInstance();
	}

	@Test
	public void testBookPhone() throws Exception {
		MobilePhone mobilePhone = bookingRepository.bookPhone("Samsung Galaxy S9");
		assertNotEquals(mobilePhone, null);
		assertEquals(mobilePhone.getName(), "Samsung Galaxy S9");
	}

	@Test
	public void testReturnPhone() throws Exception {
		MobilePhone mobilePhone = bookingRepository.bookPhone("Samsung Galaxy S9");
		assertNotEquals(mobilePhone, null);
		assertEquals(mobilePhone.getName(), "Samsung Galaxy S9");
		MobilePhone returnedMobilePhone = bookingRepository.returnPhone("Samsung Galaxy S9");
		assertNotEquals(returnedMobilePhone, null);
		assertEquals(returnedMobilePhone.getName(), "Samsung Galaxy S9");
	}

	@Test
	public void testFindByName() throws Exception {
		MobilePhone mobilePhone = bookingRepository.bookPhone("Samsung Galaxy S9");
		assertNotEquals(mobilePhone, null);
		assertEquals(mobilePhone.getName(), "Samsung Galaxy S9");
		Map.Entry<MobilePhone, Integer> foundMobilePhone = bookingRepository.findByName("Samsung Galaxy S9");
		assertNotEquals(foundMobilePhone, null);
		assertEquals(foundMobilePhone.getKey().getName(), "Samsung Galaxy S9");
	}

	@Test
	public void testIsMobilePhoneAvailable() throws Exception {
		MobilePhone mobilePhone = bookingRepository.bookPhone("Samsung Galaxy S9");
		assertNotEquals(mobilePhone, null);
		assertEquals(mobilePhone.getName(), "Samsung Galaxy S9");
		boolean isMobilePhoneAvailable = bookingRepository.isMobilePhoneAvailable("Samsung Galaxy S9");
		assertTrue(isMobilePhoneAvailable);
	}
}
