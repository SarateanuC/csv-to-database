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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsolvencyAppointee {
    @Id
    @GeneratedValue
    private UUID id;
    private String appointmentDate;
    private String email;
    private String firstName;
    private String fullName;
    @OneToMany(cascade= ALL)
    private List<InsolvencyAppointeeAddress> insolvencyAppointeeAddress;
    @OneToMany(cascade= ALL)
    private List<InsolvencyAppointeePhoneNumber> insolvencyAppointeePhoneNumber;
    private String lastName;
    private String middleNames;
    private String organisationName;
    private String vacationDate;
}
