package org.mobile.phone.reservation.service;

import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.UsersRepository;

public class UsersServiceImpl implements UsersService {
	private final UsersRepository userRepository;
	private static UsersServiceImpl USERS_SERVICE_IMPL;

	private UsersServiceImpl(UsersRepository usersRepository) {
		this.userRepository = usersRepository;
	}

	public static synchronized UsersServiceImpl getInstance(UsersRepository usersRepository) {
		if (USERS_SERVICE_IMPL == null) {
			USERS_SERVICE_IMPL = new UsersServiceImpl(usersRepository);
		}

		return USERS_SERVICE_IMPL;
	}

	@Override
	public void addUser(String firstName, String lastName) {
		userRepository.addUser(firstName, lastName);
	}

	@Override
	public User getUser(String firstName, String lastName) {
		return userRepository.getUser(firstName, lastName);
	}
}
