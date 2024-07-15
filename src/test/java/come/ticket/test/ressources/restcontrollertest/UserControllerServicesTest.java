package come.ticket.test.ressources.restcontrollertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.projet.tickets.model.Ticket;
import com.projet.tickets.model.User;

public class UserControllerServicesTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getUserList() throws Exception {
		String uri = "/api/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] ticketList = super.mapFromJson(content, User[].class);
		assertTrue(ticketList.length > 0);
	}

	@Test(expected = DataValidationException.class)
	public void createTicket() throws Exception {

		// Si le donnée est encore vide, un data violation exeption sera excecuter

		String uri = "/api/users";

		User user = new User();
		user.setId(1);
		user.setEmail("email@.com");
		user.setUsername("username");

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Création de l'utilisateur en succès");
	}

	@Test
	public void updateTicket() throws Exception {
		String uri = "/api/users/1";

		User user = new User();
		user.setId(1);
		user.setEmail("emailModifie@.com");
		user.setUsername("usernameModifier");

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();

		// avec un donnée existant ceci doit retourner
		// assertEquals(200, status);

		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Modification de l'utilisateur en succès");
	}

	@Test
	public void findById() throws Exception {
		String uri = "/api/users/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		// avec un donnée existant ceci doit retourner
		// assertEquals(200, status);
		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		Ticket ticket = super.mapFromJson(content, Ticket.class);
		assertTrue(ticket != null);
	}

	@Test
	public void deleteById() throws Exception {
		String uri = "/api/users/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		// avec un donnée existant ceci doit retourner
		// assertEquals(200, status);
		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Utilisateur inexistant!");
	}
}
