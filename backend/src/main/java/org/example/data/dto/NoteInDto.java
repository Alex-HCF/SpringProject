package org.example.data.dto;

import lombok.Data;

@Data
public class NoteInDto {
    private String headline;

    private Double price;

    private String describe;

    private LocationInDto locationDto;

    private Integer categoryId;
}
