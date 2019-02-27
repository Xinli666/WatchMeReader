package com.userexample.userexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserexampleApplication {
    public static Recommend recommend = new Recommend();

    public static void main(String[] args) {
        SpringApplication.run(UserexampleApplication.class, args);
        recommend.calculateVectorMatrix();
    }
}
