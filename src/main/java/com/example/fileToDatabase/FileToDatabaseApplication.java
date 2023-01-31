package com.example.fileToDatabase;

import com.example.fileToDatabase.service.impl.JsonService;
import com.example.fileToDatabase.service.impl.XmlUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileToDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FileToDatabaseApplication.class, args);
        JsonService bean = run.getBean(JsonService.class);
        bean.testJson("C:\\Users\\Carolina\\Downloads\\Telegram Desktop\\20190902_1.json");
    }

}
