package com.example.fileToDatabase.service.impl;

import com.example.fileToDatabase.repository.TxtUserRepository;
import com.example.fileToDatabase.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TXT")
@RequiredArgsConstructor
public class TxtUserService implements FileService {
    private final TxtUserRepository txtUserRepository;

    @Override
    public boolean isCorrectExtension(String path) {
        return path.contains(".TXT");
    }

    @Override
    public void copyFromFile(String path) {
     if(isCorrectExtension(path)){
         txtUserRepository.copyFromTxt(path);
     }
    }
}
