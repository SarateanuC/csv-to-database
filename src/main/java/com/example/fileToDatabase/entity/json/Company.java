package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private UUID id;
    private String annualReturnLastFiled;
    private String annualReturnFillingMonth;
    private String countryOfOrigin;
    private String extensiveShareHolding;
    private String financialReportFillingMonth;
    private String hasConstitutionFiled;
    private String nzsxCode;
    private String overseasCompany;
    @OneToMany
    private List<ShareHolding> shareHolding;
    private String stockExchangeListed;
}
