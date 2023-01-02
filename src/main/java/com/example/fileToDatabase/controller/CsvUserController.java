package com.example.fileToDatabase.controller;

import com.example.fileToDatabase.service.CsvUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/csv-users")
@RequiredArgsConstructor
public class CsvUserController {
    private final CsvUserService csvUserService;
    @PostMapping("/add")
    public void copyFromCsv(@RequestParam("path") String path){
        csvUserService.copyUsersFromCsv(path);
    }

    @GetMapping("/gender")
    public String verifyGender(@RequestParam("name") String name){
        return csvUserService.verifyGender(name);
    }
}
