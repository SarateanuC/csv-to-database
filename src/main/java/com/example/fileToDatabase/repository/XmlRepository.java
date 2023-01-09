package com.example.fileToDatabase.repository;

import com.example.fileToDatabase.entity.UserXml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface XmlRepository extends JpaRepository<UserXml, UUID> {
    @Query(value = "select copy_from_xml(?1)",nativeQuery = true)
    void copyFromXml(String filename);
}
