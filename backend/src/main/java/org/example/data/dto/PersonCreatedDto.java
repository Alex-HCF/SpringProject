package org.example.data.dto;

import lombok.Data;
import org.example.data.entity.Role;
import org.example.data.entity.Status;

@Data
public class PersonCreatedDto {
    Long personId;
    String login;
    String name;
    String surname;
    Role role;
    Status status;
}
