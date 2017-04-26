package com.watermark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DocumentNotfoundException extends RuntimeException {
    public DocumentNotfoundException(String message) {
        super(message);
    }
}
