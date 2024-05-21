package org.altimetrik.automotiveims.exception;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    public ResourceNotFoundException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
