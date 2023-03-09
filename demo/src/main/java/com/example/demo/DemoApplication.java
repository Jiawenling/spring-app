package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Model.User;
import com.example.demo.Repo.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository users;
	
	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			users.save(new User("user", "Mr Smith", encoder.encode("password"),"ROLE_USER"));
			users.save(new User("manager","Mrs Smith", encoder.encode("password"),"ROLE_USER,ROLE_MANAGER"));
		};
	}
}
