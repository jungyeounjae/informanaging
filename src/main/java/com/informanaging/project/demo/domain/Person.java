package com.informanaging.project.demo.domain;

import com.informanaging.project.demo.controller.dto.PersonDto;
import com.informanaging.project.demo.domain.dto.Birthday;
import lombok.*;
import org.h2.util.StringUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성(AUTO)
    private Long id;

    // nonnull 으론 에러가 발생하지 않는다 notempty와 column을 추가. name이 스트링이기 때문에 notempty를 추가
    @NonNull // - RequiredArgsConstructor
    @NotEmpty
    @Column(nullable = false)
    private String name;

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

    @ColumnDefault("0") // 0 == false, true이면 삭제로 간주다
    private boolean deleted;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY) // persistence
    @ToString.Exclude // 해당 필드의 출력을 제외시켜준다
    private Block block; // personオブジェクトに対してブロックをしたか、してなかったかを確認するpropertyなのでOne-to-one

    public void set(PersonDto personDto) {
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

    // dynamicデータはgetter()より、値を取得した方がいい。
    public Integer getAge() {
        if (this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        } else {
            return null;
        }
    }

    public boolean isBirthdayToday() {
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }
}
