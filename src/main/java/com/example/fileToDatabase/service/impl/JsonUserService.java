package com.example.fileToDatabase.service.impl;

import com.example.fileToDatabase.service.FileService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;

@Service("JSON")
public class JsonUserService implements FileService {
    @Override
    public boolean isCorrectExtension(String path) {
        return path.contains(".json");
    }

    @Override
    @SneakyThrows
    public void copyFromFile(String path) {
        if (isCorrectExtension(path)) {
            File jsonFile = new File(path).getAbsoluteFile();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonFile);

        }
    }
}
