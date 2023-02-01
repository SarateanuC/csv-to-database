package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UltimateHoldingCompanyAddress {
    @Id
    @GeneratedValue
    private UUID id;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String careOf;
    private String countryCode;
    private String description;
    private String endDate;
    private String pafId;
    private String postCode;
    private String startDate;
    private String uniqueIdentifier;
}
