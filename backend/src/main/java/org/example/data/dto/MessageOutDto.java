package org.example.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageOutDto {
    Long messageId;
    Long recipientId;
    Long senderId;
    Long noteId;
    String text;
    Date date;
}
