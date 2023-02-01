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
@AllArgsConstructor
@NoArgsConstructor
public class EntityNames {
    @Id
    @GeneratedValue
    private UUID id;
    private String endDate;
    private String entityName;
    private String startDate;
    private String uniqueIdentifier;
}
