package com.example.fileToDatabase.controller;

import com.example.fileToDatabase.service.XmlUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/xml-users")
@RequiredArgsConstructor
public class XmlController {
    private final XmlUserService xmlUserService;

    @PostMapping("/add")
    public void copyFromTxt(@RequestParam("path") String path) {
        xmlUserService.copyUsersToTable(path);
    }
}
