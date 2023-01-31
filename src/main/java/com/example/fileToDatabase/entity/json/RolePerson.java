package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePerson {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstname;
    private String lastname;
    private String middleNames;
    private String title;
}
