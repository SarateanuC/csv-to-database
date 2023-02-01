package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShareHolder {
    @Id
    @GeneratedValue
    private UUID id;
    private String vacationDate;
    private String type;
    @OneToMany(cascade= ALL)
    private List<ShareHolderAddress> shareholderAddress;
    @OneToMany(cascade= ALL)
    private List<OtherShareHolder> otherShareholder;
    private String appointmentDate;
    @OneToMany(cascade= ALL)
    private List<IndividualShareHolder> individualShareholder;
}
