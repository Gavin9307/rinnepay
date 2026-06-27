package com.gavin.rinnepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RinnepayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RinnepayApplication.class, args);
	}

}
