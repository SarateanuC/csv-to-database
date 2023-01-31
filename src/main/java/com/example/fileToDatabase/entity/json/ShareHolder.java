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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShareHolder {
    @Id
    @GeneratedValue
    private UUID id;
    private String appointmentDate;
    @OneToMany
    private List<IndividualShareHolder> individualShareHolderList;
    @OneToMany
    private List<OtherShareHolder> otherShareHolders;
    @OneToMany
    private List<ShareHolderAddress> shareHolderAddresses;
    private String type;
    private String vacationDate;
}
