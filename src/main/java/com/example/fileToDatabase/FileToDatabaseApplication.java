package com.example.fileToDatabase;

import com.example.fileToDatabase.repository.CsvUserRepository;
import com.example.fileToDatabase.service.CsvUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileToDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FileToDatabaseApplication.class, args);
        CsvUserService bean = run.getBean(CsvUserService.class);
        CsvUserRepository bean1 = run.getBean(CsvUserRepository.class);
        bean.copyUsersFromCsv("/home/administrator/Downloads/book/name_gender.csv");
       System.out.println(bean1.findAll().size());
    }

}
