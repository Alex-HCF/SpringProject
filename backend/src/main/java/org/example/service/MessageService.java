package org.example.service;

import org.example.data.entity.Message;
import org.example.data.entity.Note;
import org.example.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<Message> findById(Long id){
        return messageRepository.findById(id);
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }

//    public List<Message> findByPersonsAndNote(Long personA, Long personB, Long Note){
//    }

    public List<Message> findByPersonAndNote(Long personId, Long noteId){
        return messageRepository.findMessagesByPersonAndNote(personId, noteId);
    }
//    public List<Message> findHeadMessagesByPerson(Long personId){
//        return messageRepository.findMessagesByPersonAndNote(personId, noteId);
//    }
}
