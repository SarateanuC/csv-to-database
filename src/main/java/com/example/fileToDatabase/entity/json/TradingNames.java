package com.example.fileToDatabase.entity.json;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TradingNames {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String startDate;
    private String uniqueIdentifier;
}
