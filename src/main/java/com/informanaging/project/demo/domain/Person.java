package com.informanaging.project.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue // 자동 생성(AUTO)
    private Long id;

    private String name;

    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", job='" + job + '\'' +
                '}';
    }
}
