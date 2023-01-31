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
public class InsolvencyDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String commenced;
    @OneToMany
    private List<InsolvencyAppointee> insolvencyAppointeeList;
}
