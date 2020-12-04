package com.example.portailci.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootConfiguration
@EnableJpaRepositories
@EntityScan("com.example.portailci.domain")
//pour préciser où se trouvent les controller
@ComponentScan(basePackages = {"com.example.portailci"}, lazyInit = true )
public class SpringBootAppTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppTestApplication.class, args);
        System.out.println("Les tests peuvent démarrer");

    }
}
