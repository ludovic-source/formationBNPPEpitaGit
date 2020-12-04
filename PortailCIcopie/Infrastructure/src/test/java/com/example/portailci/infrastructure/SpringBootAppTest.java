package com.example.portailci.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan(basePackages = { "com.example.portailci" }, lazyInit = true)
@EntityScan(basePackages = { "com.example.portailci" })
@EnableJpaRepositories
public class SpringBootAppTest {

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootAppTest.class, args);
    }

}
