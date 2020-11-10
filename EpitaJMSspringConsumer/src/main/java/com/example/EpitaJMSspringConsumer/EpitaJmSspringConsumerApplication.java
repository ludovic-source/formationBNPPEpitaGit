package com.example.EpitaJMSspringConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpitaJmSspringConsumerApplication {

	public static void main(String[] args) {

		SpringApplication.run(EpitaJmSspringConsumerApplication.class, args);
		System.out.println("message reçu !");
	}

}
