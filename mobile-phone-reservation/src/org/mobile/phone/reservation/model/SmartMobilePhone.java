package org.mobile.phone.reservation.model;

import java.util.Objects;

public class SmartMobilePhone extends MobilePhone {

	private final Platform platform;

	public SmartMobilePhone(MobilePhoneVendor vendor, String name, Platform platform) {
		super(vendor, name);
		this.platform = platform;
	}

	public Platform getPlatform() {
		return platform;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(platform);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartMobilePhone other = (SmartMobilePhone) obj;
		return platform == other.platform;
	}
}
