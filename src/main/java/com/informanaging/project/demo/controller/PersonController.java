package com.informanaging.project.demo.controller;

import com.informanaging.project.demo.controller.dto.PersonDto;
import com.informanaging.project.demo.domain.Person;
import com.informanaging.project.demo.repository.PersonRepository;
import com.informanaging.project.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person") // scope는 클래스 전
@RestController // REST API Controller 사용한다는 것을 프레임워크에 알려줌
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    @RequestMapping(value = "/{id}") // /api/person/{id}의 특정 위치에 있는 값을 PathVariable 로 받겠다는 의미이다. id 와  {id} 가 mapping된
//    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    // requestody로 명시해주면 json타입의 content타입을 가지고 테스프 할 수 있다
    // responststatus는 200의 rest통신 성공과 더해, 데이터가 제대로 post됐다는 것을 의미한다. 테스트코드도 iscreated로 변경해줄 필요 있다.
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
//    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) {
        personService.put(person);
        log.info("person -> {}", personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto person){
        personService.modify(id, person);

        log.info("person -> {}", personRepository.findAll());
    }

    @PatchMapping // 一部のリソースだけ更新
    public void modifyPerson(@PathVariable Long id, String name) {
        personService.modify(id, name);
        log.info("person -> {}", personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
        log.info("person -> {}", personRepository.findAll());
    }
}