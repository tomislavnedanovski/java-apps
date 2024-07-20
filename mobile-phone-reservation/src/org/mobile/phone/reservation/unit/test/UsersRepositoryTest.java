package org.mobile.phone.reservation.unit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mobile.phone.reservation.model.User;
import org.mobile.phone.reservation.repository.UsersRepository;

public class UsersRepositoryTest {

	private UsersRepository usersRepository;

	@Before
	public void setUp() {
		usersRepository = UsersRepository.getInstance();
		usersRepository.addUser("Tomislav", "Nedanovski");
	}

	@Test
	public void testAddAndGetUser() {
		User expectedUser = new User("Tomislav", "Nedanovski");
		usersRepository.addUser("Tomislav", "Nedanovski");
		User user = usersRepository.getUser("Tomislav", "Nedanovski");
		assertNotEquals(user, null);
		assertEquals(user.getFirstName(), expectedUser.getFirstName());
		assertEquals(user.getLastName(), expectedUser.getLastName());
	}
}
