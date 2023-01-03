package com.example.fileToDatabase;

import com.example.fileToDatabase.entity.UserTxt;
import com.example.fileToDatabase.repository.TxtUserRepository;
import com.example.fileToDatabase.service.TxtUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest
class TxtToDatabaseIntegrationTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
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
    void copyFromTxtTest() {
        mockMvc.perform(
                        post("http://localhost:8086/api/txt-users/" +
                                "add?path=/docker-entrypoint-initdb.d/namesbystate/AK.TXT")
                                .contentType("application/json"))
                .andExpect(status().isOk());
        List<UserTxt> all = txtUserRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(59510);
    }

    @Test
    @SneakyThrows
    void verifyGenderTest() {
        txtUserService.copyUsersFromTxt("/docker-entrypoint-initdb.d/namesbystate/AK.TXT");
        MvcResult mvcResult = mockMvc.perform(
                        get("http://localhost:8086/api/txt-users/gender?id=5")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).isEqualTo("F");

    }
}
