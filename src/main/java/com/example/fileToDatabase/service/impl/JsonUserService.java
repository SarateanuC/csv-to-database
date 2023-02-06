package com.example.fileToDatabase.service.impl;

import com.example.fileToDatabase.entity.json.CompanyJson;
import com.example.fileToDatabase.repository.JsonRepository;
import com.example.fileToDatabase.service.FileService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.START_ARRAY;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.range;

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
            ObjectMapper objectMapper = new ObjectMapper();
            try (JsonParser jp = new JsonFactory().createParser(new File(path).getAbsoluteFile())) {
                List<CompanyJson> value = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    jp.nextToken();
                    if ("entity".equals(jp.currentName()) && jp.nextToken() == START_ARRAY) {
                        while (jp.nextToken() != END_ARRAY) {
                            value.add(objectMapper.readValue(jp, CompanyJson.class));
                        }
                        Collection<List<CompanyJson>> partitionedList = range(0, value.size())
                                .boxed()
                                .collect(groupingBy(partition -> (partition / 50),
                                        mapping(value::get, toList())))
                                .values();
                        partitionedList.parallelStream().forEach(jsonRepository::saveAll);
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException" + e);
            }
        }
    }
}

