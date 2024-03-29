package com.informanaging.project.demo.repository;

import com.informanaging.project.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional // test data rollback
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();
        person.setName("john");
        person.setBloodType("A");

        personRepository.save(person);

        List<Person> people = personRepository.findByName("john");

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("john");
        assertThat(people.get(0).getBloodType()).isEqualTo("A");
    }

    @Test
    void findByBloodType() {
//        givenPerson("martin",10,"A");
//        givenPerson("david",9,"B");
//        givenPerson("dennis",8,"O");
//        givenPerson("sophia",7,"AB");
//        givenPerson("benny",6,"A");
        List<Person> result = personRepository.findByBloodType("A");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("benny");
    }

    @Test
    void findByBirthdayBetween() {
//        givenPerson("martin",10,"A",LocalDate.of(1991,2,21));
//        givenPerson("david",9,"B",LocalDate.of(1992,7,1));
//        givenPerson("dennis",8,"O",LocalDate.of(1993,1,5));
//        givenPerson("sophia",7,"AB",LocalDate.of(1994,6,30));
//        givenPerson("benny",6,"A",LocalDate.of(1995,8,30));

        List<Person> result = personRepository.findByMonthOfBirthday(8);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
//        result.forEach(System.out::println);
    }
//
//    private void givenPerson(String name, int age, String bloodType) {
//        givenPerson(name, age ,bloodType, null);
//    }
//    private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
//        Person person = new Person(name, age,bloodType);
//        person.setBirthday(new Birthday(birthday));
//        personRepository.save(person);
//    }
}