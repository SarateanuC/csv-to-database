package com.example.fileToDatabase.service;

import com.example.fileToDatabase.repository.CsvUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CsvUserService {
    private final CsvUserRepository csvUserRepository;

    public void copyUsersFromCsv(String path) {
        csvUserRepository.copyFromCsv(path);
    }

    public String verifyGender(String name) {
        return csvUserRepository.findUserCsvByName(name)
                .orElseThrow()
                .getGender();
    }
}
