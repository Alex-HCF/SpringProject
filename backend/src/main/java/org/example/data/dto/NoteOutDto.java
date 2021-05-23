package org.example.data.dto;

import lombok.Data;
import org.example.data.entity.NoteStatus;

import java.util.Date;

@Data
public class NoteOutDto {

    private Long id;

    private Long owner;

    private String headline;

    private Double price;

    private String describe;

    private LocationOutDto locationDto;

    private CategoryOutDto categoryDto;

    private Date createDate;

    private NoteStatus noteStatus;

    private Date closeDate;

}
