package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricOtherShareHolder {
    @Id
    @GeneratedValue
    private UUID id;
    private String companyNumber;
    private String currentEntityName;
    private String entityType;
    private String nzbn;


}
