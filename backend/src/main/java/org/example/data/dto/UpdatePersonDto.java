package org.example.data.dto;

import lombok.Data;
import org.example.data.entity.Role;
import org.example.data.entity.Status;

@Data
public class UpdatePersonDto {
    String login;
    String password;
    String name;
    String surname;
    Status status;
    Role role;
}
