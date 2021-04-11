package org.example.data.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String login;

    private String name;

    private String surname;
}
