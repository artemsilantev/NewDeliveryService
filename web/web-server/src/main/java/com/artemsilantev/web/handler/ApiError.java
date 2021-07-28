package com.artemsilantev.web.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

  private final int status;
  private final String message;
}
