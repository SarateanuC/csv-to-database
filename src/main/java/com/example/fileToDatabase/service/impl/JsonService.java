package com.example.fileToDatabase.service.impl;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.fasterxml.jackson.core.JsonToken.*;

@Service
public class JsonService {
    @SneakyThrows
    public void testJson(String path) {
        File jsonFile = new File(path).getAbsoluteFile();
        JsonFactory f = new MappingJsonFactory();
        JsonParser parser = f.createParser(jsonFile);
        var jsonToken = parser.nextToken();
        while (parser.nextToken() != END_OBJECT || parser.nextToken() != START_OBJECT) {
            String fieldName = parser.getCurrentName();
            // move from field name to field value
            jsonToken = parser.nextToken();
            if ("companies".equals(fieldName)) {
                System.out.println(parser.getCurrentName());
                while(parser.nextToken() != START_ARRAY || parser.nextToken()!= END_ARRAY) {
                    String currentName = parser.getCurrentName();
                    jsonToken = parser.nextToken();
                    if ("entity".equals(currentName)) {
                        System.out.println("entity");
                        JsonNode node = parser.readValueAsTree();
                        //System.out.println(node.get("countryOfOrigin").asText());
                    }
                }
            } else {
                System.out.println("Unprocessed property: " + fieldName);
                parser.skipChildren();
            }
        }
}
}
