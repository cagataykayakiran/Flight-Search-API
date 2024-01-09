package com.amadeus;

import com.amadeus.dto.AuthRegisterRequest;
import com.amadeus.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.amadeus.enums.Role.ADMIN;
import static com.amadeus.enums.Role.USER;


@SpringBootApplication
@Configuration
@EnableScheduling
public class FlightSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightSearchApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = AuthRegisterRequest.builder()
                    .username("admin")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());

            var user = AuthRegisterRequest.builder()
                    .username("user")
                    .password("password")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(user).getToken());

        };
    }
}


