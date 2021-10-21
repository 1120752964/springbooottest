package com.ldl.springboottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringboottestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringboottestApplication.class, args);
    }

}
