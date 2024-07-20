package org.mobile.phone.reservation.model;

import java.util.Objects;

public class MobilePhone {
	protected final MobilePhoneVendor vendor;
	protected final String name;

	public MobilePhone(MobilePhoneVendor vendor, String name) {
		this.vendor = vendor;
		this.name = name;
	}

	public MobilePhoneVendor getVendor() {
		return vendor;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, vendor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobilePhone other = (MobilePhone) obj;
		return Objects.equals(name, other.name) && vendor == other.vendor;
	}
}
