package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UltimateHoldingCompany {
    @Id
    @GeneratedValue
    private UUID id;
    private String country;
    private String effectiveDate;
    private String name;
    private String number;
    private String nzbn;
    private String type;
    @OneToMany
    private List<UltimateHoldingCompanyAddress> ultimateHoldingCompanyAddressList;
    private String yn;
}
