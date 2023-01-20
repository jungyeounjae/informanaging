package com.informanaging.project.demo.repository;

import com.informanaging.project.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

// jpa repository 를 상속받으면 기본적으로 create update delete select 생성해준다
// JpaRepository<Entity, Id>
// JpaRepository를 상속 받앋기 때문에 @Repository어노테이션을 붙이지 않아도 된다
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);  // find - select , By - where , Name - parameter

    List<Person> findByBlockIsNull();

    List<Person> findByBloodType(String bloodType);

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday =?1") //JPQL , 엔티티 기반으로 실행시킨다, person.get birthday get monthof... ?1은 첫번째 인자
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    // ?1가 아닌 문자열로 표현하고 싶으면 @Param을 사용한다
    // @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday and pers면on.birtday.dayOfBirthday = :dayOfBirthday")

    // nativequery를 사용하면 네이티브 쿼리로 사용가능
    // @Query(value = "select * from Person person where person.birthday.monthOfBirthday =?1 and person.birtday.dayOfBirthday =?2", nativeQuery = true)
    // 그러면 value = "select * from person where month_of_birthday = :monthOfBirthday and day_of_birthday=:dayOfBirthday" 로 가능하다!

}
