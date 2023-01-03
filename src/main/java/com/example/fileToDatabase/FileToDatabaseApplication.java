package com.example.fileToDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileToDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FileToDatabaseApplication.class, args);
    }

}
