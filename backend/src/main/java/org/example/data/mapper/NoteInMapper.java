package org.example.data.mapper;

import org.example.utils.PersonUtils;
import org.example.data.dto.NoteInDto;
import org.example.data.entity.Category;
import org.example.data.entity.Note;
import org.example.service.CategoryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(componentModel = "spring", uses = LocationInMapper.class)
public abstract class NoteInMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PersonUtils personUtils;


    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "locationDto", target = "location")
    public abstract Note NoteDtoToNote(NoteInDto noteInDto);

    Category categoryIdToCategory(Long categoryId){
        return categoryService.findById(categoryId);
    }

    @AfterMapping
    void setOwner(@MappingTarget Note note){
         note.setOwner(personUtils.getCurrentPerson());
         note.setCreateDate(new Date());
    }


}
