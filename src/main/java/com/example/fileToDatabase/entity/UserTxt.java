package com.example.fileToDatabase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserTxt {
    @Id
    private Integer id;
    private String stateId;
    private Integer year;
    private String name;
    private String gender;
    private Integer number;
}
