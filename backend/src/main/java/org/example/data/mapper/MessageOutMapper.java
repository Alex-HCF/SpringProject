package org.example.data.mapper;

import org.example.data.dto.MessageOutDto;
import org.example.data.entity.Message;
import org.example.data.entity.Note;
import org.example.data.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class MessageOutMapper {

    @Mapping(source = "id", target = "messageId")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "note", target = "noteId", qualifiedByName = "getNoteId")
    @Mapping(source = "sender", target = "senderId", qualifiedByName = "getPersonId")
    @Mapping(source = "recipient", target = "recipientId", qualifiedByName = "getPersonId")
    public abstract MessageOutDto messageToMessageOutDto(Message message);

    @Named("getPersonId")
    Long getPersonId(Person person){
        return person == null ? null : person.getId();
    }
    @Named("getNoteId")
    Long getNoteId(Note note){
        return note == null ? null : note.getId();
    }

}
