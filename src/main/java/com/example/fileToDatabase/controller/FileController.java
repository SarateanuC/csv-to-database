package com.example.fileToDatabase.controller;

import com.example.fileToDatabase.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
   private final Map<String,FileService> fileServices ;

    @PostMapping("/add")
    public void copyFile(@RequestParam("path") String path, @RequestParam("extension") String extension) {
        this.fileServices.get(extension).copyFromFile(path);
    }
}
