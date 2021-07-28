package com.artemsilantev.core.exception;

public class LoadDataException extends RuntimeException {

  public LoadDataException(String message, Throwable cause) {
    super(message, cause);
  }

  public LoadDataException(String message) {
    super(message);
  }
}
