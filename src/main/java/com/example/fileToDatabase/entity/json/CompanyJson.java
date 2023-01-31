package com.example.fileToDatabase.entity.json;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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
    @OneToMany
    private List<Company> company;
    @OneToMany
    private List<EmailAddress> emailAddresses;
    private String entityName;
    @OneToMany
    private List<EntityNames> entityNames;
    @OneToMany
    private List<EntityStatus> entityStatuses;
    private String entityStatusCode;
    private String entityStatusDescription;
    private String entityTypeCode;
    private String entityTypeDescription;
    private String gstEffectiveStatusDescription;
    @OneToMany
    private List<HistoricShareHolder> historicShareHolders;
    @OneToMany
    private List<IndustryClassification>industryClassifications;
    @OneToMany
    private List<InsolvencyDetails> insolvencyDetails;
    private String lastUpdateDate;
    private String locationIdentifier;
    private String nzbn;
    @OneToMany
    private List<OtherAddress> otherAddresses;
    @OneToMany
    private List<PhoneNumber> phoneNumbers;
    @OneToMany
    private List<PhysicalAddress> physicalAddresses;
    @OneToMany
    private List<PostalAddress> postalAddresses;
    @OneToMany
    private List<PrincipalPlaceOfActivity> principalPlaceOfActivities;
    private String privacySettings;
    @OneToMany
    private List<RegisteredAddress> registeredAddresses;
    private String registrationDate;
    @OneToMany
    private List<Roles> roles;
    private String sourceRegister;
    private String sourceRegisterUniqueIdentifier;
    private String roleStatus;
    private String startDate;
    private String supportingInformation;
    @OneToMany
    private List<TradingNames> tradingNames;
    @OneToMany
    private List<UltimateHoldingCompany> ultimateHoldingCompanies;
    @OneToMany
    private List<Website> websites;




}
