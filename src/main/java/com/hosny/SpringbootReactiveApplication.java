package com.hosny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class SpringbootReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootReactiveApplication.class, args);
    }

}
