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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareAllocation {
    @Id
    @GeneratedValue
    private UUID id;
    private String allocation;
    @OneToMany
    private List<ShareHolder> shareHolder;

}
