package com.example.fileToDatabase.service;

import com.example.fileToDatabase.repository.TxtUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TxtUserService {
    private final TxtUserRepository txtUserRepository;

    public void copyUsersFromTxt(String path){
        txtUserRepository.copyFromTxt(path);
    }

    public String verifyGender(Integer id) {
        return txtUserRepository.findById(id)
                .orElseThrow()
                .getGender();
    }
}
