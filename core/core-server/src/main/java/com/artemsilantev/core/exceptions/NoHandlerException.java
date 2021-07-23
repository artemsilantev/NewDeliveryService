package com.artemsilantev.core.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoHandlerException extends RuntimeException{
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
