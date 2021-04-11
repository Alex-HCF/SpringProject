package org.example.controller;

import com.github.dozermapper.core.Mapper;
import org.example.data.dto.NoteDto;
import org.example.data.entity.Note;
import org.example.data.entity.Person;
import org.example.security.SecurityUser;
import org.example.service.NoteService;
import org.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    private final Mapper dozerMapper;

    private final NoteService noteService;

    private final PersonService personService;


    public NoteController(Mapper dozerMapper, NoteService noteService, PersonService personService) {
        this.dozerMapper = dozerMapper;
        this.noteService = noteService;
        this.personService = personService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<?> addNote(NoteDto noteDto, SecurityUser secOwner){
        Note note = dozerMapper.map(noteDto, Note.class);
        Person person = personService.findByUserDetails(secOwner).get();
        note.setOwner(person);
        var res = noteService.addNote(note);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
