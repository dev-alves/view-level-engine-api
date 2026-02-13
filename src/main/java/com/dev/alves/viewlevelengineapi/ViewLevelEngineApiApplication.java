package com.dev.alves.viewlevelengineapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ViewLevelEngineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewLevelEngineApiApplication.class, args);
    }

}
