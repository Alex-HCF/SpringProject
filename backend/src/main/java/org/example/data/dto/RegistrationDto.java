package org.example.data.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    String login;
    String password;
    String name;
    String surname;
}
