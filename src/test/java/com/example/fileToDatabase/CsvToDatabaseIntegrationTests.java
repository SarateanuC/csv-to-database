package com.example.fileToDatabase;

import com.example.fileToDatabase.entity.UserCsv;
import com.example.fileToDatabase.repository.CsvUserRepository;
import com.example.fileToDatabase.service.CsvUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest
public class CsvToDatabaseIntegrationTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CsvUserService csvUserService;
    @Autowired
    private CsvUserRepository csvUserRepository;
    @Container
    private static final PostgreSQLContainer POSTGRES_SQL_CONTAINER =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:13-alpine"))
                    .withDatabaseName("postgres")
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withFileSystemBind("/home/administrator/Downloads/files",
                            "/docker-entrypoint-initdb.d/", BindMode.READ_ONLY)
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

    @AfterAll
    public static void close() {
        POSTGRES_SQL_CONTAINER.close();
    }

    @Test
    @SneakyThrows
    void test() {
        assertThat(csvUserRepository.findAll()).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    @SneakyThrows
    void copyFromCsvTest() {
        mockMvc.perform(
                        post("http://localhost:8086/api/csv-users/" +
                                "add?path=/docker-entrypoint-initdb.d/name_gender.csv")
                                .contentType("application/json"))
                .andExpect(status().isOk());
        List<UserCsv> all = csvUserRepository.findAll();
        assertThat(all.size()).isEqualTo(95026);
    }

    @Test
    @SneakyThrows
    void verityGenderTest() {
        csvUserService.copyUsersFromCsv("/docker-entrypoint-initdb.d/name_gender.csv");
        MvcResult mvcResult = mockMvc.perform(
                        get("http://localhost:8086/api/csv-users/gender?name=Aaden")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).isEqualTo("M");
    }
}
