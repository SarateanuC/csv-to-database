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
    private String asicDirectorshipYn;
    private String asicName;
    private String endDate;
    @OneToMany(cascade= ALL)
    private List<RoleAddress> roleAddress;
    @OneToMany(cascade= ALL)
    private List<RoleAddressAsic> roleAsicAddress;
    private String roleEntity;
    @OneToMany(cascade= ALL)
    private List<RolePerson> rolePerson;
    private String roleStatus;
    private String roleType;
    private String startDate;
    private String uniqueIdentifier;
}
