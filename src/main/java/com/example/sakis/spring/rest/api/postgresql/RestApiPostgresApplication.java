package com.example.sakis.spring.rest.api.postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RestApiPostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiPostgresApplication.class, args);
    }

}
