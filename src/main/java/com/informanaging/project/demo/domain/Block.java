package com.informanaging.project.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * person who you blocked Entity
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private LocalDate blockStartDate;

    private LocalDate blockEndDate;

    private String blockReason;
}
