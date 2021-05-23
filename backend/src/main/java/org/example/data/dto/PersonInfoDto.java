package org.example.data.dto;

import lombok.Data;

@Data
public class PersonInfoDto {
    Long personId;
    String login;
    String status;
    String name;
    String surname;
    Integer countNotes;
    Integer countOpenNotes;
    Integer countMessages;
}
