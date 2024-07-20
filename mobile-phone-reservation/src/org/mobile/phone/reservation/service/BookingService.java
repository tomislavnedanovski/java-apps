package org.mobile.phone.reservation.service;

import org.mobile.phone.reservation.exception.BookingFailedException;
import org.mobile.phone.reservation.exception.CannotFindMobilePhoneByNameException;
import org.mobile.phone.reservation.exception.ReturnFailedException;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;

public interface BookingService {
	MobilePhoneBooking bookPhone(String phoneName, User user) throws BookingFailedException;

	MobilePhoneBooking returnPhone(String phoneName, User user) throws ReturnFailedException;

	boolean isMobilePhoneAvailable(String phoneName) throws CannotFindMobilePhoneByNameException;
}
