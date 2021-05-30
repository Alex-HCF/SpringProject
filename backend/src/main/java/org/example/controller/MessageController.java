package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.data.entity.Person;
import org.example.utils.PersonUtils;
import org.example.data.dto.MessageInDto;
import org.example.data.dto.MessageOutDto;
import org.example.data.entity.Message;
import org.example.data.mapper.MessageInMapper;
import org.example.data.mapper.MessageOutMapper;
import org.example.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MessageInMapper messageMapper;
    private final MessageOutMapper messageOutMapper;
    private final MessageService messageService;
    private final PersonUtils personUtils;

    public MessageController(MessageInMapper messageMapper, MessageOutMapper messageOutMapper, MessageService messageService, PersonUtils personUtils) {
        this.messageMapper = messageMapper;
        this.messageOutMapper = messageOutMapper;
        this.messageService = messageService;
        this.personUtils = personUtils;
    }

    @PostMapping("send")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    ResponseEntity<?> sendMessage(@RequestBody MessageInDto messageInDto){
        Message message = messageMapper.MessageDtoToMessage(messageInDto);
        message = messageService.save(message);
        var messageOutDto = messageOutMapper.messageToMessageOutDto(message);
        return new ResponseEntity<>(messageOutDto, HttpStatus.OK);
    }

    @GetMapping("findByNote")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    ResponseEntity<?> getLastMessagesByNote(@RequestParam(name = "noteId") Long noteId){
        Long currentUserId = personUtils.getCurrentPerson().getId();
        List<Message> messages = messageService.findByPersonAndNote(currentUserId, noteId);
        List<MessageOutDto> messagesOutDto = messages.stream()
                .map(messageOutMapper::messageToMessageOutDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(messagesOutDto, HttpStatus.OK);
    }


    @GetMapping("findByPerson")
    @SecurityRequirement(name = "bearer-key")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    ResponseEntity<?> getMessagesByPerson(@RequestParam(name = "personId") Long personId){
        Person currentPerson = personUtils.getCurrentPerson();
        List<Message> messages = messageService.findMessagesByTwoPerson(personId, currentPerson.getId());
        List<MessageOutDto> messageOutDtos = messages.stream().map(messageOutMapper::messageToMessageOutDto).collect(Collectors.toList());
        return new ResponseEntity<>(messageOutDtos, HttpStatus.OK);
    }

}
