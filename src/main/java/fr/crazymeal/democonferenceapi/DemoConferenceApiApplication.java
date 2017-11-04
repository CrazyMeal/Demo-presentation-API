package fr.crazymeal.democonferenceapi;

import fr.crazymeal.democonferenceapi.model.Presentation;
import fr.crazymeal.democonferenceapi.model.Speaker;
import fr.crazymeal.democonferenceapi.repository.PresentationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoConferenceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoConferenceApiApplication.class, args);
	}

	@Bean
	public String init(PresentationRepository repository) {
		Speaker s = new Speaker();
		s.setFirstName("Kevin");
		s.setLastName("Paquet");

		Presentation p = new Presentation();
		s.animate(p);
		p.setTitle("Une pres'");
		p.setDescription("Super");

		repository.save(p);
		System.out.println("Savedit!");
		return "hello";
	}
}
