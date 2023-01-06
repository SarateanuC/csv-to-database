package com.example.fileToDatabase;

import com.example.fileToDatabase.entity.UserXml;
import com.example.fileToDatabase.service.XmlUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class FileToDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FileToDatabaseApplication.class, args);
        XmlUserService bean = run.getBean(XmlUserService.class);
        List<UserXml> userXmls = bean.copyUsersToTable("/home/administrator/Downloads/files/VO.xml");

        bean.copyToCsv(userXmls);

    }

}
