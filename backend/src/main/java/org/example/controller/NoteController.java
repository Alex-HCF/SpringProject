package org.example.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.data.dto.NoteInDto;
import org.example.data.dto.NoteOutDto;
import org.example.data.dto.SearchDto;
import org.example.data.entity.Note;
import org.example.data.mapper.NoteInMapper;
import org.example.data.mapper.NoteOutMapper;
import org.example.service.NoteService;
import org.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    private final NoteService noteService;
    private final PersonService personService;
    private final NoteInMapper noteInMapper;
    private final NoteOutMapper noteOutMapper;


    public NoteController(NoteService noteService, PersonService personService,
                          NoteInMapper noteInMapper, NoteOutMapper noteOutMapper) {
        this.noteService = noteService;
        this.personService = personService;
        this.noteInMapper = noteInMapper;
        this.noteOutMapper = noteOutMapper;
    }


    @PostMapping
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    ResponseEntity<?> addNote(@RequestBody NoteInDto noteInDto){
        Note note = noteInMapper.NoteDtoToNote(noteInDto);
        note = noteService.addNote(note);
        NoteOutDto noteOutDto = noteOutMapper.noteToNoteOutDto(note);
        return new ResponseEntity<>(noteOutDto, HttpStatus.OK);
    }


    @PostMapping(value = "search")
    ResponseEntity<?> findNotes(@RequestBody SearchDto searchDto){
        List<Note> noteRes = noteService.findBySearchQuery(searchDto);
        noteRes = noteService.relevant(searchDto, noteRes);
        List<NoteOutDto> noteOutDto = noteRes.stream().map(noteOutMapper::noteToNoteOutDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(noteOutDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    ResponseEntity<?> deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
