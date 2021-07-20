package com.artemsilantev.core.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IllegalEntityException extends RuntimeException {

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}
