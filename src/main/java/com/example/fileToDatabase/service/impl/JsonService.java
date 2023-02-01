package com.example.fileToDatabase.service.impl;

import com.example.fileToDatabase.entity.json.CompanyJson;
import com.example.fileToDatabase.repository.JsonRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.*;

@Service
@RequiredArgsConstructor
public class JsonService {
    private final JsonRepository jsonRepository;

    @SneakyThrows
    public void testJson(String path) {
        File jsonFile = new File(path).getAbsoluteFile();
        JsonFactory f = new MappingJsonFactory();
        try (JsonParser parser = f.createParser(jsonFile)) {
            parser.nextToken();
            parser.nextToken();
            if (parser.nextToken() != START_OBJECT) {
                throw new IllegalStateException("Expected content to be an object");
            }
            while (parser.nextToken() != END_OBJECT) {
                String fieldName = parser.getCurrentName();
                if ("entity".equals(fieldName)) {
                    if (parser.nextToken() != START_ARRAY) {
                        throw new IllegalStateException("Expected content to be an array");
                    }
                    List<CompanyJson> companyJsons = new ArrayList<>();
                    while (parser.nextToken() != END_ARRAY) {
                        String currentName = parser.getCurrentName();
                        ObjectMapper objectMapper = new ObjectMapper();
                        CompanyJson companyJson = objectMapper.readValue(parser, CompanyJson.class);
                        companyJsons.add(companyJson);
                    }
                    jsonRepository.saveAll(companyJsons);
                } else {
                    System.out.println("Unprocessed property: " + fieldName);
                    parser.skipChildren();
                }
            }
        }
    }
}


