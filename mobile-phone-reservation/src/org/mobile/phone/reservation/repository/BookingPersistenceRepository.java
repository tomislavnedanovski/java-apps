package org.mobile.phone.reservation.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.mobile.phone.reservation.exception.MobileBookingNotFoundException;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;

public class BookingPersistenceRepository {
	private int id = 1;
	private List<MobilePhoneBooking> mobilePhoneBookings = new CopyOnWriteArrayList<>();
	private static BookingPersistenceRepository BOOKING_PERSISTENCE_REPOSITORY;

	private BookingPersistenceRepository() {

	}

	public static synchronized BookingPersistenceRepository getInstance() {
		if (BOOKING_PERSISTENCE_REPOSITORY == null) {
			BOOKING_PERSISTENCE_REPOSITORY = new BookingPersistenceRepository();
		}

		return BOOKING_PERSISTENCE_REPOSITORY;
	}

	public List<MobilePhoneBooking> getMobilePhoneBookings() {
		return mobilePhoneBookings;
	}

	public MobilePhoneBooking getMobilePhoneBooking(int id) throws MobileBookingNotFoundException {
		for (MobilePhoneBooking mobilePhoneBooking : mobilePhoneBookings) {
			if (mobilePhoneBooking.getId() == id) {
				return mobilePhoneBooking;
			}
		}

		throw new MobileBookingNotFoundException("Mobile booking with id: " + id + " not found!");
	}

	public MobilePhoneBooking save(MobilePhone mobilePhone, User user) {
		MobilePhoneBooking mobilePhoneBooking = new MobilePhoneBooking(id++, mobilePhone, user, LocalDateTime.now());
		mobilePhoneBookings.add(mobilePhoneBooking);
		return mobilePhoneBooking;
	}

	public MobilePhoneBooking delete(MobilePhone mobilePhone, User user) {
		for (MobilePhoneBooking booking : mobilePhoneBookings) {
			if (booking.getMobilePhone().equals(mobilePhone) && booking.getUser().equals(user)) {
				mobilePhoneBookings.remove(booking);
				id--;
				return booking;
			}
		}

		return null;
	}
}
