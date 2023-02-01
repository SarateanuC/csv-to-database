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
public class EmailAddress {
    @Id
    @GeneratedValue
    private UUID id;
    private String emailAddress;
    private String emailPurpose;
    private String emailPurposeDescription;
    private String startDate;
    private String uniqueIdentifier;
}
