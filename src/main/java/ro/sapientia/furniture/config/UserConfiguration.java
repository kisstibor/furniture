package ro.sapientia.furniture.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.repository.UserBodyRepository;

import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserBodyRepository userBodyRepository){
        // seeding some user into database
        return args -> {
            var jason = new UserBody(1l,
                    "Jason Smith",
                    "jason",
                    "password",
                    "jason@test.com",
                    "0755-666-777",
                    "Philadelphia"
            );
            var cara = new UserBody(2l,
                    "Cara Greene",
                    "cara",
                    "password",
                    "cara@test.com",
                    "0766-777-999",
                    "New York City"
            );
            userBodyRepository.saveAll(List.of(jason, cara));
        };
    }
}