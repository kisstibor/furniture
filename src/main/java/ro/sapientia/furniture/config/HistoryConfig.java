package ro.sapientia.furniture.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.sapientia.furniture.History;
import ro.sapientia.furniture.repository.HistoryRepository;

@Configuration
public class HistoryConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(
			HistoryRepository repository) {
		return args -> {
			History hisOne = new History(0L, 1L, 1L, "TestOrder", LocalDate.now());
			History hisTwo = new History(1L, 2L, 2L, "TestOrder2", LocalDate.now().minusDays(1));
			
			repository.saveAll(
					List.of(hisOne, hisTwo)
			);
		};
	}
}
