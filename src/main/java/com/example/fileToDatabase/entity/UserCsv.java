package com.example.fileToDatabase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCsv {
    @Id
    private Integer id;
    private String name;
    private String gender;
    private Double probability;
}
