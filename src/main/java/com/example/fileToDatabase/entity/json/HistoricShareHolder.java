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
@AllArgsConstructor
@NoArgsConstructor
public class HistoricShareHolder {
    @Id
    @GeneratedValue
    private UUID id;
    private String appointmentDate;
    @OneToMany
    private List<HistoricIndividualShareHolder> historicIndividualShareHolderList;
    @OneToMany
    private List<HistoricOtherShareHolder> historicOtherShareHolders;
    @OneToMany
    private List<HistoricShareHolderAddress> historicShareHolderAddresses;
    private String type;
    private String vacationDate;
}
