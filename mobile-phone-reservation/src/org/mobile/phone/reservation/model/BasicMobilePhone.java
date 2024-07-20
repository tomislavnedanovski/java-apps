package org.mobile.phone.reservation.model;

public class BasicMobilePhone extends MobilePhone {

	public BasicMobilePhone(MobilePhoneVendor vendor, String name) {
		super(vendor, name);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
