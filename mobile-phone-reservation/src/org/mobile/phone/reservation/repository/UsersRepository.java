package org.mobile.phone.reservation.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.mobile.phone.reservation.model.User;

public class UsersRepository {

	private static UsersRepository USERS_REPOSITORY;
	private List<User> users;

	private UsersRepository() {
		users = new CopyOnWriteArrayList<>();
	}

	public static synchronized UsersRepository getInstance() {
		if (USERS_REPOSITORY == null) {
			USERS_REPOSITORY = new UsersRepository();
		}

		return USERS_REPOSITORY;
	}

	public void addUser(String firstName, String lastName) {
		User user = new User(firstName, lastName);

		if (!userExists(firstName, lastName)) {
			users.add(user);
		}
	}

	public User getUser(String firstName, String lastName) {
		for (User user : users) {
			if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
				return user;
			}
		}

		return null;
	}

	private boolean userExists(String firstName, String lastName) {
		for (User user : users) {
			if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
				return true;
			}
		}

		return false;
	}
}
