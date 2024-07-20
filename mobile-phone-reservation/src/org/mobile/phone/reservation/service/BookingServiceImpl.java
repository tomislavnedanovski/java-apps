package org.mobile.phone.reservation.service;

import java.util.concurrent.locks.ReentrantLock;

import org.mobile.phone.reservation.exception.BookingFailedException;
import org.mobile.phone.reservation.exception.CannotFindMobilePhoneByNameException;
import org.mobile.phone.reservation.exception.ReturnFailedException;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneBooking;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.BookingPersistenceRepository;
import org.mobile.phone.reservation.repository.BookingRepository;

public class BookingServiceImpl implements BookingService {

	private static BookingServiceImpl BOOKING_SERVICE_IMPL;
	private final BookingRepository bookingRepository;
	private final BookingPersistenceRepository bookingPersistenceRepository;
	private final ReentrantLock lock = new ReentrantLock();

	public BookingServiceImpl(BookingRepository bookingRepository,
			BookingPersistenceRepository bookingPersistenceRepository) {
		this.bookingRepository = bookingRepository;
		this.bookingPersistenceRepository = bookingPersistenceRepository;
	}

	public static synchronized BookingServiceImpl getInstance(
			BookingRepository bookingRepository,
			BookingPersistenceRepository bookingPersistenceRepository) {
		if (BOOKING_SERVICE_IMPL == null) {
			BOOKING_SERVICE_IMPL = new BookingServiceImpl(bookingRepository, bookingPersistenceRepository);
		}

		return BOOKING_SERVICE_IMPL;
	}

	@Override
	public MobilePhoneBooking bookPhone(String phoneName, User user) throws BookingFailedException {
		try {
			lock.lock();
			MobilePhone mobilePhone = null;

			try {
				mobilePhone = bookingRepository.bookPhone(phoneName);
			} catch (BookingFailedException e) {
				throw (e);
			}

			return bookingPersistenceRepository.save(mobilePhone, user);
		} finally {
			lock.unlock();
		}

	}

	@Override
	public MobilePhoneBooking returnPhone(String phoneName, User user) throws ReturnFailedException {
		try {
			lock.lock();
			MobilePhone mobilePhone = null;

			for (MobilePhoneBooking mobilePhoneBooking : bookingPersistenceRepository.getMobilePhoneBookings()) {
				if (mobilePhoneBooking.getMobilePhone().getName().equals(phoneName)
						&& mobilePhoneBooking.getUser().equals(user)) {
					try {
						mobilePhone = bookingRepository.returnPhone(phoneName);
						return bookingPersistenceRepository.delete(mobilePhone, user);
					} catch (ReturnFailedException e) {
						throw (e);
					}
				}
			}

			throw new ReturnFailedException(
					"Mobile phone cannot be returned from a different user to the one that booked it!");
		} finally {
			lock.unlock();
		}
	}

	public boolean isMobilePhoneAvailable(String phoneName) throws CannotFindMobilePhoneByNameException {
		boolean exists;

		try {
			exists = bookingRepository.isMobilePhoneAvailable(phoneName);
		} catch (CannotFindMobilePhoneByNameException e) {
			throw (e);
		}

		return exists;
	}
}
