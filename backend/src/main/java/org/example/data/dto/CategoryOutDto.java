package org.example.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CategoryOutDto {

    private Long id;

    private Long parentId;

    private String name;
}
