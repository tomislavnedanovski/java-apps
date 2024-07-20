package org.mobile.phone.reservation.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mobile.phone.reservation.exception.BookingFailedException;
import org.mobile.phone.reservation.exception.CannotFindMobilePhoneByNameException;
import org.mobile.phone.reservation.exception.ReturnFailedException;
import org.mobile.phone.reservation.model.BasicMobilePhone;
import org.mobile.phone.reservation.model.MobilePhone;
import org.mobile.phone.reservation.model.MobilePhoneVendor;
import org.mobile.phone.reservation.model.Platform;
import org.mobile.phone.reservation.model.SmartMobilePhone;

public class BookingRepository {

	private Map<MobilePhone, Integer> mobilePhones;
	private static BookingRepository BOOKING_REPOSITORY;

	private BookingRepository() {
		init();
	}

	public static synchronized BookingRepository getInstance() {
		if (BOOKING_REPOSITORY == null) {
			BOOKING_REPOSITORY = new BookingRepository();
		}

		return BOOKING_REPOSITORY;
	}

	public Map<MobilePhone, Integer> getMobilePhones() {
		return mobilePhones;
	}

	private void init() {
		mobilePhones = new ConcurrentHashMap<>();

		MobilePhone samsungGalaxyS9 = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S9",
				Platform.ANDROID);
		MobilePhone samsungGalaxyS8 = new SmartMobilePhone(MobilePhoneVendor.SAMSUNG, "Samsung Galaxy S8",
				Platform.ANDROID);
		MobilePhone motorolaNexus6 = new SmartMobilePhone(MobilePhoneVendor.MOTOROLA, "Motorola Nexus 6",
				Platform.ANDROID);
		MobilePhone onePlus9 = new SmartMobilePhone(MobilePhoneVendor.ONEPLUS, "Oneplus 9", Platform.ANDROID);
		MobilePhone appleIPhone13 = new SmartMobilePhone(MobilePhoneVendor.APPLE, "Apple iPhone 13", Platform.IOS);
		MobilePhone appleIPhone12 = new SmartMobilePhone(MobilePhoneVendor.APPLE, "Apple iPhone 12", Platform.IOS);
		MobilePhone appleIPhone11 = new SmartMobilePhone(MobilePhoneVendor.APPLE, "Apple iPhone 11", Platform.IOS);
		MobilePhone iPhoneX = new SmartMobilePhone(MobilePhoneVendor.APPLE, "iPhone X", Platform.IOS);
		MobilePhone nokia3310 = new BasicMobilePhone(MobilePhoneVendor.NOKIA, "Nokia 3310");

		mobilePhones.put(samsungGalaxyS9, 1);
		mobilePhones.put(samsungGalaxyS8, 2);
		mobilePhones.put(motorolaNexus6, 1);
		mobilePhones.put(onePlus9, 1);
		mobilePhones.put(appleIPhone13, 1);
		mobilePhones.put(appleIPhone12, 1);
		mobilePhones.put(appleIPhone11, 1);
		mobilePhones.put(iPhoneX, 1);
		mobilePhones.put(nokia3310, 1);
	}

	public Map.Entry<MobilePhone, Integer> findByName(String name) throws CannotFindMobilePhoneByNameException {
		for (Map.Entry<MobilePhone, Integer> mobilePhoneEntry : mobilePhones.entrySet()) {
			MobilePhone mobilePhone = mobilePhoneEntry.getKey();

			if (mobilePhone.getName().equals(name)) {
				return mobilePhoneEntry;
			}
		}

		throw new CannotFindMobilePhoneByNameException("Mobile phone cannot be found by the name: " + name);
	}

	public boolean isMobilePhoneAvailable(String name) throws CannotFindMobilePhoneByNameException {
		Map.Entry<MobilePhone, Integer> mobilePhoneEntry = null;

		try {
			mobilePhoneEntry = findByName(name);
		} catch (CannotFindMobilePhoneByNameException e) {
			throw (e);
		}

		if (mobilePhoneEntry != null && mobilePhoneEntry.getValue() > 0) {
			return true;
		}

		return false;
	}

	private void decreaseQuantity(String name) {
		for (Map.Entry<MobilePhone, Integer> mobilePhoneEntry : mobilePhones.entrySet()) {
			MobilePhone mobilePhone = mobilePhoneEntry.getKey();
			Integer quantity = mobilePhoneEntry.getValue();

			if (mobilePhone.getName().equals(name) && quantity > 0) {
				mobilePhones.put(mobilePhone, quantity - 1);
			}
		}
	}

	private void increaseQuantity(String name) {
		for (Map.Entry<MobilePhone, Integer> mobilePhoneEntry : mobilePhones.entrySet()) {
			MobilePhone mobilePhone = mobilePhoneEntry.getKey();
			Integer quantity = mobilePhoneEntry.getValue();

			if (mobilePhone.getName().equals(name)) {
				mobilePhones.put(mobilePhone, quantity + 1);
			}
		}
	}

	public MobilePhone bookPhone(String name) throws BookingFailedException {
		MobilePhone mobilePhone = null;

		try {
			Map.Entry<MobilePhone, Integer> mobilePhoneEntry = findByName(name);

			if (mobilePhoneEntry.getValue() == 0) {
				throw new BookingFailedException(
						"Mobile phone with name: " + name + " is not available for booking at the moment!");
			}

			mobilePhone = mobilePhoneEntry.getKey();
			decreaseQuantity(name);
		} catch (CannotFindMobilePhoneByNameException e) {
			throw new BookingFailedException("Cannot book mobile phone with name: " + name);
		}

		return mobilePhone;
	}

	public MobilePhone returnPhone(String name) throws ReturnFailedException {
		Map.Entry<MobilePhone, Integer> mobilePhoneEntry = null;

		try {
			mobilePhoneEntry = findByName(name);
		} catch (CannotFindMobilePhoneByNameException e) {
			throw new ReturnFailedException("Cannot return mobile phone with name: " + name);
		}

		increaseQuantity(name);
		return mobilePhoneEntry.getKey();
	}
}
