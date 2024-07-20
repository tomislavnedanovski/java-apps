package org.mobile.phone.reservation.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MobilePhoneBooking {

	private final int id;
	private final MobilePhone mobilePhone;
	private final User user;
	private final LocalDateTime dateTime;

	public MobilePhoneBooking(int id, MobilePhone mobilePhone, User user, LocalDateTime dateTime) {
		this.id = id;
		this.mobilePhone = mobilePhone;
		this.user = user;
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public MobilePhone getMobilePhone() {
		return mobilePhone;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, id, mobilePhone, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobilePhoneBooking other = (MobilePhoneBooking) obj;
		return Objects.equals(dateTime, other.dateTime) && id == other.id
				&& Objects.equals(mobilePhone, other.mobilePhone) && Objects.equals(user, other.user);
	}
}
