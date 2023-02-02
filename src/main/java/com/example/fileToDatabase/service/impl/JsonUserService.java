package com.example.fileToDatabase.service.impl;

import com.example.fileToDatabase.entity.json.CompanyJson;
import com.example.fileToDatabase.repository.JsonRepository;
import com.example.fileToDatabase.service.FileService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;

@Service("JSON")
@RequiredArgsConstructor
public class JsonUserService implements FileService {
    private final JsonRepository jsonRepository;

    @Override
    public boolean isCorrectExtension(String path) {
        return path.contains(".json");
    }

    @Override
    @SneakyThrows
    public void copyFromFile(String path) {
        if (isCorrectExtension(path)) {
            File jsonFile = new File(path).getAbsoluteFile();
            JsonFactory f = new MappingJsonFactory();
            ObjectMapper objectMapper = new ObjectMapper();
            try (JsonParser jp = new JsonFactory().createParser(jsonFile)) {
                List<CompanyJson> value = new ArrayList<>();
                jp.nextToken();
                jp.nextToken();
                jp.nextToken();
                jp.nextToken();
                jp.nextToken();
                while (jp.nextToken() != END_ARRAY) {
                    value.add(objectMapper.readValue(jp, CompanyJson.class));
                }
                value.parallelStream().forEach(jsonRepository::save);
            } catch (IOException e) {
                System.err.println("IOException" + e);
            }
        }
    }
}

