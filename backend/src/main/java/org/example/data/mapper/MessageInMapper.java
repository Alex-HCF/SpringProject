package org.example.data.mapper;

import org.example.utils.PersonUtils;
import org.example.data.dto.MessageInDto;
import org.example.data.entity.Message;
import org.example.data.entity.Note;
import org.example.data.entity.Person;
import org.example.exception.EntityNotFound;
import org.example.service.NoteService;
import org.example.service.PersonService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class MessageInMapper {

    @Autowired
    private PersonUtils personUtils;

    @Autowired
    private NoteService noteService;

    @Autowired
    private PersonService personService;



    @Mapping(source = "recipientId", target = "recipient", qualifiedByName = "getPerson")
    @Mapping(source = "noteId", target = "note", qualifiedByName = "getNote")
    @Mapping(source = "text", target = "text")
    public abstract Message MessageDtoToMessage(MessageInDto messageInDto);

    @Named("getNote")
    Note getNote(Long id){
        if(id == null)
            return null;
        return noteService.findById(id);
    }
    @Named("getPerson")
    Person getPerson(Long id){
        if(id == null) {
            throw new EntityNotFound(Person.class, id);
        }
        return personService.findById(id);
    }

    @AfterMapping
    void afterMapping(@MappingTarget Message message){
        message.setSender(personUtils.getCurrentPerson());
        message.setDate(new Date());
    }

}
