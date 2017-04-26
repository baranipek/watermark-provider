package com.watermark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class WaterMarkNotCompletedException extends RuntimeException {
    public WaterMarkNotCompletedException(String message) {
        super(message);
    }
}
