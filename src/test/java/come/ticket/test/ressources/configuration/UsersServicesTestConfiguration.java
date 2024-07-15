package come.ticket.test.ressources.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.projet.tickets.services.impl.UserServices;

@Profile("test")
@Configuration
public class UsersServicesTestConfiguration {

	@Bean
	@Primary
	public UserServices productService() {
		return Mockito.mock(UserServices.class);
	}
}
