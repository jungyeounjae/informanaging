package com.informanaging.project.demo.repository;

import com.informanaging.project.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// jpa repository 를 상속받으면 기본적으로 create update delete select 생성해준다
// JpaRepository<Entity, Id>
public interface PersonRepository extends JpaRepository<Person, Long> {

}
