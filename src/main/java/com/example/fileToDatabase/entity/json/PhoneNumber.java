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
public class PhoneNumber {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String phoneAreaCode;
    private String phoneCountryCode;
    private String phoneNumber;
    private String phonePurpose;
    private String phonePurposeDescription;
    private String startDate;
    private String uniqueIdentifier;
}
