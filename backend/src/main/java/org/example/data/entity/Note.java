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
    @JoinColumn(name = "owner_id")
    private Person owner;

    private String headline;

    private Double price;

    private String describe;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name ="create_date")
    private Date createDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    NoteStatus noteStatus;

    @Column(name = "close_date")
    private Date closeDate;
}
