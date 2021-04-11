package org.example.repository;

import org.example.data.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Override
    Optional<Note> findById(Long id);

    boolean existsById(Long id);
}
