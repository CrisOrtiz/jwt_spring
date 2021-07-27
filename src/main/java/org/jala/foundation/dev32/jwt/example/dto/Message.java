package org.jala.foundation.dev32.jwt.example.dto;

import lombok.Getter;
import lombok.Setter;

public class Message {
    @Getter
    @Setter
    private String message;

    public Message(String message) {
        this.message = message;
    }
}
