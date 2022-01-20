package com.informanaging.project.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * person who you blocked Entity
 */
@Entity
@Data
public class Block {

    @Id
    @Getter
    private Long id;

    private String name;

    private LocalDate blockStartDate;

    private LocalDate blockEndDate;

    private String blockReason;
}
