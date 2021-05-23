package org.example.data.mapper;

import org.example.data.dto.PersonInfoDto;
import org.example.data.entity.Person;
import org.example.service.PersonService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class PersonInfoMapper {
    @Autowired
    private PersonService personService;

    @Mapping(source = "id", target = "personId")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "id", target = "countNotes", qualifiedByName = "getCountNotes")
    @Mapping(source = "id", target = "countOpenNotes", qualifiedByName = "getCountOpenNotes")
    public abstract PersonInfoDto personToPersonInfoDto(Person person);

    @Named("getCountNotes")
    Integer getCountNotes(Long id){
        return personService.countNotesForPersonId(id);
    }
    @Named("getCountOpenNotes")
    Integer getCountOpenNotes(Long id){
        return personService.countOpenNotesForPersonId(id);
    }

//    @AfterMapping
//    private void setAdditionalFeilds(@)

}
