package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarsCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarsCatalogApplication.class, args);
    }
}
