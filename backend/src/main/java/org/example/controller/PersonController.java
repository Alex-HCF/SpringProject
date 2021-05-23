package org.example.controller;

import org.example.data.dto.PersonInfoDto;
import org.example.data.entity.Person;
import org.example.data.mapper.PersonInfoMapper;
import org.example.exception.EntityNotFound;
import org.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;
    private final PersonInfoMapper personInfoMapper;

    public PersonController(PersonService personService, PersonInfoMapper personInfoMapper) {
        this.personService = personService;
        this.personInfoMapper = personInfoMapper;
    }


    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("info")
    ResponseEntity<?> getPersonInfo(@RequestParam(name = "id") Long id){
        Person person = personService.findById(id).orElseThrow(() -> new EntityNotFound(Person.class, id));
        PersonInfoDto personInfoDto = personInfoMapper.personToPersonInfoDto(person);
        return new ResponseEntity<>(personInfoDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("info")
    ResponseEntity<?> getPersonsInfo(@RequestParam(name = "startId") Long startId, @RequestParam(name = "endId") Long endId){
        Person person = personService.findById(id).orElseThrow(() -> new EntityNotFound(Person.class, id));
        PersonInfoDto personInfoDto = personInfoMapper.personToPersonInfoDto(person);
        return new ResponseEntity<>(personInfoDto, HttpStatus.OK);
    }
}
