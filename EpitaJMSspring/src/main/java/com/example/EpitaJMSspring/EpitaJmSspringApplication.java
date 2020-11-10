package com.example.EpitaJMSspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EpitaJmSspringApplication {

	public static void main(String[] args) {

		SpringApplication.run(EpitaJmSspringApplication.class, args);
		System.out.println("prêt pour les tests JMS !");
	}

}
