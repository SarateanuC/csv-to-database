package com.example.fileToDatabase;

import com.example.fileToDatabase.entity.UserCsv;
import com.example.fileToDatabase.entity.UserTxt;
import com.example.fileToDatabase.entity.UserXml;
import com.example.fileToDatabase.repository.CsvUserRepository;
import com.example.fileToDatabase.repository.TxtUserRepository;
import com.example.fileToDatabase.repository.XmlRepository;
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
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest
class FileToDatabaseIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TxtUserRepository txtUserRepository;
    @Autowired
    private CsvUserRepository csvUserRepository;
    @Autowired
    private XmlRepository xmlRepository;

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
                        post("http://localhost:8081/api/file/" +
                                "add?path=/docker-entrypoint-initdb.d/namesbystate/AK.TXT&extension=TXT")
                                .contentType("application/json"))
                .andExpect(status().isOk());
        List<UserTxt> all = txtUserRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(29755);
    }

    @Test
    @SneakyThrows
    void copyFromCsvTest() {
        mockMvc.perform(
                        post("http://localhost:8081/api/file/" +
                                "add?path=/docker-entrypoint-initdb.d/name_gender.csv&extension=CSV")
                                .contentType("application/json"))
                .andExpect(status().isOk());
        List<UserCsv> all = csvUserRepository.findAll();
        assertThat(all.size()).isEqualTo(95026);
    }

    @Test
    @SneakyThrows
    void copyFromXmlTest() {
        mockMvc.perform(
                        post("http://localhost:8081/api/file/" +
                                "add?path=/docker-entrypoint-initdb.d/VO.xml&extension=XML")
                                .contentType("application/json"))
                .andExpect(status().isOk());
        List<UserXml> all = xmlRepository.findAll();
        assertThat(all.size()).isEqualTo(900);
    }
}
