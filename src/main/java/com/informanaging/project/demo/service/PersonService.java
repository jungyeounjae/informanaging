package com.informanaging.project.demo.service;

import com.informanaging.project.demo.controller.dto.PersonDto;
import com.informanaging.project.demo.domain.Block;
import com.informanaging.project.demo.domain.Person;
import com.informanaging.project.demo.domain.dto.Birthday;
import com.informanaging.project.demo.repository.BlockRepository;
import com.informanaging.project.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
        List<Person> people = personRepository.findAll();
         List<Block> blocks = blockRepository.findAll();
        // blocks 객체를 순회 하면서 block 객체의 GetName 을 실행
        // map 은 리턴 값이 Stream 이기 때문에 collect 를 실행
//         List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());
//        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());

        return personRepository.findByBlockIsNull();
    }

    @Transactional
    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);

        log.info("person : {}", person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> people = personRepository.findAll();
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByName(name);
    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("id is not existed"));

        if (!person.getName().equals(personDto.getName())) {
            throw new RuntimeException("名前が違います");
        }

        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("not existed id"));

        person.setName(name);

        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {

//      personRepository.deleteById(id); hard delete

        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("not existed id"));
        // soft delete
        person.setDeleted(true);

        personRepository.save(person);
    }
}
