package com.example.fileToDatabase;

import com.example.fileToDatabase.service.impl.XmlUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileToDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileToDatabaseApplication.class, args);
    }

}
