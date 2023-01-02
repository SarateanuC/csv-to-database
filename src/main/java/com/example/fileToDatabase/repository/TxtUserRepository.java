package com.example.fileToDatabase.repository;

import com.example.fileToDatabase.entity.UserCsv;
import com.example.fileToDatabase.entity.UserTxt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TxtUserRepository extends JpaRepository<UserTxt, Integer> {
    @Query("select copy_from_txt(:filename)")
    void copyFromTxt(String filename);

    Optional<UserTxt> findUserCsvByNameAndYear(String name,Integer year);

}
