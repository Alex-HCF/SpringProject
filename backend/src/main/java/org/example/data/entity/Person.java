package org.example.data.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "person", schema = "public")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String login;

    @NonNull
    private String name;

    private String surname;

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
