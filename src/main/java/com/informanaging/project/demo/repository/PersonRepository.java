package com.informanaging.project.demo.repository;

import com.informanaging.project.demo.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// jpa repository 를 상속받으면 기본적으로 create update delete select 생성해준다
// JpaRepository<Entity, Id>
// JpaRepository를 상속 받앋기 때문에 @Repository어노테이션을 붙이지 않아도 된다
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);  // find - select , By - where , Name - parameter

    List<Person> findByBlockIsNull();

    List<Person> findByBloodType(String bloodType);

    // Person findByBloodType(String bloodType);

    List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);
}
