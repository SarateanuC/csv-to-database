package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles {
    @Id
    @GeneratedValue
    private UUID id;
    private String acn;
    private String asicDirectorShipYn;
    private String asicName;
    private String endDate;
    @OneToMany
    private List<RoleAddress> roleAddressList;
    @OneToMany
    private List<RoleAddressAsic> roleAddressAsicLis;
    private String roleEntity;
    @OneToMany
    private List<RolePerson> rolePersonList;
    private String roleStatus;
    private String roleType;
    private String startDate;
    private String uniqueIdentifier;
}
