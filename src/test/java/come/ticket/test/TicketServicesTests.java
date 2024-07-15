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

import com.projet.tickets.model.Ticket;
import com.projet.tickets.model.Ticket.StatusTicket;
import com.projet.tickets.model.User;
import com.projet.tickets.services.impl.TicketServices;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TicketServicesTests {

	@InjectMocks
	private TicketServices ticketService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void findAllTicket() throws Exception {
		List<Ticket> list = new ArrayList<>();
		Ticket ticket = new Ticket();
		ticket.setStatus(StatusTicket.En_Cours);
		ticket.setDescription("description");
		ticket.setTitre("Titre");

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");
		ticket.setUser(user);

		ticketService.createTicket(ticket);
		list.add(ticket);

		Ticket ticket2 = new Ticket();
		ticket2.setStatus(StatusTicket.En_Cours);
		ticket2.setDescription("description2");
		ticket2.setTitre("Titre2");

		User user2 = new User();
		user2.setEmail("prisca2@gmail.com");
		user2.setPassword("prisca2");
		user2.setUsername("prisca2");
		ticket2.setUser(user2);

		ticketService.createTicket(ticket2);
		list.add(ticket2);

		when(ticketService.findAllTicket()).thenReturn(list);

		// test
		List<Ticket> lists = ticketService.findAllTicket();

		assertEquals(1, lists.size());
	}

	public void findById() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setStatus(StatusTicket.En_Cours);
		ticket.setDescription("description");
		ticket.setTitre("Titre");

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");
		ticket.setUser(user);
		ticketService.createTicket(ticket);
		Ticket ticketFound = ticketService.findById(1);
		assertNotNull(ticketFound, "Le ticket existe");

	}

	public void createOrUpdateTicket() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setStatus(StatusTicket.En_Cours);
		ticket.setDescription("description");
		ticket.setTitre("Titre");

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");
		ticket.setUser(user);

		ticketService.createTicket(ticket);
	}

	public void assigneTicketForUser() throws Exception {

		// Creation du ticket 1
		Ticket ticket = new Ticket();
		ticket.setStatus(StatusTicket.En_Cours);
		ticket.setDescription("description");
		ticket.setTitre("Titre");

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");
		ticket.setUser(user);

		ticketService.createTicket(ticket);

		Ticket ticket2 = new Ticket();
		ticket2.setStatus(StatusTicket.En_Cours);
		ticket2.setDescription("description2");
		ticket2.setTitre("Titre2");

		User user2 = new User();
		user2.setEmail("prisca2@gmail.com");
		user2.setPassword("prisca2");
		user2.setUsername("prisca2");
		ticket2.setUser(user2);

		ticketService.createTicket(ticket2);
		ticketService.assigneTicketForUser(1, 2);
	}

	public void deleteTicket() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setStatus(StatusTicket.En_Cours);
		ticket.setDescription("description");
		ticket.setTitre("Titre");

		User user = new User();
		user.setEmail("prisca@gmail.com");
		user.setPassword("prisca");
		user.setUsername("prisca");
		ticket.setUser(user);

		ticketService.deleteTicket(1);
	}

}
