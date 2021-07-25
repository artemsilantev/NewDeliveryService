package com.artemsilantev.web.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IllegalResourceException extends RuntimeException {

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}
