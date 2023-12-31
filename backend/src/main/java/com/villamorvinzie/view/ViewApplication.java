package com.villamorvinzie.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewApplication.class, args);
    }
}
