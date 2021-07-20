package com.artemsilantev.core.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DuplicateEntityException extends RuntimeException {

  private final String storageName;
  private final Long id;

  @Override
  public String getMessage() {
    return String.format("Object in this storage %s with id (%d) has duplicate", storageName, id);
  }
}
