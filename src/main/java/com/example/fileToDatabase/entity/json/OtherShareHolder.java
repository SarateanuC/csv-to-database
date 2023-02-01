package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherShareHolder {
    @Id
    @GeneratedValue
    private UUID id;
    private String companyNumber;
    private String currentEntityName;
    private String entityType;
    private String nzbn;
}
