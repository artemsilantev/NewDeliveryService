package com.artemsilantev.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

  private final int status;
  private final String message;
}
