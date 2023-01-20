package com.informanaging.project.demo.domain;

import com.informanaging.project.demo.domain.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue // 자동 생성(AUTO)
    private Long id;

    @NonNull // - RequiredArgsConstructor
    private String name;

    @NonNull // - RequiredArgsConstructor
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY) // persistence
    @ToString.Exclude
    private Block block; // personオブジェクトに対してブロックをしたか、してなかったかを確認するpropertyなのでOne-to-one

    // lazy : block 값이 필요 할 때만 사용된다.

}
