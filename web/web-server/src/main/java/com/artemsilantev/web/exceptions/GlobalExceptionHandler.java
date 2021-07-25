package com.artemsilantev.web.exceptions;

import com.artemsilantev.core.exceptions.IllegalEntityException;
import com.artemsilantev.core.exceptions.NoRecordException;
import java.rmi.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    var apiError = new ApiError(status.value(), ex.getMessage());
    return super.handleExceptionInternal(ex, apiError, headers, status, request);
  }

  @ExceptionHandler(value = {NoRecordException.class})
  public ResponseEntity<Object> handleNoRecordException(NoRecordException exception,
      WebRequest request) {
    var notFoundException = new ResourceNotFoundException(exception.getMessage());
    return handleExceptionInternal(notFoundException, null,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {IllegalEntityException.class})
  public ResponseEntity<Object> handleIllegalEntityException(IllegalEntityException exception,
      WebRequest request) {
    var illegalResourceException = new IllegalResourceException(exception.getMessage());
    return handleExceptionInternal(illegalResourceException, null,
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleServerException(Exception exception, WebRequest request) {
    log.error("Error occurred: {} - {}", exception.getClass(), exception.getMessage());
    var serverException = new ServerException("Problem on server side. Please try again later.");
    return handleExceptionInternal(serverException, null,
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }


}
