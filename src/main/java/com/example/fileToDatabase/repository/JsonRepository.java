package com.example.fileToDatabase.repository;

import com.example.fileToDatabase.entity.json.CompanyJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JsonRepository extends JpaRepository<CompanyJson,UUID> {
}
