package com.informanaging.project.demo.domain;

import com.informanaging.project.demo.controller.dto.PersonDto;
import com.informanaging.project.demo.domain.dto.Birthday;
import lombok.*;
import org.h2.util.StringUtils;
import org.springframework.util.StringUtils.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성(AUTO)
    private Long id;

    // nonnull 으론 에러가 발생하지 않는다 notempty와 column을 추가. name이 스트링이기 때문에 notempty를 추가
    @NonNull // - RequiredArgsConstructor
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull // - RequiredArgsConstructor
    @Min(1)
    private int age;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY) // persistence
    @ToString.Exclude // 해당 필드의 출력을 제외시켜준다
    private Block block; // personオブジェクトに対してブロックをしたか、してなかったかを確認するpropertyなのでOne-to-one

    // lazy : block 값이 필요 할 때만 사용된다.

    public void set(PersonDto personDto) {
        // ageはintなので、ageがnullだったら、自動的に0が入ります。
        // 0の場合は、設定しないとの意味。
        if (personDto.getAge() != 0) {
            this.setAge(personDto.getAge());
        }

        if(!StringUtils.isNullOrEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
        }

        if(!StringUtils.isNullOrEmpty(personDto.getBloodType())) {
            this.setBloodType(personDto.getBloodType());
        }

        if(!StringUtils.isNullOrEmpty(personDto.getAddress())) {
            this.setAddress(personDto.getAddress());
        }

        if(!StringUtils.isNullOrEmpty(personDto.getPhoneNumber())) {
            this.setPhoneNumber(personDto.getPhoneNumber());
        }

        if(!StringUtils.isNullOrEmpty(personDto.getJob())) {
            this.setJob(personDto.getJob());
        }
    }
}
