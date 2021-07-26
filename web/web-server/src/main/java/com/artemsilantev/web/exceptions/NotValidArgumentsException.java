package com.artemsilantev.web.exceptions;

import java.util.Arrays;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
public class NotValidArgumentsException extends RuntimeException {

  private final Collection<String> errors;
  private final String message;

  public NotValidArgumentsException(String errors, String message) {
    this.errors = Arrays.asList(errors);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return new StringBuilder()
        .append(message).append("Errors: ").append(StringUtils.join(errors))
        .toString();
  }
}
