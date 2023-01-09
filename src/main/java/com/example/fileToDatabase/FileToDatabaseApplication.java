package com.example.fileToDatabase;

import com.example.fileToDatabase.service.impl.XmlUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileToDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FileToDatabaseApplication.class, args);
        XmlUserService bean = run.getBean(XmlUserService.class);
        bean.copyFromFile("/home/administrator/Downloads/files/VO.xml");
    }

}
