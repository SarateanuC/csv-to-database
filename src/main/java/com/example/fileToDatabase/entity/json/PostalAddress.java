package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostalAddress {
    @Id
    @GeneratedValue
    private UUID id;
}
