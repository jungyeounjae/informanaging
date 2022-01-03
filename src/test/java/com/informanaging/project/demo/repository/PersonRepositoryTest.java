package com.informanaging.project.demo.repository;

import com.informanaging.project.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();
        person.setName("YJ");
        person.setAge(10);
        person.setBloodType("A");

        personRepository.save(person);

//        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("YJ");
        assertThat(people.get(0).getAge()).isEqualTo(10);
        assertThat(people.get(0)).isEqualTo("A");
    }

    @Test
    void hashCodeAndEquals() {
        Person person1 = new Person("margin", 10);
        Person person2 = new Person("margin", 10);

        System.out.println(person1.equals(person2)); // hresult : false
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));

    }
}