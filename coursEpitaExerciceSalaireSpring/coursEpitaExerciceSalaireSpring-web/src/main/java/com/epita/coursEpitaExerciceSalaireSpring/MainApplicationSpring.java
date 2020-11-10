package com.epita.coursEpitaExerciceSalaireSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.epita.coursEpitaExerciceSalaireSpring.dao")
@EntityScan("com.epita.coursEpitaExerciceSalaireSpring.entite")
//pour préciser où se trouvent les controller
@ComponentScan(basePackages = {"com.epita"})
public class MainApplicationSpring {

	public static void main(String[] args) {
		SpringApplication.run(MainApplicationSpring.class, args);
		System.out.println("ça marche !");

	}

}
