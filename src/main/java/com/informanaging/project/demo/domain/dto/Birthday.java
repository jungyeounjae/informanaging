package com.informanaging.project.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Embeddable // entity 에 속해 있는 dto
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Birthday {
    private int yearOfBirthday;

    private int monthOfBirthday;

    private int dayOfBirthday;

    public Birthday(LocalDate birthday) {
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }
}
