package come.ticket.test.ressources.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.projet.tickets.services.impl.TicketServices;

@Profile("test")
@Configuration
public class TicketServicesTestConfiguration {

	@Bean
	@Primary
	public TicketServices productService() {
		return Mockito.mock(TicketServices.class);
	}
}
