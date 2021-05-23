package org.example.data.mapper;

import org.example.data.dto.CategoryInDto;
import org.example.data.dto.CategoryOutDto;
import org.example.data.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryOutMapper {

    public abstract CategoryOutDto categoryToCategoryOutDto(Category category);
}
