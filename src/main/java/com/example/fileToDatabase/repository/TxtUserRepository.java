package com.example.fileToDatabase.repository;

import com.example.fileToDatabase.entity.UserTxt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TxtUserRepository extends JpaRepository<UserTxt, Integer> {
    @Query("select copy_from_txt(:filename)")
    void copyFromTxt(String filename);
}
