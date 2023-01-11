package ro.sapientia.furniture.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.sapientia.furniture.model.HistoryBody;
import ro.sapientia.furniture.repository.HistoryRepository;

@Configuration
public class HistoryConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(
			HistoryRepository repository) {
		return args -> {
			HistoryBody hisOne = new HistoryBody(0L, 1L, 1L, "TestOrder", LocalDate.now());
			
			repository.saveAll(
					List.of(hisOne)
			);
		};
	}
}
