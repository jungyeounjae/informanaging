package com.informanaging.project.demo.service;

import com.informanaging.project.demo.domain.Block;
import com.informanaging.project.demo.domain.Person;
import com.informanaging.project.demo.repository.BlockRepository;
import com.informanaging.project.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
        List<Person> people = personRepository.findAll();
         List<Block> blocks = blockRepository.findAll();
        // blocks객체를 순회하면서 block객체의 Getname을 실행
        // map은 리턴 값이 STReam이기 때문에 collect를 실행
//         List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
    }
}
