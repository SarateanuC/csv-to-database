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
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyJson {
    @Id
    @GeneratedValue
    private UUID id;
    private String australianBusinessNumber;
    private String australianCompanyNumber;
    private String australianServiceAddress;
    @OneToMany(cascade = ALL)
    private List<Company> company;
    @OneToMany(cascade = ALL)
    private List<EmailAddress> emailAddress;
    private String entityName;
    @OneToMany(cascade = ALL)
    private List<EntityNames> entityNames;
    @OneToMany(cascade = ALL)
    private List<EntityStatus> entityStatus;
    private String entityStatusCode;
    private String entityStatusDescription;
    private String entityTypeCode;
    private String entityTypeDescription;
    private String gstEffectiveDate;
    private String gstStatus;
    private String gstEffectiveStatusDescription;
    @OneToMany(cascade = ALL)
    private List<HistoricShareHolder> historicShareholder;
    @OneToMany(cascade = ALL)
    private List<IndustryClassification> industryClassification;
    @OneToMany(cascade = ALL)
    private List<InsolvencyDetails> insolvencyDetails;
    private String lastUpdatedDate;
    private String locationIdentifier;
    private String nzbn;
    @OneToMany(cascade = ALL)
    private List<OtherAddress> otherAddress;
    @OneToMany(cascade = ALL)
    private List<PhoneNumber> phoneNumber;
    @OneToMany(cascade = ALL)
    private List<PhysicalAddress> physicalAddress;
    @OneToMany(cascade = ALL)
    private List<PostalAddress> postalAddress;
    @OneToMany(cascade = ALL)
    private List<PrincipalPlaceOfActivity> principalPlaceOfActivity;
    private String privacySettings;
    @OneToMany(cascade = ALL)
    private List<RegisteredAddress> registeredAddress;
    private String registrationDate;
    @OneToMany(cascade = ALL)
    private List<Roles> roles;
    private String sourceRegister;
    private String sourceRegisterUniqueIdentifier;
    private String roleStatus;
    private String startDate;
    private String supportingInformation;
    @OneToMany(cascade = ALL)
    private List<TradingNames> tradingNames;
    @OneToMany(cascade = ALL)
    private List<UltimateHoldingCompany> ultimateHoldingCompany;
    @OneToMany(cascade = ALL)
    private List<Website> website;
}
