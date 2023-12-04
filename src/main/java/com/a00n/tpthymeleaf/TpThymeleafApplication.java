package com.a00n.tpthymeleaf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.a00n.tpthymeleaf.entities.User;
import com.a00n.tpthymeleaf.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TpThymeleafApplication {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpThymeleafApplication.class, args);
	}

	@Bean
	public CommandLineRunner a00n() {
		return args -> {
			User user1 = User.builder().name("ayoub").email("ayoub@gmail.com").build();
			userRepository.save(user1);
		};
	}

}
