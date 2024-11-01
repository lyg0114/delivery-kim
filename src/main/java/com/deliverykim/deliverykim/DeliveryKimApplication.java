package com.deliverykim.deliverykim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DeliveryKimApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryKimApplication.class, args);
	}

}
