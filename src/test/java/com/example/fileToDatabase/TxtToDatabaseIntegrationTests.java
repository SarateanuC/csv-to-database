package com.example.fileToDatabase;

import com.example.fileToDatabase.entity.UserTxt;
import com.example.fileToDatabase.repository.TxtUserRepository;
import com.example.fileToDatabase.service.TxtUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@Testcontainers
@SpringBootTest
class TxtToDatabaseIntegrationTests {
    @Autowired
    private TxtUserService txtUserService;
    @Autowired
    private TxtUserRepository txtUserRepository;

    @Container
    private static final PostgreSQLContainer POSTGRES_SQL_CONTAINER =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:13-alpine"))
                    .withDatabaseName("postgres")
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withReuse(true);


    @DynamicPropertySource
    static void overrideTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", POSTGRES_SQL_CONTAINER::getDriverClassName);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @BeforeAll
    public static void init() {
        POSTGRES_SQL_CONTAINER.start();
    }

    @Transactional
    @Test
    void copyFromTxtTest() {
        txtUserService.copyUsersFromTxt("/home/administrator/Downloads/book/namesbystate/AK.TXT");
        List<UserTxt> all = txtUserRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(29755);
    }


    @Transactional
    @Test
    void verifyGenderTest() {
        txtUserService.copyUsersFromTxt("/home/administrator/Downloads/book/namesbystate/AL.TXT");
        UserTxt userTxt = txtUserRepository.findById(3).orElseThrow();
        //System.out.println(userTxt.getName());
        //Optional<UserTxt> patricia = txtUserRepository.findUserCsvByNameAndYear("Patricia", 1940);
//        Assertions.assertThat(patricia.get().getNumber()).isEqualTo(6);
        //UserTxt marilyn = txtUserRepository.findUserCsvByNameAndYear("Marilyn", 1939).orElseThrow();
        //  Assertions.assertThat(marilyn.getGender()).isEqualTo("F");
    }


}
