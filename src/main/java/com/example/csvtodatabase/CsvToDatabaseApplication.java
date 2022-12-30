package com.example.csvtodatabase;

import com.example.csvtodatabase.service.CsvService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CsvToDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CsvToDatabaseApplication.class, args);
//        CsvService bean = run.getBean(CsvService.class);
//        bean.csvToEntity("/home/administrator/Downloads/book/name_gender.csv");

    }

}
