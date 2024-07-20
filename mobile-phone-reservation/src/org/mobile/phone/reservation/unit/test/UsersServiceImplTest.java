package org.mobile.phone.reservation.unit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.UsersRepository;
import org.mobile.phone.reservation.service.UsersService;
import org.mobile.phone.reservation.service.UsersServiceImpl;

public class UsersServiceImplTest {

	private UsersService usersService;
	private UsersRepository usersRepository;

	@Before
	public void setUp() {
		usersRepository = UsersRepository.getInstance();
		usersRepository.addUser("Tomislav", "Nedanovski");
		usersService = UsersServiceImpl.getInstance(usersRepository);
	}

	@Test
	public void testAddAndGetUser() {
		User expectedUser = new User("Tomislav", "Nedanovski");
		usersService.addUser("Tomislav", "Nedanovski");
		User user = usersService.getUser("Tomislav", "Nedanovski");
		assertNotEquals(user, null);
		assertEquals(user.getFirstName(), expectedUser.getFirstName());
		assertEquals(user.getLastName(), expectedUser.getLastName());
	}
}
