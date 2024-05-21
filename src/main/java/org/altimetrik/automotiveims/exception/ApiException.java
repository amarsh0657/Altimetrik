package org.altimetrik.automotiveims.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZonedDateTime;

@Data
@ControllerAdvice
public class ApiException {
    private String errorMessage;
    private Integer statusCode;
    private ZonedDateTime zonedDateTime;
}
