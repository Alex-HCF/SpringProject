package org.example.data.dto;

import lombok.Data;
import org.example.data.entity.Category;
import org.example.data.entity.Location;

@Data
public class NoteDto {
    private String headline;

    private Double price;

    private String describe;

    private Location location;

    private Category category;
}
