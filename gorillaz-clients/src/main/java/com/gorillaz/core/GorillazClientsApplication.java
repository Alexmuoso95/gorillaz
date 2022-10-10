package com.gorillaz.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
public class GorillazClientsApplication implements CommandLineRunner{
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(GorillazClientsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "123";
		for (int i = 0 ; i<2; i++) {
			String passwordEncrypted = encoder.encode(password);
			System.out.println(passwordEncrypted);
		}
	}
}
