package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private UUID id;
    private String annualReturnFilingMonth;
    private String annualReturnLastFiled;
    private String countryOfOrigin;
    private String extensiveShareholding;
    private String financialReportFilingMonth;
    private boolean hasConstitutionFiled;
    private String nzsxCode;
    private String overseasCompany;
    @OneToMany(cascade= ALL)
    private List<ShareHolding> shareholding;
    private String stockExchangeListed;
}
