package com.example.fileToDatabase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.concurrent.atomic.AtomicReference;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserXml {
    @Id
    private String documentId;
    private String documentCreationDate;
    private String ONDate;
    private String view;
    private String details;
    private String category;
    private String productivity;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String regionCode;
    private String regionType;
    private String regionName;
    private String cityType;
    private String cityName;
    private AtomicReference<StringBuilder> text;
}
