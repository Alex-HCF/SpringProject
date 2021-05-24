package org.example.data.mapper;

import org.example.data.dto.NoteOutDto;
import org.example.data.entity.Note;
import org.example.data.entity.Person;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, CategoryMapper.class})
public abstract class NoteOutMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "headline", target = "headline")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "describe", target = "describe")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "closeDate", target = "closeDate")
    @Mapping(source = "location", target = "locationDto")
    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "owner", target = "owner", qualifiedByName = "getPersonId")
    public abstract NoteOutDto noteToNoteOutDto(Note note);

    @Named("getPersonId")
    Long getPersonId(Person person){
        return person == null ? null : person.getId();
    }

}
