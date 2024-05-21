package org.altimetrik.automotiveims.exception;

import lombok.Data;

@Data
public class BadRequestException extends  RuntimeException {
    private String message;

    public BadRequestException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
