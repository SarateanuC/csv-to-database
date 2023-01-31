package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EntityStatus {
    @Id
    @GeneratedValue
    private UUID id;
    private String comment;
    private String endDate;
    private String entityStatus;
    private String startDate;
    private String statusReasonCode;
}
