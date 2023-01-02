package com.example.fileToDatabase.controller;

import com.example.fileToDatabase.service.TxtUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/txt-users")
@RequiredArgsConstructor
public class TxtUserController {
    private final TxtUserService txtUserService;

    @PostMapping("/add")
    public void copyFromTxt(@RequestParam("path") String path){
        txtUserService.copyUsersFromTxt(path);
    }

    @GetMapping("/gender")
    public String verifyGender(@RequestParam("name") String name,@RequestParam("year")Integer year){
       return txtUserService.verifyGender(name,year);
    }
}
