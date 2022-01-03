package com.informanaging.project.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {

    @Id
    @GeneratedValue // 자동 생성(AUTO)
    private Long id;

    @NonNull // - RequiredArgsConstructor
    private String name;

    @NonNull // - RequiredArgsConstructor
    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        Person person = (Person) object;

        if (!person.getName().equals(this.getName())) {
            return false;
        }

        if (person.getAge() != this.getAge()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (name + age).hashCode();
    }

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
