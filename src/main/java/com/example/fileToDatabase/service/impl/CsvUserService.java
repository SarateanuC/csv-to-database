package com.example.fileToDatabase.service.impl;

import com.example.fileToDatabase.repository.CsvUserRepository;
import com.example.fileToDatabase.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CSV")
@RequiredArgsConstructor
public class CsvUserService implements FileService {
    private final CsvUserRepository csvUserRepository;

    @Override
    public boolean isCorrectExtension(String path) {
        return path.contains(".csv");
    }

    @Override
    public void copyFromFile(String path) {
        if (isCorrectExtension(path)) {
            csvUserRepository.copyFromCsv(path);
        }
    }
}
