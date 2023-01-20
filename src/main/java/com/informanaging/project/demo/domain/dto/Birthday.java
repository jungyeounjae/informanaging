package com.informanaging.project.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable // entity 에 속해 있는 dto
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Birthday {
    private int yearOfBirthday;
    private int monthOfBirthday;
    private int dayOfBirthday;
}
