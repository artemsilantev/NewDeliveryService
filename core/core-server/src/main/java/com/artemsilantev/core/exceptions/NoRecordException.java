package com.artemsilantev.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoRecordException extends RuntimeException {

  private final String entityName;
  private final Long id;


  @Override
  public String getMessage() {
    return String.format("%s was not found by this id (%d)", entityName, id);
  }
}
