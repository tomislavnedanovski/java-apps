package org.mobile.phone.reservation.service;

import org.mobile.phone.reservation.exception.MobileBookingNotFoundException;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;

public interface BookingPersistenceService {
	MobilePhoneBooking getMobilePhoneBooking(int id) throws MobileBookingNotFoundException;

	MobilePhoneBooking save(MobilePhone mobilePhone, User user);

	MobilePhoneBooking delete(MobilePhone mobilePhone, User user);
}
