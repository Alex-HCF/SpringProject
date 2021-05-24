package org.example.data.mapper;

import org.example.data.dto.CategoryInDto;
import org.example.data.dto.CategoryOutDto;
import org.example.data.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract CategoryInDto categoryToCategoryInDto(Category category);


    public abstract CategoryOutDto categoryToCategoryOutDto(Category category);


    public abstract Category categoryInToCategory(CategoryInDto categoryInDto);
}
