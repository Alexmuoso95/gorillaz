package com.gorillaz.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gorillaz.core.config.downstream.DownStreamServicesProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@SpringBootApplication
@EnableConfigurationProperties(DownStreamServicesProperties.class)
@EnableSwagger2
@EnableRetry
public class GorillazApplication implements CommandLineRunner{
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(GorillazApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String password = "123";
//		for (int i = 0 ; i<2; i++) {
//			String passwordEncrypted = encoder.encode(password);
//			System.out.println(passwordEncrypted);
//		}
	}
}
