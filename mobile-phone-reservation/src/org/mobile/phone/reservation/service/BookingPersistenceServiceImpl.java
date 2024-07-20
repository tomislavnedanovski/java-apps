package org.mobile.phone.reservation.service;

import org.mobile.phone.reservation.exception.MobileBookingNotFoundException;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.BookingPersistenceRepository;

public class BookingPersistenceServiceImpl implements BookingPersistenceService {
	private final BookingPersistenceRepository bookingRepository;
	private static BookingPersistenceServiceImpl BOOKING_PERSISTENCE_SERVICE_IMPL;;

	private BookingPersistenceServiceImpl(BookingPersistenceRepository bookingPersistenceRepository) {
		this.bookingRepository = bookingPersistenceRepository;
	}

	public static synchronized BookingPersistenceServiceImpl getInstance(
			BookingPersistenceRepository bookingPersistenceRepository) {
		if (BOOKING_PERSISTENCE_SERVICE_IMPL == null) {
			BOOKING_PERSISTENCE_SERVICE_IMPL = new BookingPersistenceServiceImpl(bookingPersistenceRepository);
		}

		return BOOKING_PERSISTENCE_SERVICE_IMPL;
	}

	@Override
	public MobilePhoneBooking getMobilePhoneBooking(int id) throws MobileBookingNotFoundException {
		MobilePhoneBooking mobilePhoneBooking = null;

		try {
			mobilePhoneBooking = bookingRepository.getMobilePhoneBooking(id);
		} catch (MobileBookingNotFoundException e) {
			throw (e);
		}

		return mobilePhoneBooking;
	}

	@Override
	public MobilePhoneBooking save(MobilePhone mobilePhone, User user) {
		return bookingRepository.save(mobilePhone, user);
	}

	@Override
	public MobilePhoneBooking delete(MobilePhone mobilePhone, User user) {
		return bookingRepository.delete(mobilePhone, user);
	}
}
