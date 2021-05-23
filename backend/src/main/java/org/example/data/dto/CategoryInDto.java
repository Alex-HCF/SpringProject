package org.example.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CategoryInDto {

    private Long parentId;

    private String name;
}
