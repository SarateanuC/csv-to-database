package com.example.fileToDatabase.repository;

import com.example.fileToDatabase.entity.UserCsv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CsvUserRepository extends JpaRepository<UserCsv, Integer> {
    //    @Transactional
//    @Modifying
    @Query("select copy_from_csv(:filename)")
    void copyFromCsv(String filename);

    Optional<UserCsv> findUserCsvByName(String name);


}
