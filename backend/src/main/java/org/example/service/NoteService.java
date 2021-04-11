package org.example.service;

import org.example.data.entity.Category;
import org.example.data.entity.Note;
import org.example.exception.EntityNotFound;
import org.example.repository.CategoryRepository;
import org.example.repository.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    public NoteService(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    public Note addNote(Note note){
        if(!categoryRepository.existsById(note.getCategory().getId())){
            throw new EntityNotFound(Category.class, note.getCategory().getId());
        }
        return noteRepository.save(note);
    }

    public Note updateNote(Note note){
        if(!noteRepository.existsById(note.getId())){
            throw new EntityNotFound(Note.class, note.getId());
        }
        return noteRepository.save(note);
    }

    public void deleteNote(Long id){
        if(!noteRepository.existsById(id)){
            throw new EntityNotFound(Note.class, id);
        }
        noteRepository.deleteById(id);
    }
}
