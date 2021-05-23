package org.example.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    Person sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    Person recipient;

    @ManyToOne
    @JoinColumn(name = "note_id")
    Note note;

    @NotBlank
    String text;

    Date date;
}
