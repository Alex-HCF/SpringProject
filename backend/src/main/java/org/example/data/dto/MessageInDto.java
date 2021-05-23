package org.example.data.dto;

import lombok.Data;

@Data
public class MessageInDto {
    Long recipientId;

    Long noteId;

    String text;
}
