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
public class HistoricShareHolder {
    @Id
    @GeneratedValue
    private UUID id;
    private String appointmentDate;
    @OneToMany(cascade= ALL)
    private List<HistoricalIndividualShareHolder> historicIndividualShareholder;
    @OneToMany(cascade= ALL)
    private List<HistoricOtherShareHolder> historicOtherShareholder;
    @OneToMany(cascade= ALL)
    private List<HistoricShareHolderAddress> historicShareholderAddress;
    private String type;
    private String vacationDate;

}
