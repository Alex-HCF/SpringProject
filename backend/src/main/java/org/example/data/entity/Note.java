package org.example.data.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Person owner;

    private String headline;

    private Double price;

    private String describe;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Category category;

    private Date date;
}
