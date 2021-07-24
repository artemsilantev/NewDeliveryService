package com.artemsilantev.web.exceptions;

import com.artemsilantev.core.exceptions.NoRecordException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NoRecordException.class})
  public ResponseEntity<?> handleNoRecordException(NoRecordException exception,
      WebRequest request) {
    var message = String
        .format("%s not found by id (%d)", exception.getEntityName(), exception.getId());
    return handleExceptionInternal(exception, message,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
