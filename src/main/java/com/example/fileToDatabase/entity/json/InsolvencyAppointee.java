package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsolvencyAppointee {
    @Id
    @GeneratedValue
    private UUID id;
    private String appointmentDate;
    private String email;
    private String firstname;
    private String fullName;
    @OneToMany
    private List<InsolvencyAppointeeAddress> insolvencyAppointeeAddressList;
    @OneToMany
    private List<InsolvencyAppointeePhoneNumber> phoneNumbers;
    private String lastname;
    private String middleNames;
    private String organisationName;
    private String vacationDate;
}
