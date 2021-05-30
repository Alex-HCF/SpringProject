package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.data.dto.PersonInfoDto;
import org.example.data.dto.UpdatePersonDto;
import org.example.data.entity.Person;
import org.example.data.mapper.PersonMapper;
import org.example.exception.EntityNotFound;
import org.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }


    @GetMapping("{id}")
    ResponseEntity<?> getPersonInfo(@PathVariable(name = "id") Long id){
        Person person = personService.findById(id);
        PersonInfoDto personInfoDto = personMapper.personToPersonInfoDto(person);
        return new ResponseEntity<>(personInfoDto, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> getPersonsInfo(@RequestParam(name = "startId") Long startId, @RequestParam(name = "endId") Long endId){
        List<Person> person = personService.findPersonsByIdRange(startId, endId);
        List<PersonInfoDto> personInfoDtoList = person.stream().map(personMapper::personToPersonInfoDto).collect(Collectors.toList());
        return new ResponseEntity<>(personInfoDtoList, HttpStatus.OK);
    }

    @PutMapping("{id}/change_status")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    ResponseEntity<?> changeStatus(@PathVariable(name = "id") Long id, @RequestParam("new_status") String status){
        Person person;
        person = switch (status){
            case "active" -> personService.activate(id);
            case "ban" -> personService.ban(id);
            case "disable" -> personService.disable(id);
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
        return new ResponseEntity<>(personMapper.personToPersonInfoDto(person), HttpStatus.OK);
    }

    @PutMapping("{id}/update")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    ResponseEntity<?> updatePerson(@PathVariable("id") Long id, @RequestBody UpdatePersonDto updatePersonDto){
        Person person = personService.update(id, updatePersonDto);
        return new ResponseEntity<>(personMapper.personToPersonInfoDto(person), HttpStatus.OK);
    }

}
