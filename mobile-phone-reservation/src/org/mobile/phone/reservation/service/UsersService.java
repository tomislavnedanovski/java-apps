package org.mobile.phone.reservation.service;

import org.mobile.phone.reservation.model.User;

public interface UsersService {
	void addUser(String firstName, String lastName);

	User getUser(String firstName, String lastName);
}
