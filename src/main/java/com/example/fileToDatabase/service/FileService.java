package com.example.fileToDatabase.service;

public interface FileService {
    boolean isCorrectExtension(String path);
    void copyFromFile(String path);
}
