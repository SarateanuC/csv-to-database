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
@AllArgsConstructor
@NoArgsConstructor
public class InsolvencyDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String commenced;
    @OneToMany(cascade= ALL)
    private List<InsolvencyAppointee> insolvencyAppointee;
    @OneToMany(cascade= ALL)
    private List<InsolvencyReport> insolvencyReport;
}
