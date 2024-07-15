package come.ticket.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projet.tickets.model.User;
import com.projet.tickets.services.impl.UserServices;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServicesTest {

	@InjectMocks
	private UserServices userService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void findAlluser() throws Exception {
		List<User> list = new ArrayList<>();

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");

		userService.createUser(user);
		list.add(user);

		User user2 = new User();
		user2.setEmail("prisca2@gmail.com");
		user2.setPassword("prisca2");
		user2.setUsername("prisca2");

		userService.createUser(user2);
		list.add(user2);

		when(userService.findAllUser()).thenReturn(list);

		// test
		List<User> lists = userService.findAllUser();

		assertEquals(1, lists.size());
	}

	public void findById() throws Exception {

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");

		userService.createUser(user);
		User userFound = userService.findById(1);
		assertNotNull(userFound, "Le user existe");

	}

	public void createOrUpdateuser() throws Exception {

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");

		userService.createUser(user);
	}

	public void deleteuser() throws Exception {

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");

		userService.deleteUser(1);
	}

}
